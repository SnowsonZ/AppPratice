package com.example.snowson.apptest.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
 */

public class RoundImageButton extends View {
    private Paint paint;
    private String mText;
    private int mDrawable;
    private Bitmap bitmap;
    private Path path;
    private RectF rectF;

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
        ta.recycle();
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public RoundImageButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.roundImageButton);
        mDrawable = ta.getInt(R.styleable.roundImageButton_src, -1);
        mText = ta.getString(R.styleable.roundImageButton_text);
        ta.recycle();
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        path = new Path();
        if (mDrawable != 0) {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.webwxgetmsgimg);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
//        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
//        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
//        switch (widthMode)
        rectF = new RectF(0, 0, getMeasuredWidth(), getMeasuredHeight());
        path.addRoundRect(rectF, 20, 20, Path.Direction.CCW);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(canvas.getWidth() / 2, canvas.getHeight() / 2);
        canvas.clipPath(path);
        canvas.drawBitmap(bitmap, (canvas.getWidth() - bitmap.getWidth()) / 2,
                (canvas.getHeight() - bitmap.getHeight()) / 2, paint);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(ScreenUtils.sp2px(getContext(), 20));
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        canvas.drawText(mText, 0, 0, paint);
    }
}
