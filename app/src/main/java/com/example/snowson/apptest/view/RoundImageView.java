package com.example.snowson.apptest.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.example.snowson.apptest.R;

/**
 * Created by snowson on 17-12-21.
 */

public class RoundImageView extends AppCompatImageView {

    private Path mPath;
    private float mRadius;
    private RectF rectF;

    public RoundImageView(Context context) {
        this(context, null);
    }

    public RoundImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RoundImageView);
        mRadius = ta.getDimension(R.styleable.RoundImageView_radius, 0);
        ta.recycle();
        init();
    }

    private void init() {
        mPath = new Path();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(Math.min(getMeasuredWidth(), getMaxWidth()),
                Math.min(getMeasuredHeight(), getMaxHeight()));

        rectF = new RectF(0, 0, getMeasuredWidth(), getMeasuredHeight());

        mPath.addRoundRect(rectF, mRadius, mRadius, Path.Direction.CCW);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.clipPath(mPath);
        super.onDraw(canvas);
    }
}
