package com.example.snowson.apptest.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by snowson on 17-12-19.
 */

public class ScreenUtils {

    public static float dp2px(Context context, float dp) {
        if (context == null || dp <= 0) {
            return 0;
        }
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dp, context.getResources().getDisplayMetrics());
    }

    public static float sp2px(Context context, float sp) {
        if (context == null || sp <= 0) {
            return 0;
        }
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                sp, context.getResources().getDisplayMetrics());
    }
}
