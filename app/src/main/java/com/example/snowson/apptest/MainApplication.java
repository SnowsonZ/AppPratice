package com.example.snowson.apptest;

import android.app.Activity;
import android.app.Application;

import com.example.snowson.apptest.DaggerComponent.ApplicationComponent;
import com.example.snowson.apptest.DaggerComponent.DaggerAndroidDaggerApplicationComponent;
import com.example.snowson.apptest.DaggerComponent.DaggerApplicationComponent;
import com.example.snowson.apptest.DaggerModule.ApplicationModule;
import com.example.snowson.apptest.utils.Constants;
import com.example.snowson.apptest.utils.PackageUtils;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.CsvFormatStrategy;
import com.orhanobut.logger.DiskLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.LogAdapter;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * author: snowson
 * created on: 17-12-30 下午5:15
 * description:
 */

public class MainApplication extends Application implements HasActivityInjector {

    private ApplicationComponent mApplicationComponent;
    @Inject
    DispatchingAndroidInjector<Activity> mDispatchingActivityInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerAndroidDaggerApplicationComponent.create().inject(this);
        //init logger
        initLogger();
        //init bugly
        CrashReport.initCrashReport(getApplicationContext(),

                PackageUtils
                        .getAppMetaData(this, Constants.BUGLY_ID, ""),
                !PackageUtils
                        .getAppMetaData(this, Constants.IS_RELEASE, false));

        //init umeng
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "");
        UMConfigure.setLogEnabled(PackageUtils.getAppMetaData(this, Constants.IS_RELEASE,
                false));
//        PlatformConfig.setWeixin();
        PlatformConfig.setQQZone("ID1106650050", "HATNxrHJ4w70Akro");
//        PlatformConfig.setAlipay();
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();
    }

    private void initLogger() {
        FormatStrategy logcatStrategy = PrettyFormatStrategy
                .newBuilder()
                .methodCount(5)
                .tag("AppTest")
                .build();
        FormatStrategy fileLogStrategy = CsvFormatStrategy
                .newBuilder()
                .tag("AppTest")
                .build();
        LogAdapter logcatAdapter = new AndroidLogAdapter(logcatStrategy) {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return true;
            }
        };

        LogAdapter fileAdapter = new DiskLogAdapter(fileLogStrategy) {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return true;
            }
        };
        Logger.addLogAdapter(logcatAdapter);
//        Logger.addLogAdapter(fileAdapter);
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return mDispatchingActivityInjector;
    }
}
