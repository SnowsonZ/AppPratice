package com.example.snowson.apptest.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

/**
 * author: snowson
 * created on: 17-12-30 下午5:22
 * description:
 */

public class PackageUtils {

//    public static boolean isDebugVersion(Context context) {
//        ApplicationInfo info = context.getApplicationInfo();
//        return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
//    }

    public static <T> T getAppMetaDataBoolean(Context context, String metaName,
                                                T defValue) {
        try {
            T value = (T) context.getPackageManager().getApplicationInfo(context.getPackageName(),
                    PackageManager.GET_META_DATA).metaData.get(metaName);
            Log.d("meta-data", metaName + " = " + value);
            return value;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return defValue;
        }
    }
}
