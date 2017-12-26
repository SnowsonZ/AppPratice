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

    @Override
    public void transformPage(View page, float position) {
        Log.i(this.getClass().getName(), "scaleX:" + page.getScaleX() +"width: " + page.getWidth() + "position: " + position);
        int width = page.getWidth();
        int height = page.getHeight();
        page.setPivotX(width / 2);
        page.setPivotY(height / 2);

        if (position < -1) {
            page.setScaleX(MIN_SCALE);
            page.setScaleY(MIN_SCALE);
            page.setPivotX(width);
            page.setAlpha(MIN_ALPHA);
        } else if (position <= 1) {
            float scale = MIN_SCALE + (1 - Math.abs(position)) * (1 - MIN_SCALE);
            page.setScaleX(scale);
            page.setScaleY(scale);
            page.setPivotX(width * ((1 - position) * 0.5f));
            page.setAlpha(MIN_ALPHA + (1 - Math.abs(position)) * (1 - MIN_ALPHA));
        } else {
            page.setScaleX(MIN_SCALE);
            page.setScaleY(MIN_SCALE);
            page.setPivotX(0);
            page.setAlpha(MIN_ALPHA);
        }

    }
}
