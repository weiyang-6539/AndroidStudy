package com.example.kline.core;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.kline.utils.TextDrawHelper;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * @author yang
 * @date 2025/3/11
 * @desc
 */
public class HeatMapView extends ScrollAndScaleView {
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
    private final RectF tempRect = new RectF();
    private final int colorIncrease = Color.parseColor("#d84532");
    private final int colorDecrease = Color.parseColor("#4a7c21");
    private final int colorMainBg = Color.parseColor("#3e0b50");

    /* 绘制主图的区域 */
    private final RectF mainRect = new RectF();
    /* 绘制x轴区域 */
    private final RectF axisXRect = new RectF();
    /* 绘制y轴区域 */
    private final RectF axisYRect = new RectF();
    // 绘制文本
    private final Paint mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final TextDrawHelper helper = new TextDrawHelper();

    private float bmpOriginScale;
    private final PointF bmpCenterPoint = new PointF(); // 图片当前显示中心点位置

    private final Formatter formatter = new Formatter();

    public HeatMapView(Context context) {
        this(context, null);
    }

    public HeatMapView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HeatMapView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mTextPaint.setTextSize(24f);
        mTextPaint.setColor(Color.parseColor("#000000"));
    }

    @Override
    public int getMinScrollX() {
        return 0;
    }

    @Override
    public int getMaxScrollX() {
        return (int) (mainRect.width() * mScale - mainRect.width());
    }

    @Override
    public int getMinScrollY() {
        return 0;
    }

    @Override
    public int getMaxScrollY() {
        return (int) (mainRect.height() * mScale - mainRect.height());
    }

    @Override
    public float getScaleMax() {
        return 20f;
    }

    @Override
    public float getScaleMin() {
        return 1f;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);

        // 确定图形复制区域
        IData data = adapter.getData();
        Double v = data.getYData().get(data.getYSize() - 1);
        int textWidth = (int) mTextPaint.measureText(formatter.formatPrice(v));
        int left = 0, top = 0;
        int right = width - textWidth - 5;
        int bottom = (int) (right / adapter.getRatio());
        mainRect.set(left, top, right, bottom);

        float textHeight = helper.getTextHeight(mTextPaint);
        // 确定X轴区域
        axisXRect.set(0, mainRect.bottom + 5, right, mainRect.bottom + 5 + textHeight * 2);
        // 确定Y轴区域
        axisYRect.set(mainRect.right + 5, 0, width, mainRect.bottom);

        int height = (int) axisXRect.bottom;

        // 设置图片缩放
        bmpOriginScale = mainRect.width() / data.getXSize();
        bmpCenterPoint.x = mainRect.width() / 2;
        bmpCenterPoint.y = mainRect.height() / 2;
        matrix.setScale(bmpOriginScale, bmpOriginScale);

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        mCandlePaint.setColor(colorMainBg);
        canvas.drawRect(mainRect, mCandlePaint);
        // 将 Bitmap 绘制到固定矩形区域内
        canvas.save();
        canvas.clipRect(mainRect); // 限制绘制区域
        canvas.drawBitmap(adapter.getBitmap(), matrix, null);
        canvas.restore();

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
        }

        // 绘制x轴
        drawAxisX(canvas);
        // 绘制y轴
        drawAxisY(canvas);
    }

    /**
     * 绘制蜡烛矩形
     */
    public void drawCandle(Canvas canvas, float right, float top, float bottom, boolean isIncrease) {
        tempRect.set(right - mainRect.width() / adapter.getData().getXSize() / 2, top, right, bottom);
        mCandlePaint.setColor(isIncrease ? colorIncrease : colorDecrease);
        canvas.drawRect(tempRect, mCandlePaint);
    }

    private void drawAxisX(Canvas canvas) {
        ArrayList<ArrayList<Number>> data = adapter.getData().getXData();
        int parts = 5;
        int num = data.size() / parts;
        PointF pointF = new PointF();
        for (int i = 0; i < data.size(); i++) {
            int mod = i % num;
            int div = i / num;
            if (mod == 0) {
                pointF.x = (axisXRect.right - axisXRect.left) * div / parts;
                pointF.y = axisXRect.top;
                String ss = formatter.formatDate(data.get(i).get(0).intValue() * 1000L);

                String[] arr = ss.split(" ");

                helper.drawPointBot(canvas, arr[0], pointF, mTextPaint);
                pointF.y += helper.getTextHeight(mTextPaint) + 3;
                helper.drawPointBot(canvas, arr[1], pointF, mTextPaint);
            }
        }
    }

    private void drawAxisY(Canvas canvas) {
        ArrayList<Double> yData = adapter.getData().getYData();

        int parts = 10;
        int num = yData.size() / parts;
        PointF pointF = new PointF();
        for (int i = 0; i < yData.size(); i++) {
            int mod = i % num;
            int div = i / num;
            if (mod == 0) {
                pointF.x = axisYRect.left;
                pointF.y = (axisYRect.bottom - axisYRect.top) * (parts - div) / parts;
                String ss = formatter.formatPrice(yData.get(i));
                helper.drawPointRight(canvas, ss, pointF, mTextPaint);
            }
        }
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        bmpCenterPoint.x += oldl - l;
        bmpCenterPoint.y += oldt - t;

        int dx = (int) (getMaxScrollX()/mScale * bmpCenterPoint.x / mainRect.width());
        int dy = (int) (getMaxScrollY()/mScale * bmpCenterPoint.y / mainRect.height());

        matrix.postTranslate(dx, dy);
        invalidate();
    }

    @Override
    protected void onScaleChanged(float scale, float oldScale) {
        matrix.setScale(bmpOriginScale * scale, bmpOriginScale * scale);

        mScrollX = (int) (getMaxScrollX() * bmpCenterPoint.x / mainRect.width());
        mScrollY = (int) (getMaxScrollY() * bmpCenterPoint.y / mainRect.height());

        matrix.postTranslate(-mScrollX, -mScrollY);
        invalidate();
    }

    private int getAxisX(int i) {
        return (int) ((i + 1) * mainRect.width() / adapter.getData().getXSize());
    }

    private int getAxisY(double value) {
        ArrayList<Double> yData = adapter.getData().getYData();
        double minValue = yData.get(0);
        double maxValue = yData.get(yData.size() - 1);

        float scaleY = (float) ((-mainRect.height()) / (maxValue - minValue));

        return (int) ((maxValue - value) * scaleY + mainRect.height());
    }

    private void logD(String log) {
        Log.d("LiqView", log);
    }
}
