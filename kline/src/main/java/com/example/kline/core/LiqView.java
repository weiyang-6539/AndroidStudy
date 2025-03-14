package com.example.kline.core;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

/**
 * @author yang
 * @date 2025/3/12
 * @desc
 */
public class LiqView extends View implements ScaleGestureDetector.OnScaleGestureListener, GestureDetector.OnGestureListener {
    private float scaleFactor = 1f;
    private final float maxScale = 20f;
    private final float minScale = 1f;

    private float translateX = 0f;
    private float translateY = 0f;

    private final Paint mCandlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final float mCandleWidth = .5f;
    private final RectF tempRect = new RectF();
    private final int colorIncrease = Color.parseColor("#d84532");
    private final int colorDecrease = Color.parseColor("#4a7c21");

    private final ScaleGestureDetector scaleDetector;
    private final GestureDetector gestureDetector;

    private final Matrix matrix = new Matrix(); // 图片的缩放矩阵
    private final Rect dstRect = new Rect();
    private final DataSetObserver mDataSetObserver = new DataSetObserver() {
        @Override
        public void onChanged() {
            requestLayout();
        }

        @Override
        public void onInvalidated() {
            invalidate();
        }
    };

    private KLineAdapter adapter;

    public void setAdapter(KLineAdapter adapter) {
        if (this.adapter != null && mDataSetObserver != null) {
            this.adapter.unregisterDataSetObserver(mDataSetObserver);
        }
        this.adapter = adapter;
        if (adapter != null && mDataSetObserver != null) {
            adapter.registerDataSetObserver(mDataSetObserver);
        }
    }

    public LiqView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        scaleDetector = new ScaleGestureDetector(context, this);
        gestureDetector = new GestureDetector(context, this);

        mCandlePaint.setStyle(Paint.Style.FILL);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        scaleDetector.onTouchEvent(event);
        gestureDetector.onTouchEvent(event);
        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = (int) (width / adapter.getRatio());

        // 设置图片缩放
        IData data = adapter.getData();
        float scale = 1f * width / data.getXSize();
        matrix.setScale(scale, scale);

        dstRect.set(0, 0, width, height);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.parseColor("#3e0b50"));

        canvas.save();

        // 应用缩放
        canvas.scale(scaleFactor, scaleFactor, getWidth() / 2f, getHeight() / 2f);

        // 处理拖动
        canvas.translate(translateX / scaleFactor, translateY / scaleFactor);

        // 绘制bitmap
        canvas.drawBitmap(adapter.getBitmap(), null, dstRect, null);

        // 绘制烛状图
        ArrayList<ArrayList<Number>> xData = adapter.getData().getXData();
        for (int i = 0; i < xData.size(); i++) {
            int x = getAxisX(i);
            ArrayList<Number> candle = xData.get(i);
            int openY = getAxisY(candle.get(1).doubleValue());
            int highY = getAxisY(candle.get(2).doubleValue());
            int lowY = getAxisY(candle.get(3).doubleValue());
            int closeY = getAxisY(candle.get(4).doubleValue());

            if (openY > closeY) {//涨(这里比较的y坐标值)
                drawCandle(canvas, x, openY, closeY, true);
            } else if (openY < closeY) {
                drawCandle(canvas, x, openY, closeY, false);
            } else {
                drawCandle(canvas, x, closeY - 1, closeY, true);
            }

            logD("x:" + x + " openY:" + openY + " closeY" + closeY);
        }

        canvas.restore();
    }

    /**
     * 绘制蜡烛矩形
     */
    public void drawCandle(Canvas canvas, float right, float top, float bottom, boolean isIncrease) {
        tempRect.set(right - mCandleWidth * scaleFactor / 2, top, right, bottom);
        mCandlePaint.setColor(isIncrease ? colorIncrease : colorDecrease);
        canvas.drawRect(tempRect, mCandlePaint);
    }

    @Override
    public boolean onDown(@NonNull MotionEvent e) {
        return true;
    }

    @Override
    public void onShowPress(@NonNull MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(@NonNull MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(@Nullable MotionEvent e1, @NonNull MotionEvent e2, float distanceX, float distanceY) {
        if (scaleFactor >= 1) { // 只有缩放大于1时才允许拖动
            translateX -= distanceX;
            translateY -= distanceY;

            float maxTranslateX = getWidth() * (scaleFactor - 1) / 2;
            float maxTranslateY = getHeight() * (scaleFactor - 1) / 2;

            translateX = Math.max(-maxTranslateX, Math.min(translateX, maxTranslateX));
            translateY = Math.max(-maxTranslateY, Math.min(translateY, maxTranslateY));

            invalidate();
        }
        return true;
    }

    @Override
    public void onLongPress(@NonNull MotionEvent e) {

    }

    @Override
    public boolean onFling(@Nullable MotionEvent e1, @NonNull MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public boolean onScale(@NonNull ScaleGestureDetector detector) {
        float newScale = scaleFactor * detector.getScaleFactor();
        scaleFactor = Math.max(minScale, Math.min(newScale, maxScale));
        invalidate();
        return true;
    }

    @Override
    public boolean onScaleBegin(@NonNull ScaleGestureDetector detector) {
        return true;
    }

    @Override
    public void onScaleEnd(@NonNull ScaleGestureDetector detector) {

    }

    private int getAxisX(int i) {
        return (int) ((i + 1) * 1f * getWidth() / adapter.getData().getXSize());
    }

    private int getAxisY(double value) {
        ArrayList<Double> yData = adapter.getData().getYData();
        double minValue = yData.get(0);
        double maxValue = yData.get(yData.size() - 1);

        float scaleY = (float) ((-getHeight()) / (maxValue - minValue));

        return (int) ((maxValue - value) * scaleY + getHeight());
    }

    private void logD(String log) {
        Log.d("LiqView", log);
    }
}
