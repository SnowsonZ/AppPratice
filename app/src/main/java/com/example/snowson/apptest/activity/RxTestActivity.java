package com.example.snowson.apptest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.snowson.apptest.R;
import com.example.snowson.apptest.net.RetrofitBuilder;
import com.example.snowson.apptest.net.retrofittest.LoginRequest;
import com.example.snowson.apptest.net.retrofittest.LoginResponse;
import com.example.snowson.apptest.net.retrofittest.RetrofitTest;
import com.example.snowson.apptest.utils.ScreenUtils;
import com.orhanobut.logger.Logger;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class RxTestActivity extends AppCompatActivity {

    private Button mRxTestBtn;
    private static final String TAG = RxTestActivity.class.getName();
    //Disposable,观察者用来取消接收被观察者发来的信息
    //CompositeDisposable用来管理Disposable
    private Disposable mDisposable;
    private CompositeDisposable mDisposables = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_test);
        initView();
        initEvent();
        testSyncObserable("one", "two", "three", "four", "five");
        testAsyncObserable();
    }

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


    private void initEvent() {
        mRxTestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void initView() {
        mRxTestBtn = findViewById(R.id.btn_test);
        Logger.d(TAG + " > " + "initView method");
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
