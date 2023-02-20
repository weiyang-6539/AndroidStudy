package com.example.avatardemo.widget;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.IntDef;
import androidx.annotation.RequiresApi;

/*
 * @author Yang
 * @since 2023/2/1 14:17
 * @desc 显示头像的View
 */
public class AvatarView extends View {

    @IntDef({Shape.CIRCLE, Shape.RECTANGLE, Shape.TRIANGLE})
    public @interface Shape {
        int CIRCLE = 1;
        int RECTANGLE = 2;
        int TRIANGLE = 3;
    }

    @Shape
    private int shape = Shape.CIRCLE;

    private final Paint mBitmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private Bitmap mBitmap;

    private RectF mBitmapRect;

    private final Matrix mBitmapMatrix = new Matrix();

    private final Matrix mTransformMatrix = new Matrix();

    /**
     * 当前触摸的点数
     */
    private int pointNum = 0;
    //最大的缩放比例
    public static final float SCALE_MAX = 5f;
    private static final float SCALE_MIN = 0.1f;

    private double oldDist = 0;
    private double moveDist = 0;

    private double oldAngle;
    private double moveAngle;
    /**
     * 针对控件的坐标系，即控件左上角为原点
     */
    private double moveX = 0;
    private double moveY = 0;

    private double downX = 0;
    private double downY = 0;
    // 针对屏幕的坐标系，即屏幕左上角为原点
    private double moveRawX = 0;
    private double moveRawY = 0;

    private float mCenterX, mCenterY;
    private float radius;//内部形状外接圆半径
    private float ratio = .6f;

    private final Paint mPathPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private final Path mClipPath = new Path();

    private Path getClipPath() {
        mClipPath.reset();
        if (shape == Shape.CIRCLE) {
            mClipPath.addCircle(mCenterX, mCenterY, radius, Path.Direction.CW);
        } else if (shape == Shape.RECTANGLE) {
            mClipPath.moveTo(mCenterX, mCenterY - radius);
            mClipPath.lineTo(mCenterX - radius, mCenterY);
            mClipPath.lineTo(mCenterX, mCenterY + radius);
            mClipPath.lineTo(mCenterX + radius, mCenterY);
            mClipPath.close();
        } else if (shape == Shape.TRIANGLE) {
            mClipPath.moveTo(mCenterX, mCenterY - radius);
            mClipPath.lineTo(mCenterX - radius * cos(30), mCenterY + radius / 2);
            mClipPath.lineTo(mCenterX + radius * cos(30), mCenterY + radius / 2);
            mClipPath.close();
        }
        return mClipPath;
    }

    public void setShape(@Shape int shape) {
        this.shape = shape;
        invalidate();
    }

    public AvatarView(Context context) {
        super(context);

        mPathPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mPathPaint.setStyle(Paint.Style.FILL);

    }

    public void setBitmap(Bitmap bitmap) {
        this.mBitmap = bitmap;
        mBitmapMatrix.reset();
        RectF mViewRect = new RectF(0, 0, getMeasuredWidth(), getMeasuredHeight());
        mBitmapRect = new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight());
        mBitmapMatrix.setRectToRect(mBitmapRect, mViewRect, Matrix.ScaleToFit.CENTER);
        mBitmapMatrix.mapRect(mBitmapRect);
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int w = MeasureSpec.getSize(widthMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);

        mCenterX = w >> 1;
        mCenterY = h >> 1;

        radius = ratio * w / 2;

        setMeasuredDimension(w, h);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mBitmap == null) {
            return super.onTouchEvent(event);
        }
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                pointNum = 1;
                /*downX = event.getX();
                downY = event.getY();*/
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                //两点按下时的距离
                oldDist = spacing(event);
                oldAngle = standardAngle(event);
                pointNum += 1;
                break;
            case MotionEvent.ACTION_MOVE:
                if (pointNum == 1) {
                    if (downX == 0 && downY == 0) {
                        downX = event.getX();
                        downY = event.getY();
                    }
                    //只有一个手指的时候才有移动的操作
                    float lessX = (float) (event.getX() - downX);
                    float lessY = (float) (event.getY() - downY);
                    moveX = event.getX();
                    moveY = event.getY();
                    moveRawX = event.getRawX();
                    moveRawY = event.getRawY();
                    mBitmapMatrix.postTranslate(lessX, lessY);
                    mBitmapRect.offset(lessX, lessY);
                    downX = event.getX();
                    downY = event.getY();
                    invalidate();
                } else if (pointNum == 2) {

                    //只有2个手指的时候才有放大缩小的操作
                    downX = event.getX();
                    downY = event.getY();
                    moveDist = spacing(event);
                    double space = moveDist - oldDist;
                    float scale = (float) (1.0f + space / oldDist);
                    if (scale > SCALE_MIN && scale < SCALE_MAX) {
                        mBitmapMatrix.postScale(scale, scale, mBitmapRect.centerX(), mBitmapRect.centerY());
                        RectUtil.scaleRect(mBitmapRect, scale);
                    } else if (scale < SCALE_MIN) {
                        mBitmapMatrix.postScale(SCALE_MIN, SCALE_MIN, mBitmapRect.centerX(), mBitmapRect.centerY());
                        RectUtil.scaleRect(mBitmapRect, scale);
                    }
                    oldDist = moveDist;

                    moveAngle = standardAngle(event);
                    mBitmapMatrix.postRotate((float) (moveAngle - oldAngle), mBitmapRect.centerX(), mBitmapRect.centerY());
                    RectUtil.rotateRect(mBitmapRect, mBitmapRect.centerX(), mBitmapRect.centerY(), (float) (moveAngle - oldAngle));
                    oldAngle = moveAngle;

                    invalidate();
                }
                break;
            case MotionEvent.ACTION_POINTER_UP:
                pointNum -= 1;
                if (pointNum == 1) {
                    downX = 0;
                    downY = 0;
                }
                break;
            case MotionEvent.ACTION_UP:
                pointNum = 0;
                downX = 0;
                downY = 0;
                break;
            default:
                break;
        }
        return true;
    }

    /**
     * 计算两个点的距离
     *
     * @param event 事件
     * @return 返回数值
     */
    private double spacing(MotionEvent event) {
        if (event.getPointerCount() == 2) {
            float x = event.getX(0) - event.getX(1);
            float y = event.getY(0) - event.getY(1);
            return Math.sqrt(x * x + y * y);
        } else {
            return 0;
        }
    }

    private double standardAngle(MotionEvent event) {
        if (event.getPointerCount() == 2) {
            float x = event.getX(0) - event.getX(1);
            float y = event.getY(0) - event.getY(1);
            double tan = Math.atan2(y, x);
            double angleA = 180 * tan / Math.PI;
            return angleA;
        } else {
            return 0;
        }
    }

    //最低版本21
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        if (mBitmap != null) {
            canvas.clipPath(getClipPath());
            canvas.drawBitmap(mBitmap, mBitmapMatrix, mBitmapPaint);
            canvas.saveLayer(0, 0, getMeasuredWidth(), getMeasuredHeight(), null);
            canvas.drawPath(mClipPath, mPathPaint);
        }

    }

    /**
     * 计算余弦值
     *
     * @param angle 角度
     * @return
     */
    float cos(float angle) {
        return (float) Math.cos(angle * Math.PI / 180);
    }

    /**
     * 计算正弦值
     *
     * @param angle 角度
     * @return
     */
    float sin(float angle) {
        return (float) Math.sin(angle * Math.PI / 180);
    }

}
