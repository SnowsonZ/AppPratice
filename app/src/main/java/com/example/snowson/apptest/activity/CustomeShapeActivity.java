package com.example.snowson.apptest.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.snowson.apptest.R;

public class CustomeShapeActivity extends AppCompatActivity {

    private View rectangle;
    private Handler handler;
    private float index = 0.1f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shape_image);
        rectangle = findViewById(R.id.rectangle);
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(rectangle.getScaleX() < 0 || rectangle.getScaleY() < 0) {
                    rectangle.setScaleX(0);
                    index = 0.1f;
                }else if(rectangle.getScaleX() > 1 || rectangle.getScaleY() > 1) {
                    rectangle.setScaleX(1);
                    index = -0.1f;
                }
                rectangle.setScaleX(rectangle.getScaleX() + index);
                rectangle.setScaleY(rectangle.getScaleX() + index);
                handler.post(this);
            }
        }, 2000);

    }
}
