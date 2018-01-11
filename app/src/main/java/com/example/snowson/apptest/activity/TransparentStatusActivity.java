package com.example.snowson.apptest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.snowson.apptest.R;
import com.example.snowson.apptest.utils.ScreenUtils;

/**
 * 沉浸式状态栏适配，自带虚拟按键背景冲突解决
 */

public class TransparentStatusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScreenUtils.transparentStatus(this);
        setContentView(R.layout.activity_trans_tarent_status);

        initHeader();
    }

    private void initHeader() {
        findViewById(R.id.tv_header_search).setVisibility(View.GONE);
    }
}
