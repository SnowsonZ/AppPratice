package com.example.snowson.apptest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.snowson.apptest.R;
import com.example.snowson.apptest.utils.ScreenUtils;
import com.jakewharton.rxbinding2.InitialValueObservable;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxCompoundButton;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function3;

public class RxBinddingTestActivity extends AppCompatActivity {

    private CheckBox mItem1Cb;
    private CheckBox mItem2Cb;
    private CheckBox mItem3Cb;
    private Button mClickBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_bindding_test);
        initView();
        initEvent();
    }

    private void initEvent() {
        mClickBtn.setEnabled(false);
        mClickBtn.setClickable(false);
        InitialValueObservable<Boolean> obsCb1 = RxCompoundButton.checkedChanges(mItem1Cb);
        InitialValueObservable<Boolean> obsCb2 = RxCompoundButton.checkedChanges(mItem2Cb);
        InitialValueObservable<Boolean> obsOb3 = RxCompoundButton.checkedChanges(mItem3Cb);
        Observable.combineLatest(obsCb1, obsCb2, obsOb3, new Function3<Boolean, Boolean, Boolean, Boolean>() {
            @Override
            public Boolean apply(Boolean aBoolean, Boolean aBoolean2, Boolean aBoolean3) throws Exception {
                return aBoolean && aBoolean2 && aBoolean3;
            }
        }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                mClickBtn.setEnabled(aBoolean);
                mClickBtn.setClickable(aBoolean);
            }
        });

        RxView.clicks(mClickBtn)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object aVoid) throws Exception {
                        ScreenUtils.showToast(RxBinddingTestActivity.this, "提交...");
                    }
                });
    }

    private void initView() {
        mItem1Cb = findViewById(R.id.cb_item1);
        mItem2Cb = findViewById(R.id.cb_item2);
        mItem3Cb = findViewById(R.id.cb_item3);
        mClickBtn = findViewById(R.id.btn_click);
    }
}
