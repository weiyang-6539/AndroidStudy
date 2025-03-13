package com.example.kline.core;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author yang
 * @date 2025/3/12
 * @desc
 */
public class LiqView extends View implements ScaleGestureDetector.OnScaleGestureListener, GestureDetector.OnGestureListener {
    private float scaleFactor = 20.0f;
    private final float maxScale = 20.0f;
    private final float minScale = 1.0f;

    private float translateX = 0f;
    private float translateY = 0f;

    private final ScaleGestureDetector scaleDetector;
    private final GestureDetector gestureDetector;

    private ScaleGestureDetector scaleGestureDetector;

    private static final int INVALID_POINTER_ID = -1;

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


        canvas.restore();
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

    private void logD(String log) {
        Log.d("LiqView", log);
    }
}
