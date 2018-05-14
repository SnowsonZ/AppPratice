package com.example.snowson.apptest.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.snowson.apptest.R;
import com.example.snowson.apptest.utils.MapUtils;

public class MapTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_test);

        int count = 10000;
        int key = 423;
        MapUtils.arrayMap(count, key);
        MapUtils.parseArray(count, key);
        MapUtils.hashMap(count, key);
    }
}
