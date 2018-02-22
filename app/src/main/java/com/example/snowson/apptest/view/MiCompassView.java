package com.example.snowson.apptest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.example.snowson.apptest.R;

/**
 * Created by snowson on 18-2-22.
 */

public class MiCompassView extends View {

    //view的宽度
    private int mWidth;
    //方位文字尺寸
    private int mTextHeight;
    //圆心点坐标
    private int mCenterX;
    private int mCenterY;
    //外部圆的半径
    private int mOutSideRadius;
    //内层圆的半径
    private int mInsideRadius;
    //camera最大平移距离
    private float mMaxCameraTranslate;
    //当前方位
    private float mCurDirection = 0;
    private Paint mTextPaint;
    private Rect mTextRect;
    private Canvas mCanvas;
    private Path mOutsideTriangle;
    private Paint mOutSidetrianglePaint;
    private Paint mDarkRedPaint;
    private Paint mLightGrayPaint;
    private Paint mDeepGrayPaint;
    private RectF mOutSideRectF;
    //内层三角形path
    private Path mInsideTriangle;
    //内层三角形paint
    private Paint mInsideTrianglePaint;
    //内层圆rectF
    private RectF mInsideRectF;

    //旋转的轨迹paint
    private Paint mAnglePaint;

    //中心圆paint
    private Paint mInnerPaint;

    public MiCompassView(Context context) {
        super(context);
        init();
    }

    public MiCompassView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MiCompassView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MiCompassView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        mTextRect = new Rect();
        mCanvas = new Canvas();
        mOutsideTriangle = new Path();
        mInsideTriangle = new Path();

        mOutSidetrianglePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mOutSidetrianglePaint.setStyle(Paint.Style.FILL);
        mOutSidetrianglePaint.setColor(getContext().getResources().getColor(R.color.darkGray));

        mDarkRedPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDarkRedPaint.setStyle(Paint.Style.STROKE);
        mDarkRedPaint.setColor(getContext().getResources().getColor(R.color.darkRed));

        mLightGrayPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLightGrayPaint.setStyle(Paint.Style.STROKE);
        mLightGrayPaint.setColor(getContext().getResources().getColor(R.color.lightGray));

        mDeepGrayPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDeepGrayPaint.setStyle(Paint.Style.STROKE);
        mDeepGrayPaint.setColor(getContext().getResources().getColor(R.color.darkGray));

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setStyle(Paint.Style.STROKE);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(80);
        mTextPaint.setColor(Color.WHITE);

        mInsideTrianglePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mInsideTrianglePaint.setStyle(Paint.Style.FILL);
        mInsideTrianglePaint.setColor(Color.RED);

        mAnglePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mAnglePaint.setStyle(Paint.Style.STROKE);
        mAnglePaint.setColor(Color.RED);

        mInnerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mInnerPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        mWidth = Math.min(widthSize, heightSize);
        if (widthMode == MeasureSpec.UNSPECIFIED) {
            mWidth = widthSize;
        } else if (heightMode == MeasureSpec.UNSPECIFIED) {
            mWidth = heightSize;
        }
        mTextHeight = mWidth / 3;
        mCenterX = mWidth / 2;
        mCenterY = mWidth / 2 + mTextHeight;
        mOutSideRadius = mWidth * 3 / 8;
        mInsideRadius = mOutSideRadius * 4 / 5;
        mMaxCameraTranslate = 0.02f * mOutSideRadius;
        setMeasuredDimension(mWidth, mWidth + mWidth / 3);
    }

    private void drawText() {
        String text = "北";

        if (mCurDirection <= 15 || mCurDirection >= 345) {
            text = "北";
        } else if (mCurDirection > 15 && mCurDirection <= 75) {
            text = "东北";
        } else if (mCurDirection > 75 && mCurDirection <= 105) {
            text = "东";
        } else if (mCurDirection > 105 && mCurDirection <= 165) {
            text = "东南";
        } else if (mCurDirection > 165 && mCurDirection <= 195) {
            text = "南";
        } else if (mCurDirection > 195 && mCurDirection <= 255) {
            text = "西南";
        } else if (mCurDirection > 255 && mCurDirection <= 285) {
            text = "西";
        } else if (mCurDirection > 285 && mCurDirection < 345) {
            text = "西北";
        }
        mTextPaint.getTextBounds(text, 0, text.length(), mTextRect);
        int textWidth = mTextRect.width();
        mCanvas.drawText(text,
                mWidth / 2 - textWidth / 2, mTextHeight / 2, mTextPaint);
    }

    /**
     * 绘制外层圆
     */
    private void drawCompassOutSide() {
        mCanvas.save();
        //小三角形的高度
        int triangleHeight = 40;
        mOutsideTriangle.moveTo(mWidth / 2, mTextHeight - triangleHeight);
        //小三角形边长
        float mTriangleSide = 46.18f;
        //绘制三角形
        mOutsideTriangle.lineTo(mWidth / 2 - mTriangleSide / 2, mTextHeight);
        mOutsideTriangle.lineTo(mWidth / 2 + mTriangleSide / 2, mTextHeight);
        mOutsideTriangle.close();
        mCanvas.drawPath(mOutsideTriangle, mOutSidetrianglePaint);

        //绘制外层圆弧
        mDarkRedPaint.setStrokeWidth((float) 5);
        mLightGrayPaint.setStrokeWidth((float) 5);
        mDeepGrayPaint.setStrokeWidth((float) 5);
        mLightGrayPaint.setStyle(Paint.Style.STROKE);
        mOutSideRectF = new RectF(mWidth / 2 - mOutSideRadius, mTextHeight,
                mWidth / 2 + mOutSideRadius, mTextHeight + mOutSideRadius * 2);
        mCanvas.drawArc(mOutSideRectF, -80, 120, false, mDeepGrayPaint);
        mCanvas.drawArc(mOutSideRectF, 40, 20, false, mLightGrayPaint);
        mCanvas.drawArc(mOutSideRectF, -100, -20, false, mDeepGrayPaint);
        mCanvas.drawArc(mOutSideRectF, -120, -120, false, mDarkRedPaint);
        mCanvas.restore();
    }

    //绘制内层圆
    private void drawCompassInside() {
        mCanvas.save();
        //内层小三角形的高
        int triangleHeight = (mOutSideRadius - mInsideRadius) / 2;
        mCanvas.rotate(mCurDirection, mWidth / 2, mTextHeight + mOutSideRadius);
        mInsideTriangle.moveTo(mWidth / 2, mTextHeight + triangleHeight);
        //内层小三角形边长
        float triangleSlide = (float) (triangleHeight * 2 / Math.sqrt(3));
        //绘制内层三角形
        mInsideTriangle.lineTo(mWidth / 2 - triangleSlide / 2, mTextHeight + 2 * triangleHeight);
        mInsideTriangle.lineTo(mWidth / 2 + triangleSlide / 2, mTextHeight + 2 * triangleHeight);
        mInsideTriangle.close();
        mCanvas.drawPath(mInsideTriangle, mInsideTrianglePaint);

        //绘制内层圆弧
        mInsideRectF = new RectF(mWidth / 2 - mInsideRadius, mTextHeight + mOutSideRadius - mInsideRadius,
                mWidth / 2 + mInsideRadius, mTextHeight + mOutSideRadius + mInsideRadius);
        mCanvas.drawArc(mInsideRectF, -85, 350, false, mDeepGrayPaint);
        //绘制旋转轨迹.
        mAnglePaint.setStrokeWidth(5);
        float resultDirection;
        if (mCurDirection < 180) {
            resultDirection = mCurDirection;
            mCanvas.drawArc(mInsideRectF, -95, -resultDirection, false, mAnglePaint);
        } else {
            resultDirection = 360 - mCurDirection;
            mCanvas.drawArc(mInsideRectF, -85, resultDirection, false, mAnglePaint);
        }

    }

    private void drawInnerCricle() {
        RadialGradient sg = new RadialGradient(mCenterX, mTextHeight + mOutSideRadius,
                mInsideRadius - 40, Color.parseColor("#323232"),
                Color.parseColor("#000000"), Shader.TileMode.CLAMP);
        mInnerPaint.setShader(sg);
        mCanvas.drawCircle(mWidth / 2, mTextHeight + mOutSideRadius,
                mInsideRadius - 40, mInnerPaint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mCanvas = canvas;
        drawText();
        drawCompassOutSide();
        drawCompassInside();
        drawInnerCricle();
    }

    public float getCurDirection() {
        return mCurDirection;
    }

    public void setCurDirection(float mCurDirection) {
        this.mCurDirection = mCurDirection;
        invalidate();
    }
}
