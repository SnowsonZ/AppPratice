package com.example.snowson.apptest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.snowson.apptest.R;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Logger  Timber
public class LoggerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logger);
        initData();
    }

    private void initData() {
        String value = "Hello world!!";
        String[] strings = new String[5];
        for (int i = 0; i < strings.length; i++) {
            strings[i] = "当前Index:" + i;
        }
        String jsonString = "[{name: 'snowson', age: 24, priority: 10}, {goodsname: 'bask', " +
                "type: 3, count: 10}, {city:'北京', citycode: 1012, weather: '阴雨'}]";
        Map<String, String> map = new HashMap<String, String>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        map.put("key4", "value4");

        List<String> lists = new ArrayList<String>();
        lists.add("listone");
        lists.add("listtwo");
        lists.add("listthree");

        Logger.d("error!!!!!!!!!!");
        //log
        Logger.d(value);
        Logger.w(value);
        Logger.e(value);
        Logger.i(value);
        Logger.v(value);
        Logger.wtf(value);

        Logger.d(strings);
        Logger.json(jsonString);
        Logger.d(map);
        Logger.d(lists);
    }
}
