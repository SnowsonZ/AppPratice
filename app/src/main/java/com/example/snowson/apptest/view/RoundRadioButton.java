package com.example.snowson.apptest.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.AttributeSet;

import com.example.snowson.apptest.R;

/**
 * Created by snowson on 17-12-21.
 * 自定义实现制定背景图片的圆角RadioButton
 */

public class RoundRadioButton extends AppCompatRadioButton {

    private Path mPath;
    private Paint mPaint;
    private float mRadius;

    public RoundRadioButton(Context context) {
        super(context);
        init();
    }

    public RoundRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.roundImageButton);
        mRadius = ta.getDimension(R.styleable.roundImageButton_radius, 0);
        init();
    }

    public RoundRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPath = new Path();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(2);
        mPaint.setColor(Color.BLACK);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        RectF mRectF = new RectF(0, 0, getMeasuredWidth(), getMeasuredHeight());
        mPath.addRoundRect(mRectF, mRadius, mRadius, Path.Direction.CCW);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.clipPath(mPath);
        super.draw(canvas);
    }
}
