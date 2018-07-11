package com.example.snowson.apptest.transition;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

/**
 * Created by snowson on 17-12-26.
 */

public class ZoomOutPageTransformer implements ViewPager.PageTransformer {

    private static final float MAX_SCALE = 1;
    private static final float MIN_SCALE = 0.6f;
    private static final float MIN_ALPHA = 0.5f;

    @Override
    public void transformPage(View view, float position) {
        Log.i(this.getClass().getName(), "view.color: " + view.getDrawingCacheBackgroundColor() + "; position: " + position);
        int pageWidth = view.getWidth();
        int pageHeight = view.getHeight();

        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.setAlpha(0);

        } else if (position <= 1) { // [-1,1]
            // Modify the default slide transition to shrink the page as well
            float scaleFactor = Math.max(MIN_SCALE, MAX_SCALE - Math.abs(position) * MAX_SCALE);
            float vertMargin = pageHeight * (MAX_SCALE - scaleFactor) / 2;
            float horzMargin = pageWidth * (MAX_SCALE - scaleFactor) / 2;
            if (position < 0) {
                view.setTranslationX(horzMargin - vertMargin / 2);
            } else {
                view.setTranslationX(-horzMargin + vertMargin / 2);
            }

            // Scale the page down (between MIN_SCALE and 1)
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);

            // Fade the page relative to its size.
            view.setAlpha(MIN_ALPHA +
                    (scaleFactor - MIN_SCALE) /
                            (MAX_SCALE - MIN_SCALE) * (1 - MIN_ALPHA));

        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            view.setAlpha(0);
        }
    }
}
