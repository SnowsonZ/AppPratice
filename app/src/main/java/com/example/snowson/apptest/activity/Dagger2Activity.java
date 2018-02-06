package com.example.snowson.apptest.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.snowson.apptest.DaggerComponent.DaggerMainComponent;
import com.example.snowson.apptest.DaggerModule.MainModule;
import com.example.snowson.apptest.MainApplication;
import com.example.snowson.apptest.R;
import com.example.snowson.apptest.bean.dagger2.CartBean;
import com.example.snowson.apptest.inter.Level;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

public class Dagger2Activity extends AppCompatActivity {

    @Inject
    @Level("LOW")
    CartBean cBean;
    @Inject
    @Level("LOW")
    CartBean cBean1;
    @Inject
    Context mContext;
    private TextView mDisplayTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger2);
        mDisplayTv = findViewById(R.id.tv_display);
        DaggerMainComponent.builder().
                mainModule(new MainModule(this))
                .applicationComponent(((MainApplication) getApplicationContext()).getApplicationComponent())
                .build().inject(this);
        mDisplayTv.setText(cBean.getCartId());
        Logger.w("cBean---> " + cBean.toString());
        Logger.w("cBean1---> " + cBean1.toString());
        Logger.w("mContext---> " + mContext);
    }
}
