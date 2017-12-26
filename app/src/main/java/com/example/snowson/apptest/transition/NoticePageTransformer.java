package com.example.snowson.apptest.transition;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by snowson on 17-12-26.
 */

public class NoticePageTransformer implements ViewPager.PageTransformer {

    public static final float MIN_SCALE = 0.85f;
    public static final float MIN_ALPHA = 0.5f;

    @Override
    public void transformPage(View page, float position) {

        if (position < -1) {
            page.setScaleX(MIN_SCALE);
            page.setAlpha(0);
        } else if (position < 1) {
            float scale = MIN_SCALE + (1 - Math.abs(position)) * MIN_SCALE;
            page.setScaleX(scale);
            page.setScaleY(scale);
            page.setAlpha(MIN_ALPHA + (1 - Math.abs(position)) * MIN_ALPHA);
        } else {
            page.setScaleX(MIN_SCALE);
            page.setAlpha(0);
        }

    }
}
