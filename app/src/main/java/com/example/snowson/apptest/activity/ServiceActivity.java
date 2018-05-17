package com.example.snowson.apptest.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.snowson.apptest.AidlService;
import com.example.snowson.apptest.R;
import com.example.snowson.apptest.aidl.IAidlInterface;
import com.example.snowson.apptest.utils.ScreenUtils;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mStartBtn;
    private Button mConnectBtn;
    private Button mStopBtn;
    private LinearLayout mInfoSv;
    private MyConnection mConnection;
    private Intent intent;
    private int num = 0;
    private IAidlInterface binder;
    //    private AidlService.AidlBinder binder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        initView();
    }

    public void initView() {
        mStartBtn = findViewById(R.id.btn_start);
        mConnectBtn = findViewById(R.id.btn_connect);
        mStopBtn = findViewById(R.id.btn_stop);
        mInfoSv = findViewById(R.id.sv_info);


        mStartBtn.setOnClickListener(this);
        mConnectBtn.setOnClickListener(this);
        mStopBtn.setOnClickListener(this);
        mInfoSv.removeAllViews();

        mConnection = new MyConnection();
    }

    public void addInfo(String content) {
        if (TextUtils.isEmpty(content)) {
            return;
        }
        TextView tv = new TextView(this);
        tv.setTextColor(Color.GRAY);
        tv.setGravity(Gravity.LEFT);
        tv.setTextSize(ScreenUtils.sp2px(this, 12));
        tv.setPadding(10, 10, 10, 10);
        tv.setText(content);
        mInfoSv.addView(tv);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                intent = new Intent(this, AidlService.class);
                bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
                startService(intent);
                break;
            case R.id.btn_connect:
                if (binder != null) {
                    try {
                        String content = binder.numberCount(num++);
                        addInfo(content);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.btn_stop:
                unbindService(mConnection);
                stopService(intent);
                break;
            default:
                break;
        }
    }

    class MyConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = IAidlInterface.Stub.asInterface(service);
//            binder = (AidlService.AidlBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mConnection);
        stopService(intent);
        if(mConnection != null) {
            mConnection = null;
        }
    }
}
