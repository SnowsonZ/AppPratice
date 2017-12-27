package com.example.snowson.apptest.viewholder;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.view.View;

import com.example.snowson.apptest.utils.ScreenUtils;

/**
 * Created by snowson on 17-12-27.
 */

public class BannerHolderView implements TypeHolder<String> {
    private CardView cardView;

    @Override
    public View createView(Context context) {
        cardView = new CardView(context);
        return cardView;
    }

    @Override
    public void bindView(Context context, String resId) {
        cardView.setCardBackgroundColor(Color.parseColor(resId));
        cardView.setRadius(ScreenUtils.dp2px(context, 10));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cardView.setElevation(ScreenUtils.dp2px(context, 7));
        }
    }
}
