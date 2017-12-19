package com.example.snowson.apptest.view;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.aspsine.swipetoloadlayout.SwipeLoadMoreTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;

/**
 * Created by snowson on 17-12-19.
 */

public class LoadMoreFooterView extends AppCompatTextView
        implements SwipeLoadMoreTrigger, SwipeTrigger {
    public LoadMoreFooterView(Context context) {
        super(context);
    }

    public LoadMoreFooterView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadMoreFooterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onLoadMore() {
        setText("LOADING MORE");
    }

    @Override
    public void onPrepare() {
        setText("");
    }

    @Override
    public void onMove(int yScrolled, boolean isComplete, boolean automatic) {
        if (!isComplete) {
            if (yScrolled <= -getHeight()) {
                setText("RELEASE TO LOAD MORE");
            } else {
                setText("SWIPE TO LOAD MORE");
            }
        } else {
            setText("LOAD MORE RETURNING");
        }
    }

    @Override
    public void onRelease() {
        setText("LOADING MORE");
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
