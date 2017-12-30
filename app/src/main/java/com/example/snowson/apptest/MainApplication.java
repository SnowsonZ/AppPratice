package com.example.snowson.apptest;

import android.app.Application;

import com.example.snowson.apptest.utils.Constants;
import com.example.snowson.apptest.utils.PackageUtils;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * author: snowson
 * created on: 17-12-30 下午5:15
 * description:
 */

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //init bugly
        CrashReport.initCrashReport(getApplicationContext(),

                PackageUtils.getAppMetaDataBoolean(this,
                        Constants.BUGLY_ID, ""),
                PackageUtils.getAppMetaDataBoolean(this,
                        Constants.IS_RELEASE, false));
    }
}
