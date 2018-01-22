package com.example.snowson.apptest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.snowson.apptest.R;
import com.example.snowson.apptest.bean.rx.BaseUserInfo;
import com.example.snowson.apptest.bean.rx.ExtraUserInfo;
import com.example.snowson.apptest.bean.rx.LoginRequest;
import com.example.snowson.apptest.bean.rx.LoginResponse;
import com.example.snowson.apptest.bean.rx.RegisterRequest;
import com.example.snowson.apptest.bean.rx.RegisterResponse;
import com.example.snowson.apptest.bean.rx.UserInfo;
import com.example.snowson.apptest.net.RetrofitBuilder;
import com.example.snowson.apptest.net.retrofittest.RetrofitTest;
import com.example.snowson.apptest.utils.ScreenUtils;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.orhanobut.logger.Logger;

import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class RxTestActivity extends AppCompatActivity {

    private Button mRxTestBtn;
    private static final String TAG = RxTestActivity.class.getName();
    //Disposable,观察者用来取消接收被观察者发来的信息
    //CompositeDisposable用来管理Disposable
    private Disposable mDisposable;
    private CompositeDisposable mDisposables = new CompositeDisposable();
    private Subscription mSubscription;
    private EditText mTestEt;
    private TextView mResultTv;
    private Button mUnsubscribeBtn;
    private CompositeDisposable mCompositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_test);
        initView();
        initEvent();
//        testSyncObserable("one", "two", "three", "four", "five");
//        testAsyncObserable();

        Observable.create(new ObservableOnSubscribe<Void>() {
            @Override
            public void subscribe(ObservableEmitter<Void> emitter) throws Exception {

            }
        });
    }

    private void backpressObservable() {
        Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> emitter) throws Exception {
                if (emitter.requested() >= 0) {
                    emitter.onNext("sinal1");
                }
            }
            //设置背压类型
        }, BackpressureStrategy.ERROR)
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new FlowableSubscriber<String>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        mSubscription = s;
                        s.request(10);
                    }

                    @Override
                    public void onNext(String s) {
                        //消费事件,并再次请求事件
                        mSubscription.request(10);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * zip 多请求合并
     */
    private void zipAsyncObservable() {
        Retrofit retrofit = RetrofitBuilder.create();
        RetrofitTest api = retrofit.create(RetrofitTest.class);
        Observable<BaseUserInfo> buiObv = Observable.create(new ObservableOnSubscribe<BaseUserInfo>() {
            @Override
            public void subscribe(ObservableEmitter<BaseUserInfo> emitter) throws Exception {

            }
        }).subscribeOn(Schedulers.io());

        Observable<ExtraUserInfo> euiObv = Observable.create(new ObservableOnSubscribe<ExtraUserInfo>() {
            @Override
            public void subscribe(ObservableEmitter<ExtraUserInfo> emitter) throws Exception {

            }
        }).subscribeOn(Schedulers.io());
        Observable.zip(buiObv, euiObv, new BiFunction<BaseUserInfo, ExtraUserInfo, UserInfo>() {
            @Override
            public UserInfo apply(BaseUserInfo baseUserInfo, ExtraUserInfo extraUserInfo) throws Exception {
                return null;
            }
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UserInfo>() {
                    @Override
                    public void accept(UserInfo userInfo) throws Exception {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    /**
     * nested async opt used flatmap
     */
    private void nestedAsydncObserable() {
        Retrofit retrofit = RetrofitBuilder.create();
        final RetrofitTest api = retrofit.create(RetrofitTest.class);
        api.register(new RegisterRequest())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                //注册成功
                .doOnNext(new Consumer<RegisterResponse>() {
                    @Override
                    public void accept(RegisterResponse registerResponse) throws Exception {

                    }
                })
                //注册失败
                .onErrorReturn(new Function<Throwable, RegisterResponse>() {
                    @Override
                    public RegisterResponse apply(Throwable throwable) throws Exception {
                        return null;
                    }
                })
                .observeOn(Schedulers.io())
                .flatMap(new Function<RegisterResponse, ObservableSource<LoginResponse>>() {
                    @Override
                    public ObservableSource<LoginResponse> apply(RegisterResponse registerResponse)
                            throws Exception {
                        return api.login(new LoginRequest());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LoginResponse>() {
                    @Override
                    public void accept(LoginResponse loginResponse) throws Exception {
                        ScreenUtils.showToast(RxTestActivity.this, "登陆成功");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        ScreenUtils.showToast(RxTestActivity.this, "登录失败");
                    }
                });
    }

    /**
     * general async opt
     */
    private void testAsyncObserable() {
        Retrofit retrofit = RetrofitBuilder.create();
        RetrofitTest api = retrofit.create(RetrofitTest.class);
        api.login(new LoginRequest()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable = d;
                        mDisposables.add(mDisposable);
                    }

                    @Override
                    public void onNext(LoginResponse loginResponse) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        //activity销毁或者其他异常原因,不再接收消息
                        mDisposable.dispose();
                        //else
                        ScreenUtils.showToast(RxTestActivity.this, "登录失败");
                    }

                    @Override
                    public void onComplete() {
                        //activity销毁或者其他异常原因,不再接收消息
                        mDisposable.dispose();
                        //else
                        ScreenUtils.showToast(RxTestActivity.this, "登录成功");
                    }
                });
    }

    private void testSyncObserable(final String... names) {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext(names[0]);
                emitter.onNext(names[1]);
                emitter.onNext(names[2]);
                emitter.onNext(names[3]);
                emitter.onComplete();
                emitter.onNext(names[4]);
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                Logger.e(s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }


    //publish connect   mutilpile subscribers
    private void initEvent() {
        ConnectableObservable<String> obs = RxTextView.textChangeEvents(mTestEt)
                .map(event -> event.text().toString()).publish();
        obs.subscribe(content -> mResultTv.setText(content));
        obs.subscribe(content -> ScreenUtils.showToast(this, content));
        mCompositeDisposable = new CompositeDisposable();
        Disposable connect = obs.connect();
        mCompositeDisposable.add(connect);

        RxView.clicks(mUnsubscribeBtn)
                .throttleLast(500, TimeUnit.MICROSECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object obj) throws Exception {
                        //取消注册
                        mCompositeDisposable.dispose();
                    }
                });

        ConnectableObservable<Integer> obsI = Observable.range(7, 1000)
                .sample(2, TimeUnit.MICROSECONDS).publish();
        Disposable disposable3 = obsI.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.i(TAG, " ##1--> " + integer);
            }
        });

        Disposable disposable4 = obsI.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.i(TAG, " ##2--> " + integer);
            }
        });
        Disposable disposable5 = obsI.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.i(TAG, " ##3--> " + integer);
            }
        });
        mCompositeDisposable.add(disposable3);
        mCompositeDisposable.add(disposable4);
        mCompositeDisposable.add(disposable5);
        obsI.connect();
    }

    private void initView() {
        mRxTestBtn = findViewById(R.id.btn_test);
        mTestEt = findViewById(R.id.et_input_test);
        mResultTv = findViewById(R.id.tv_result);
        mUnsubscribeBtn = findViewById(R.id.btn_unsubscribe);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDisposables != null && mDisposables.size() > 0) {
            mDisposables.clear();
            mDisposables = null;
        }
    }
}
