package com.example.snowson.apptest.bugly;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.multidex.MultiDex;

import com.example.snowson.apptest.utils.Constants;
import com.example.snowson.apptest.utils.PackageUtils;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.CsvFormatStrategy;
import com.orhanobut.logger.DiskLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.LogAdapter;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.tinker.loader.app.DefaultApplicationLike;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

public class MainApplicationLike extends DefaultApplicationLike {
    private Application mContext;

    public MainApplicationLike(Application application, int tinkerFlags,
                               boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime,
                               long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime,
                applicationStartMillisTime, tinkerResultIntent);
        mContext = application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        DaggerAndroidDaggerApplicationComponent.create().inject(this);
        //init logger
        initLogger();
        //init bugly 添加热修复
//        CrashReport.initCrashReport(mContext,

//                PackageUtils
//                        .getAppMetaData(mContext, Constants.BUGLY_ID, ""),
//                !PackageUtils
//                        .getAppMetaData(mContext, Constants.IS_RELEASE, false));
        Bugly.init(mContext, PackageUtils
                .getAppMetaData(mContext, Constants.BUGLY_ID, ""), !PackageUtils
                .getAppMetaData(mContext, Constants.IS_RELEASE, false));

        //init umeng
        UMConfigure.init(mContext, UMConfigure.DEVICE_TYPE_PHONE, "");
        UMConfigure.setLogEnabled(PackageUtils.getAppMetaData(mContext, Constants.IS_RELEASE,
                false));
//        PlatformConfig.setWeixin();
        PlatformConfig.setQQZone("ID1106650050", "HATNxrHJ4w70Akro");
//        PlatformConfig.setAlipay();
    }

    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        MultiDex.install(base);
        Beta.installTinker(this);
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

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void registerActivityLifecycleCallback(Application.ActivityLifecycleCallbacks callbacks) {
        getApplication().registerActivityLifecycleCallbacks(callbacks);
    }
}
