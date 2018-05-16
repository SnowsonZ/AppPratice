package com.example.snowson.apptest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.snowson.apptest.aidl.IAidlInterface;

/**
 * Created by Administrator on 2018/5/16.
 */

public class AidlService extends Service {
    private final String TAG = this.getClass().getName();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "Service onBind");
        return new AidlBinder();
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "Service onCreate");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "Service onDestroy");
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "Service onUnbind");
        return super.onUnbind(intent);
    }

    public class AidlBinder extends IAidlInterface.Stub {


        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
                               double aDouble, String aString) throws RemoteException {

        }

        @Override
        public String numberCount(int num) throws RemoteException {
            String content = " current communication info : " + num;
            Log.d("AidlService: ", content);
            return content;
        }
    }
}
