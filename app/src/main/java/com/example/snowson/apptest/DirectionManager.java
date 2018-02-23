package com.example.snowson.apptest;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Created by snowson on 18-2-23.
 */

public class DirectionManager {

    public static void bindDirectionService(SensorEventListener listener, Context context, LifecycleOwner lifecycleOwner) {
        new DirectionListener(listener, context, lifecycleOwner);
    }

    static class DirectionListener implements LifecycleObserver {
        private SensorEventListener mListener;
        private Context mContext;
        private LifecycleOwner mLifecycleOwner;
        private SensorManager mSensorManager;

        public DirectionListener(SensorEventListener listener, Context context, LifecycleOwner lifecycleOwner) {
            mListener = listener;
            mContext = context;
            mLifecycleOwner = lifecycleOwner;
            mLifecycleOwner.getLifecycle().addObserver(this);
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        private void start() {
            mSensorManager = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
            //使用重力加速度及磁场判定当前方向
            if (mSensorManager != null) {
                mSensorManager.registerListener(mListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                        SensorManager.SENSOR_DELAY_NORMAL);
                mSensorManager.registerListener(mListener, mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
                        SensorManager.SENSOR_DELAY_NORMAL);
            }
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        private void stop() {
            if(mSensorManager != null) {
                mSensorManager.unregisterListener(mListener);
            }
        }
    }
}
