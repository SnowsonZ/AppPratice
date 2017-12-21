package com.example.snowson.apptest.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.example.snowson.apptest.R;
import com.example.snowson.apptest.utils.ScreenUtils;

/**
 * Created by snowson on 17-12-20.
 *  暂时无用，留作例子参考
 */

public class RoundImageButton extends View {
    private Paint paint;
    private String mText;
    private int mDrawable;
    //    private Bitmap bitmap;
    private Path path;
    private RectF rectF;
    private float mTextSize; //unit sp
    private int mDefPadding = 10; //unit dp
    private float mRadius = 0;

    public RoundImageButton(Context context) {
        super(context);
        init();
    }

    public RoundImageButton(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundImageButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.roundImageButton);
        mDrawable = ta.getResourceId(R.styleable.roundImageButton_src, 0);
        mText = ta.getString(R.styleable.roundImageButton_text);
        mTextSize = ta.getDimension(R.styleable.roundImageButton_textSize, 0);
        mRadius = ta.getDimension(R.styleable.roundImageButton_radius, 0);
        ta.recycle();
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public RoundImageButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.roundImageButton);
        mDrawable = ta.getInt(R.styleable.roundImageButton_src, -1);
        mText = ta.getString(R.styleable.roundImageButton_text);
        mTextSize = ta.getDimension(R.styleable.roundImageButton_textSize, 0);
        ta.recycle();
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(mTextSize);
        path = new Path();
        if (mDrawable != 0) {
//            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.selected_info);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = 0, height = 0;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        switch (widthMode) {
            case MeasureSpec.EXACTLY:
                width = widthSize;
                break;
            case MeasureSpec.UNSPECIFIED:
                width = getMeasuredWidth();
                break;
            case MeasureSpec.AT_MOST:
                width = (int) (paint.measureText(mText));
                break;
        }

        switch (heightMode) {
            case MeasureSpec.EXACTLY:
                height = heightSize;
                break;
            case MeasureSpec.UNSPECIFIED:
                height = getMeasuredHeight();
                break;
            case MeasureSpec.AT_MOST:
                height = (int) (paint.descent() - paint.ascent());
                break;
        }
        int padding = (int) ScreenUtils.dp2px(getContext(), mDefPadding);
        setPadding(padding, padding, padding, padding);
        setMeasuredDimension(width + getPaddingLeft() + getPaddingRight(),
                height + getPaddingTop() + getPaddingBottom());

        rectF = new RectF(0, 0, getMeasuredWidth(), getMeasuredHeight());
        path.addRoundRect(rectF, mRadius, mRadius, Path.Direction.CCW);
    }

    @Override
    public void draw(Canvas canvas) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);
        paint.setColor(Color.BLACK);
        canvas.clipPath(path);
        canvas.drawPath(path, paint);
        super.draw(canvas);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawBitmap(bitmap, null, rectF, paint);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(mText, canvas.getWidth() / 2,
                canvas.getHeight() / 2 - paint.ascent() / 2
                        - (paint.ascent() - paint.getFontMetrics().top), paint);
    }
}
