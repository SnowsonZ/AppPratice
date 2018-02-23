package com.example.snowson.apptest.view;

import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
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
import android.view.MotionEvent;
import android.view.View;

import com.example.snowson.apptest.R;
import com.example.snowson.apptest.utils.ScreenUtils;

/**
 * Created by snowson on 18-2-22.
 * 1. 文字绘制:居中设置文字属性
 * 2. path绘制:arc,circle,triangle
 * 3. canvas几何变换: 倒序变换
 * 4. camera三维变换
 * 5. 动画
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
    private int mCurDirection = 0;
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

    private Paint mDegreeNormalPaint;

    private Paint mDegreeLabelPaint;

    private Paint mDirectionPaint;

    private Paint mDegreeNumPaint;

    private Paint mDirectionNumPaint;
    private float mCameraTranslateX;
    private float mCameraTranslateY;
    //camera最大旋转角度
    private float mMaxCameraRotate = 10;
    private float mCameraRotateX;
    private float mCameraRotateY;
    private ValueAnimator mValueAnimator;
    //camera矩阵
    private Matrix mCameraMatrix;

    //设置camera
    private Camera mCamera;

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
        mCameraMatrix = new Matrix();
        mCamera = new Camera();

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
        mTextPaint.setTextSize(ScreenUtils.sp2px(getContext(), 40));
        mTextPaint.setColor(Color.WHITE);

        mInsideTrianglePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mInsideTrianglePaint.setStyle(Paint.Style.FILL);
        mInsideTrianglePaint.setColor(Color.RED);

        mAnglePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mAnglePaint.setStyle(Paint.Style.STROKE);
        mAnglePaint.setColor(Color.RED);

        mInnerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mInnerPaint.setStyle(Paint.Style.FILL);

        mDegreeNormalPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDegreeNormalPaint.setStyle(Paint.Style.STROKE);
        mDegreeNormalPaint.setStrokeWidth(3);
        mDegreeNormalPaint.setColor(getContext().getResources().getColor(R.color.darkGray));

        mDegreeLabelPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDegreeLabelPaint.setStyle(Paint.Style.STROKE);
        mDegreeLabelPaint.setStrokeWidth(3);
        mDegreeLabelPaint.setColor(Color.WHITE);

        mDirectionPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDirectionPaint.setStyle(Paint.Style.STROKE);
        mDirectionPaint.setColor(Color.WHITE);
        mDirectionPaint.setTextSize(ScreenUtils.sp2px(getContext(), 14));


        mDegreeNumPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDegreeNumPaint.setStyle(Paint.Style.STROKE);
        mDegreeNumPaint.setColor(getContext().getResources().getColor(R.color.darkGray));
        mDegreeNumPaint.setTextSize(ScreenUtils.sp2px(getContext(), 10));

        mDirectionNumPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDirectionNumPaint.setStyle(Paint.Style.STROKE);
        mDirectionNumPaint.setTextSize(ScreenUtils.sp2px(getContext(), 40));
        mDirectionNumPaint.setColor(Color.WHITE);
        mDirectionNumPaint.setFakeBoldText(true);

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
        if (mCurDirection == 0) {
            text = "北";
        } else if (mCurDirection > 0 && mCurDirection < 90) {
            text = "东北";
        } else if (mCurDirection == 90) {
            text = "东";
        } else if (mCurDirection > 90 && mCurDirection < 180) {
            text = "东南";
        } else if (mCurDirection == 180) {
            text = "南";
        } else if (mCurDirection > 180 && mCurDirection < 270) {
            text = "西南";
        } else if (mCurDirection == 270) {
            text = "西";
        } else if (mCurDirection > 270 && mCurDirection < 360) {
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
        mCanvas.rotate(-mCurDirection, mWidth / 2, mTextHeight + mOutSideRadius);
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
        if (mCurDirection >= 5 && 360 - mCurDirection >= 5) {
            if (mCurDirection < 180) {
                resultDirection = mCurDirection;
                mCanvas.drawArc(mInsideRectF, -85, resultDirection - 5, false, mAnglePaint);
            } else {
                resultDirection = 360 - mCurDirection;
                mCanvas.drawArc(mInsideRectF, -95, -resultDirection + 5, false, mAnglePaint);
            }
        }
        mCanvas.restore();
    }

    /**
     * 绘制内层渐变圆形
     */
    private void drawInnerCricle() {
        RadialGradient sg = new RadialGradient(mCenterX, mTextHeight + mOutSideRadius,
                mInsideRadius - 40, Color.parseColor("#323232"),
                Color.parseColor("#000000"), Shader.TileMode.CLAMP);
        mInnerPaint.setShader(sg);
        mCanvas.drawCircle(mWidth / 2, mTextHeight + mOutSideRadius,
                mInsideRadius - 40, mInnerPaint);
    }

    /**
     * 刻度绘制
     */
    private void drawInnerDegree() {
        mCanvas.save();
        mCanvas.rotate(-mCurDirection, mWidth / 2, mTextHeight + mOutSideRadius);
        int degreeHeight;
        Paint paint;
        String direction;
        for (int i = 0; i < 360; i++) {
            if (i == 0 || i == 90 || i == 180 || i == 270) {
                degreeHeight = 20;
                paint = mDegreeLabelPaint;

                if (i == 0) {
                    direction = "N";
                    mDirectionPaint.setColor(Color.RED);
                } else if (i == 90) {
                    direction = "E";
                    mDirectionPaint.setColor(Color.WHITE);
                } else if (i == 180) {
                    direction = "S";
                    mDirectionPaint.setColor(Color.WHITE);
                } else {
                    direction = "W";
                    mDirectionPaint.setColor(Color.WHITE);
                }
                Rect rect = new Rect();
                mDirectionPaint.getTextBounds(direction, 0, 1, rect);
                int width = rect.width();
                int height = rect.height();
                mCanvas.drawText(direction, mWidth / 2 - width / 2, mTextHeight + mOutSideRadius
                        - mInsideRadius + 10 + degreeHeight + height + 10, mDirectionPaint);
            } else {
                degreeHeight = 10;
                paint = mDegreeNormalPaint;

                Rect rect = new Rect();
                String degree = String.valueOf(i);
                if (i == 30 || i == 60 || i == 120 || i == 150 || i == 210 || i == 240
                        || i == 300 || i == 330) {
                    mDegreeNumPaint.getTextBounds(degree, 0, degree.length(), rect);
                    int width = rect.width();
                    int height = rect.height();
                    mCanvas.drawText(degree, mWidth / 2 - width / 2, mTextHeight + mOutSideRadius
                            - mInsideRadius + 10 + degreeHeight + height + 10, mDegreeNumPaint);
                }
            }
            mCanvas.drawLine(mWidth / 2, mTextHeight + mOutSideRadius - mInsideRadius
                    + 10, mWidth / 2, mTextHeight + mOutSideRadius - mInsideRadius
                    + 10 + degreeHeight, paint);
            mCanvas.rotate(1, mWidth / 2, mTextHeight + mOutSideRadius);
        }
        mCanvas.restore();
    }

    /**
     * 绘制当前方位值
     */
    private void drawCurDirectionText() {
        String degree = Math.round(mCurDirection) + "°";
        Rect rect = new Rect();
        mDirectionNumPaint.getTextBounds(degree, 0, degree.length(), rect);
        int width = rect.width();
        int height = rect.height();
        mCanvas.drawText(degree, mWidth / 2 - width / 2, mTextHeight + mOutSideRadius + height / 5,
                mDirectionNumPaint);

    }

    /**
     * 设置camera相关
     */
    private void set3DMetrix() {
        mCameraMatrix.reset();
        mCamera.save();
        mCamera.rotateX(mCameraRotateX);
        mCamera.rotateY(mCameraRotateY);
        mCamera.getMatrix(mCameraMatrix);
        mCamera.restore();
        //camera默认旋转是View左上角为旋转中心
        //所以动作之前要，设置矩阵位置 -mTextHeight-mOutSideRadius
        mCameraMatrix.preTranslate(-getWidth() / 2, -getHeight() / 2);
        //动作之后恢复位置
        mCameraMatrix.postTranslate(getWidth() / 2, getHeight() / 2);
        mCanvas.concat(mCameraMatrix);
    }

    /**
     * 计算camera旋转角度
     *
     * @param event
     */
    private void getCameraRotate(MotionEvent event) {
        float mRotateX = -(event.getY() - (getHeight()) / 2);
        float mRotateY = (event.getX() - getWidth() / 2);
        //求出旋转大小与半径之比
        float[] percentArr = getPercent(mRotateX, mRotateY);
        mCameraRotateX = percentArr[0] * mMaxCameraRotate;
        mCameraRotateY = percentArr[1] * mMaxCameraRotate;
    }

    /**
     * 计算camera平移距离
     *
     * @param event
     */
    private void getCameraTranslate(MotionEvent event) {
        float translateX = event.getX() - getWidth() / 2;
        float translateY = event.getY() - getHeight() / 2;
        //位移大小与半径之比
        float[] percentArr = getPercent(translateX, translateY);
        //最终位移的大小按比例匀称改变
        mCameraTranslateX = percentArr[0] * mMaxCameraTranslate;
        mCameraTranslateY = percentArr[1] * mMaxCameraTranslate;
    }

    /**
     * 计算百分比
     *
     * @param translateX
     * @param translateY
     * @return
     */
    private float[] getPercent(float translateX, float translateY) {
        float[] percentArr = new float[2];
        float percentX = translateX / mWidth;
        float percentY = translateY / mWidth;
        if (percentX > 1) {
            percentX = 1;
        } else if (percentX < -1) {
            percentX = -1;
        }
        if (percentY > 1) {
            percentY = 1;
        } else if (percentY < -1) {
            percentY = -1;
        }
        percentArr[0] = percentX;
        percentArr[1] = percentY;
        return percentArr;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mCanvas = canvas;
        set3DMetrix();
        drawText();
        drawCompassOutSide();
        drawCompassInside();
        drawInnerCricle();
        drawInnerDegree();
        drawCurDirectionText();
    }

    public float getCurDirection() {
        return mCurDirection;
    }

    public void setCurDirection(float curDirection) {
        if (curDirection == 360) {
            curDirection = 0;
        }
        mCurDirection = (int) curDirection;
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (mValueAnimator != null && mValueAnimator.isRunning()) {
                    mValueAnimator.cancel();
                }
                //3D 效果让Camera旋转,获取旋转偏移大小
                getCameraRotate(event);
                //获取平移大小
                getCameraTranslate(event);
                break;
            case MotionEvent.ACTION_MOVE:
                //3D 效果让Camera旋转,获取旋转偏移大小
                getCameraRotate(event);
                //获取平移大小
                getCameraTranslate(event);
                break;
            case MotionEvent.ACTION_UP:
                //松开手 复原动画
                startRestore();
                break;
        }
        return true;
    }

    private void startRestore() {
        final String cameraRotateXName = "cameraRotateX";
        final String cameraRotateYName = "cameraRotateY";
        final String canvasTranslateXName = "canvasTranslateX";
        final String canvasTranslateYName = "canvasTranslateY";
        PropertyValuesHolder cameraRotateXHolder =
                PropertyValuesHolder.ofFloat(cameraRotateXName, mCameraRotateX, 0);
        PropertyValuesHolder cameraRotateYHolder =
                PropertyValuesHolder.ofFloat(cameraRotateYName, mCameraRotateY, 0);
        PropertyValuesHolder canvasTranslateXHolder =
                PropertyValuesHolder.ofFloat(canvasTranslateXName, mCameraTranslateX, 0);
        PropertyValuesHolder canvasTranslateYHolder =
                PropertyValuesHolder.ofFloat(canvasTranslateYName, mCameraTranslateY, 0);
        mValueAnimator = ValueAnimator.ofPropertyValuesHolder(cameraRotateXHolder,
                cameraRotateYHolder, canvasTranslateXHolder, canvasTranslateYHolder);
        mValueAnimator.setInterpolator(new TimeInterpolator() {
            @Override
            public float getInterpolation(float input) {
                float f = 0.571429f;
                float result = (float) (Math.pow(2, -2 * input) * Math.sin((input - f / 4) * (2 * Math.PI) / f) + 1);
//                Logger.d(result);
                return result;
            }
        });
        mValueAnimator.setDuration(1000);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCameraRotateX = (float) animation.getAnimatedValue(cameraRotateXName);
                mCameraRotateY = (float) animation.getAnimatedValue(cameraRotateYName);
                mCameraTranslateX = (float) animation.getAnimatedValue(canvasTranslateXName);
                mCameraTranslateY = (float) animation.getAnimatedValue(canvasTranslateYName);
            }
        });
        mValueAnimator.start();
    }
}
