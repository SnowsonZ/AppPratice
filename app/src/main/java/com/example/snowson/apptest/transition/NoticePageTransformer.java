package com.example.snowson.apptest.transition;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

/**
 * Created by snowson on 17-12-26.
 */

public class NoticePageTransformer implements ViewPager.PageTransformer {

    public static final float MIN_SCALE = 0.85f;
    public static final float MIN_ALPHA = 0.5f;
    public static final float DEFAULT_CENTER = 0.5f;

    @Override
    public void transformPage(View page, float position) {
        if (position < -1 || position > 1) {
            page.setScaleX(MIN_SCALE);
            page.setScaleY(MIN_SCALE);
            page.setAlpha(MIN_ALPHA);
        } else if (position <= 1) {
            float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position) * (1 - MIN_SCALE));
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
            page.setAlpha(Math.max(MIN_ALPHA, 1 - Math.abs(position) * (1 - MIN_ALPHA)));
        }
        Log.i(this.getClass().getName(), "scaleX:" + page.getScaleX() + "width: " + page.getWidth() + "position: " + position);
    }
}
