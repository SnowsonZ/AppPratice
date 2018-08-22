package com.example.snowson.apptest.utils;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * Created by snowson on 17-12-19.
 */

public class ScreenUtils {

    private static float originDensity, originScaleDensity;
    private static final int DENSITY_WIDTH_DP = 360;
    private static final int DENSITY_HEIGHT_DP = 640;
    public static final int TYPE_WIDTH = 0;
    public static final int TYPE_HEIGHT = 1;

    public static void setCustomeDensity(@NonNull Activity activity,
                                         @NonNull final Application application,
                                         int type) {
        DisplayMetrics appDisplayMetrics = application.getResources().getDisplayMetrics();
        //获取原来的density
        if (originDensity == 0) {
            originDensity = appDisplayMetrics.density;
            originScaleDensity = appDisplayMetrics.scaledDensity;
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration configuration) {
                    if (configuration != null && configuration.fontScale > 0) {
                        originScaleDensity
                                = application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {

                }
            });
        }
        //计算目标density
        float targetDensity;
        //根据宽或者高进行适配
        switch (type) {
            case TYPE_HEIGHT:
                targetDensity = appDisplayMetrics.heightPixels / DENSITY_HEIGHT_DP;
                break;
            case TYPE_WIDTH:
            default:
                targetDensity = appDisplayMetrics.widthPixels / DENSITY_WIDTH_DP;
        }
        float targetScaleDensity = targetDensity * originScaleDensity / originDensity;
        int targetDensityDpi = (int) (targetDensity * 160);
        //设置目标density
        appDisplayMetrics.density = targetDensity;
        appDisplayMetrics.scaledDensity = targetScaleDensity;
        appDisplayMetrics.densityDpi = targetDensityDpi;

        //设置activity的density
        DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
        activityDisplayMetrics.density = targetDensity;
        activityDisplayMetrics.scaledDensity = targetScaleDensity;
        activityDisplayMetrics.densityDpi = targetDensityDpi;
    }

    public static float dp2px(Context context, float dp) {
        if (context == null || dp <= 0) {
            return 0;
        }
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dp, context.getResources().getDisplayMetrics());
    }

    public static int sp2px(Context context, float sp) {
        if (context == null || sp <= 0) {
            return 0;
        }
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                sp, context.getResources().getDisplayMetrics());
    }

    /**
     * 缩小小数点后的数字
     *
     * @param text
     * @return
     */
    public static CharSequence getFormatText(CharSequence text) {
        if (TextUtils.isEmpty(text)) {
            return "";
        }
        if (!text.toString().contains(".")) {
            return text;
        }
        Spannable wordToSpan = new SpannableStringBuilder(text);
        int dotIndex = text.toString().indexOf(".");
        wordToSpan.setSpan(new AbsoluteSizeSpan(12, true), dotIndex + 1,
                text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        wordToSpan.setSpan(new AbsoluteSizeSpan(12, true), 0,
                1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return wordToSpan;
    }

    public static void showToast(Context context, String content) {
        if (content == null || TextUtils.isEmpty(content)) {
            return;
        }
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }

    /**
     * 透明状态栏
     *
     * @param activity
     */
    public static void transparentStatus(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    /**
     * 秒转化为时间点
     *
     * @param seconds
     * @return
     */
    public static String timeFormat(int seconds) {
        String hour = "", min = "", second = "";
        if (seconds < 60) {
            hour = "00";
            min = "00";
            second = formatTimeRange(seconds);
        } else if (seconds < 60 * 60) {
            hour = "00";
            min = formatTimeRange(seconds / 60);
            second = formatTimeRange(seconds % 60);
        } else {
            hour = String.valueOf(seconds / 60 / 60);
            min = String.valueOf(seconds / 60 % 60);
            second = String.valueOf(seconds / 60 / 60 % 60);
        }
        return hour + ":" + min + ":" + second;
    }

    private static String formatTimeRange(int time) {
        if (time < 10) {
            return "0" + time;
        } else {
            return String.valueOf(time);
        }
    }
}
