package com.example.snowson.apptest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;

import com.example.snowson.apptest.utils.ScreenUtils;

/**
 * Created by snowson on 17-12-20.
 */

public class RoundImageButtonWidthText extends android.support.v7.widget.AppCompatImageButton {
    RectF rectF;
    private Path path;
    private Paint paint;
    String content = "电子看板";

    public RoundImageButtonWidthText(Context context) {
        super(context);
        init();
    }

    public RoundImageButtonWidthText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RoundImageButtonWidthText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        path = new Path();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        rectF = new RectF(0, 0, getMeasuredWidth(), getMeasuredHeight());
        path.addRoundRect(rectF, 60, 60, Path.Direction.CCW);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.reset();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        paint.setColor(Color.BLACK);
        canvas.clipPath(path);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.YELLOW);
        canvas.drawRect(rectF, paint);
        super.onDraw(canvas);
        paint.reset();
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        paint.setColor(Color.BLACK);
        paint.setTextSize(ScreenUtils.sp2px(getContext(), 16));
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(content, canvas.getWidth() / 2, canvas.getHeight() / 2 + 10, paint);
    }
}
