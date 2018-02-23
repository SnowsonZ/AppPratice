package com.example.snowson.apptest.activity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.snowson.apptest.DirectionManager;
import com.example.snowson.apptest.R;
import com.example.snowson.apptest.view.MiCompassView;

public class AComponentsActivity extends AppCompatActivity {

    private MiCompassView mCompassView;
    private float[] accelerometer = new float[3];
    private float[] magnetic = new float[3];
    private float[] direction = new float[9];

    private SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                accelerometer = event.values;
            }
            if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
                magnetic = event.values;
            }
            if (accelerometer.length > 0 && accelerometer.length > 0) {
                SensorManager.getRotationMatrix(direction, null, accelerometer, magnetic);
                float[] value = new float[3];
                SensorManager.getOrientation(direction, value);
                mCompassView.setCurDirection((float) Math.toDegrees(value[0]));
            }
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
