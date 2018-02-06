package com.example.snowson.apptest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.snowson.apptest.DaggerComponent.ApplicationComponent;
import com.example.snowson.apptest.MainApplication;
import com.example.snowson.apptest.R;
import com.example.snowson.apptest.bean.dagger2.CartBean;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class AndroidDaggerActivity extends AppCompatActivity {

    @Inject
    CartBean cBean;
    @Inject
    String packageName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_dagger);
        ApplicationComponent appComponent
                = ((MainApplication) getApplicationContext()).getApplicationComponent();
        Logger.d(appComponent.toString());
        Logger.d(cBean.getCartId());
        Logger.d(packageName);
    }
}
