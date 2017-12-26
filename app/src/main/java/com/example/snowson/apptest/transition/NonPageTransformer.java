package com.example.snowson.apptest.transition;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * <h3>Description:  </h3>
 * <b>Notes:</b> Created by lpc on 2017/12/21.<br />
 */

public class NonPageTransformer implements ViewPager.PageTransformer
{
    @Override
    public void transformPage(View page, float position)
    {
        page.setScaleX(0.999f);//hack
    }

    public static final ViewPager.PageTransformer INSTANCE = new NonPageTransformer();
}