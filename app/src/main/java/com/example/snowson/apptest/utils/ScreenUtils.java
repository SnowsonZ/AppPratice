package com.example.snowson.apptest.utils;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.util.TypedValue;
import android.widget.Toast;

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

    /**
     * 缩小小数点后的数字
     * @param text
     * @return
     */
    public static CharSequence getFormatText(CharSequence text) {
        if(TextUtils.isEmpty(text)) {
            return "";
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
        if(content == null || TextUtils.isEmpty(content)) {
            return;
        }
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }
}
