package com.example.snowson.apptest;

import android.app.Application;

import com.example.snowson.apptest.utils.Constants;
import com.example.snowson.apptest.utils.PackageUtils;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

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

                PackageUtils
                        .getAppMetaData(this, Constants.BUGLY_ID, ""),
                PackageUtils
                        .getAppMetaData(this, Constants.IS_RELEASE, false));

        //init umeng
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "");
        UMConfigure.setLogEnabled(PackageUtils.getAppMetaData(this, Constants.IS_RELEASE,
                false));
//        PlatformConfig.setWeixin();
        PlatformConfig.setQQZone("ID1106650050", "HATNxrHJ4w70Akro");
//        PlatformConfig.setAlipay();
    }
}
