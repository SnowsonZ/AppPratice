package com.example.snowson.apptest.view;

import android.content.Context;
import android.util.AttributeSet;

import com.aspsine.swipetoloadlayout.SwipeRefreshTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;

/**
 * Created by snowson on 17-12-19.
 */

public class RefreshHeaderView extends
        android.support.v7.widget.AppCompatTextView implements SwipeRefreshTrigger, SwipeTrigger {

    public RefreshHeaderView(Context context) {
        super(context);
    }

    public RefreshHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RefreshHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onRefresh() {
        setText("REFRESHING");
    }

    @Override
    public void onPrepare() {
        setText("PREPARE");
    }

    @Override
    public void onMove(int yScrolled, boolean isComplete, boolean automatic) {
        if (!isComplete) {
            if (yScrolled > getHeight()) {
                setText("RELEASE TO REFRESH");
            } else {
                setText("SWIPE TO REFRESH");
            }
        } else {
            setText("REFRESH RETURNING");
        }
    }

    @Override
    public void onRelease() {

    }

    @Override
    public void onComplete() {
        setText("COMPLETE");
    }

    @Override
    public void onReset() {
        setText("");
    }
}
