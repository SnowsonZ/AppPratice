package com.example.snowson.apptest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.orhanobut.logger.Logger;

/**
 * Created by Administrator on 2018/4/10.
 */

public class ViewMeasureTestView extends View {
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public ViewMeasureTestView(Context context) {
        super(context);
    }

    public ViewMeasureTestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewMeasureTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ViewMeasureTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int wMode = MeasureSpec.getMode(widthMeasureSpec);
        int wSize = MeasureSpec.getSize(widthMeasureSpec);
        int hMode = MeasureSpec.getMode(heightMeasureSpec);
        int hSize = MeasureSpec.getSize(heightMeasureSpec);
        Logger.e("wMode : " + Integer.toBinaryString(wMode) + "\nwSize : " + wSize);
        Logger.e("hMode : " + Integer.toBinaryString(hMode) + "\nhSize : " + hSize);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(200, 300, 100, mPaint);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Logger.e("onTouchEvent occur");
        return super.onTouchEvent(event);
    }

}
