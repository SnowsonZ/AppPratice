package com.example.snowson.apptest.fragment;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;

/**
 * Created by snowson on 17-12-15.
 */

public class BannerItem extends CardView {

    private CardView mBannerItem;

    public BannerItem(Context context) {
        super(context);
    }

    public BannerItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BannerItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void setContent(int resId) {
        mBannerItem.setBackgroundResource(resId);
    }
}
