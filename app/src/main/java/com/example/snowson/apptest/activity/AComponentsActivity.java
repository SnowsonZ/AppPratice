package com.example.snowson.apptest.activity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.snowson.apptest.DirectionManager;
import com.example.snowson.apptest.R;
import com.example.snowson.apptest.view.MiCompassView;

public class AComponentsActivity extends AppCompatActivity {

    private MiCompassView mCompassView;

    private SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            mCompassView.setCurDirection(event.values[0]);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acomponents);
        mCompassView = findViewById(R.id.compassView);
        DirectionManager.bindDirectionService(listener, this, this);
    }
}
