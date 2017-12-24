package com.example.snowson.apptest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * Created by snowson on 17-12-21.
 */

public class CircleImageView extends AppCompatImageView {

    private Path mPath;

    public CircleImageView(Context context) {
        super(context);
        init();
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPath = new Path();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int diameter = getMeasuredWidth() > getMeasuredHeight() ?
                getMeasuredHeight() : getMeasuredWidth();
        setMeasuredDimension(diameter, diameter);

        mPath.addCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2,
                diameter / 2, Path.Direction.CCW);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.clipPath(mPath);
        super.onDraw(canvas);
    }
}
