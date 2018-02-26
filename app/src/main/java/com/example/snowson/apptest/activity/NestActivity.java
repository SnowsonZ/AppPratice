package com.example.snowson.apptest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.snowson.apptest.R;
import com.example.snowson.apptest.adapter.SimpleAdapter;
import com.example.snowson.apptest.bean.DataTypeTwo;
import com.example.snowson.apptest.utils.ScreenUtils;

import java.util.ArrayList;

public class NestActivity extends AppCompatActivity {

    private int colorSet[] = {android.R.color.holo_green_light,
            android.R.color.holo_orange_light, android.R.color.holo_blue_light};
    private ArrayList<DataTypeTwo> data_two;
    private ListView mDisplayLv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nest);
        mDisplayLv = findViewById(R.id.lv_display);
        initData();
    }

    private void initData() {
        data_two = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            DataTypeTwo item = new DataTypeTwo();
            item.setColorTinyPic(colorSet[i % colorSet.length]);
            item.setTinyName("TinyName" + i);
            data_two.add(item);
        }
        mDisplayLv.setAdapter(new SimpleAdapter<>(this, data_two));
        mDisplayLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ScreenUtils.showToast(NestActivity.this, data_two.get(position).getTinyName());
            }
        });
    }
}
