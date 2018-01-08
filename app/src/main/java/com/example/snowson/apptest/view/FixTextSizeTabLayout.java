package com.example.snowson.apptest.view;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by snowson on 18-1-8.
 */

public class FixTextSizeTabLayout extends TabLayout {
    public FixTextSizeTabLayout(Context context) {
        super(context);
    }

    public FixTextSizeTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FixTextSizeTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setTabPadding();
    }

    /**
     * 清除tab的内间距，避免因为固定内间距导致长的文字被压缩
     * 但是在字体大的时候会明显看出tab之间的间距不一致。所以用这个方法将tab文字变成大小一致后，字体大小酌情设置。
     */
    public void setTabPadding() {
        LinearLayout ll_tab = (LinearLayout) getChildAt(0);
        if(ll_tab == null) {
            return;
        }
        for (int i = 0; i < ll_tab.getChildCount(); i++) {
            View child = ll_tab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0,
                    LinearLayout.LayoutParams.MATCH_PARENT, 1);
            child.setLayoutParams(params);
        }
    }
}
