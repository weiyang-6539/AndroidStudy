package com.example.kline.core;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.ArrayList;

/**
 * @author yang
 * @date 2025/3/11
 * @desc
 */
public class KLineView extends ScrollAndScaleView {
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

    private final Matrix matrix = new Matrix(); // 图片的缩放矩阵
    private final Paint mCandlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final float mCandleWidth = 1f;
    private final RectF tempRect = new RectF();
    private final int colorIncrease = Color.parseColor("#d84532");
    private final int colorDecrease = Color.parseColor("#4a7c21");

    public KLineView(Context context) {
        super(context);
    }

    public KLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public KLineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int getMinScrollX() {
        return 0;
    }

    @Override
    public int getMaxScrollX() {
        return (int) (getWidth() / mScale - getWidth());
    }

    @Override
    public int getMinScrollY() {
        return 0;
    }

    @Override
    public int getMaxScrollY() {
        return (int) (getHeight() / mScale - getHeight());
    }

    @Override
    public float getScaleMax() {
        return 1f;
    }

    @Override
    public float getScaleMin() {
        return .05f;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = (int) (width / adapter.getRatio());

        // 设置图片缩放
        IData data = adapter.getData();
        float scale = (getScaleMax() / getScaleMin()) * width / data.getXSize();
        matrix.setScale(scale, scale);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        canvas.drawColor(Color.parseColor("#3e0b50"));

        canvas.drawBitmap(adapter.getBitmap(), matrix, null);

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

    }

    /**
     * 绘制蜡烛矩形
     */
    public void drawCandle(Canvas canvas, float right, float top, float bottom, boolean isIncrease) {
        tempRect.set(right - 20f * getWidth() / adapter.getData().getXSize() / 2, top, right, bottom);
        mCandlePaint.setColor(isIncrease ? colorIncrease : colorDecrease);
        canvas.drawRect(tempRect, mCandlePaint);
    }

    @Override
    protected void onScaleChanged(float scale, float oldScale) {
        // 设置图片缩放
        IData data = adapter.getData();
        float newScale = getScaleMax() / getScaleMin() * getWidth() / data.getXSize() * scale;
        matrix.setScale(newScale, newScale);

        invalidate();
    }


    private int getAxisX(int i) {
        return (int) ((i + 1) * 20f * getWidth() / adapter.getData().getXSize());
    }

    private int getAxisY(double value) {
        ArrayList<Double> yData = adapter.getData().getYData();
        double minValue = yData.get(0);
        double maxValue = yData.get(yData.size() - 1);

        float scaleY = (float) ((-getHeight() * 20f) / (maxValue - minValue));

        return (int) ((maxValue - value) * scaleY + getHeight() * 20f);
    }

    private void logD(String log) {
        Log.d("LiqView", log);
    }
}
