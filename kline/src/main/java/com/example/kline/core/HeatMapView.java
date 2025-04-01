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

import androidx.annotation.NonNull;

import com.example.kline.utils.TextDrawHelper;

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

    private final Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final PointF tempP = new PointF();
    private final RectF tempRect = new RectF();
    private final int colorIncrease = Color.parseColor("#d84532");
    private final int colorDecrease = Color.parseColor("#4a7c21");
    private final int colorMainBg = Color.parseColor("#3e0b50");
    /* 烛状图单根柱子宽 */
    private float mCandleWidth;

    /* 绘制主图的区域 */
    private final RectF mainRect = new RectF();
    /* 绘制x轴区域 */
    private final RectF axisXRect = new RectF();
    /* 绘制y轴区域 */
    private final RectF axisYRect = new RectF();
    // 绘制文本
    private final Paint mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final TextDrawHelper helper = new TextDrawHelper();

    private float bmpScaleX;
    private float bmpScaleY;
    private final PointF bmpPoint = new PointF(); // 图片当前显示中心点位置

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
    public float getMaxScale() {
        return 20f;
    }

    @Override
    public float getMinScale() {
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
        axisXRect.set(left, mainRect.bottom + 5, right, mainRect.bottom + 5 + textHeight * 2);
        // 确定Y轴区域
        axisYRect.set(mainRect.right + 5, top, width, bottom);

        int height = (int) axisXRect.bottom;

        // 设置图片缩放， 实际是初始单个像素点放大倍数
        bmpScaleX = mainRect.width() / data.getXSize();
        bmpScaleY = mainRect.height() / data.getYSize();
        bmpPoint.x = mainRect.width() / 2;
        bmpPoint.y = mainRect.height() / 2;
        matrix.setScale(bmpScaleX, bmpScaleY);

        mCandleWidth = bmpScaleX / 2;

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        mPaint.setColor(colorMainBg);
        canvas.drawRect(mainRect, mPaint);

        mPaint.setColor(Color.parseColor("#eeeeee"));
        canvas.drawRect(axisYRect, mPaint);
        canvas.drawRect(axisXRect, mPaint);

        // 将 Bitmap 绘制到固定矩形区域内
        canvas.save();
        // 限制绘制区域
        canvas.clipRect(mainRect);
        // 绘制热力图
        canvas.drawBitmap(adapter.getBitmap(), matrix, null);
        // 绘制烛状图
        drawKLine(canvas);
        // restore
        canvas.restore();
        // 绘制x轴
        drawAxisX(canvas);
        // 绘制y轴
        drawAxisY(canvas);
    }

    private void drawKLine(Canvas canvas) {
        ArrayList<ArrayList<Number>> xData = adapter.getData().getXData();
        int startIndex = indexOfX(drawX2X(axisXRect.left));
        int endIndex = indexOfX(drawX2X(axisXRect.right));
        for (int i = startIndex; i <= endIndex; i++) {
            int x = getAxisX(i);
            ArrayList<Number> candle = xData.get(i);
            int openY = getAxisY(candle.get(1).doubleValue());
            int highY = getAxisY(candle.get(2).doubleValue());
            int lowY = getAxisY(candle.get(3).doubleValue());
            int closeY = getAxisY(candle.get(4).doubleValue());

            if (openY > closeY) {//涨(这里比较的y坐标值)
                drawCandle(canvas, x, openY, closeY, true);
                drawCandleLine(canvas, x, highY, closeY);
                drawCandleLine(canvas, x, openY, lowY);
            } else if (openY < closeY) {
                drawCandle(canvas, x, openY, closeY, false);
                drawCandleLine(canvas, x, highY, openY);
                drawCandleLine(canvas, x, closeY, lowY);
            } else {
                drawCandle(canvas, x, closeY - 1, closeY, true);
            }
        }
        mPaint.setStyle(Paint.Style.FILL);
    }

    /**
     * 绘制蜡烛矩形
     */
    public void drawCandle(Canvas canvas, float x, float top, float bottom, boolean isIncrease) {
        tempRect.set(x - mCandleWidth / 2f, top, x + mCandleWidth / 2f, bottom);
        mPaint.setColor(isIncrease ? colorIncrease : colorDecrease);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawRect(tempRect, mPaint);
    }

    /**
     * 绘制蜡烛图上下影线
     */
    public void drawCandleLine(Canvas canvas, float x, float startY, float stopY) {
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawLine(x, startY, x, stopY, mPaint);
    }

    private void drawAxisX(Canvas canvas) {
        ArrayList<ArrayList<Number>> data = adapter.getData().getXData();
        int numX = 5; // x轴显示日期数
        float quoteW = axisXRect.width() / (numX * 2);
        for (int i = 0; i < numX; i++) {
            float dx = quoteW * (2 * i + 1);
            int index = indexOfX(drawX2X(dx));
            tempP.x = axisXRect.left + dx;
            tempP.y = axisXRect.top;
            String ss = formatter.formatDate(data.get(index).get(0).intValue() * 1000L);

            String[] arr = ss.split(" ");

            helper.drawPointBot(canvas, arr[0], tempP, mTextPaint);
            tempP.y += helper.getTextHeight(mTextPaint) + 3;
            helper.drawPointBot(canvas, arr[1], tempP, mTextPaint);
        }
    }

    private void drawAxisY(Canvas canvas) {
        ArrayList<Double> yData = adapter.getData().getYData();

        int numY = 10; // y轴显示价格数
        int startIndex = indexOfY(drawY2Y(0));
        int endIndex = indexOfY(drawY2Y(axisYRect.height()));
        for (int i = startIndex; i < endIndex; i += Math.max(2, (endIndex - startIndex) / numY)) {
            tempP.x = axisYRect.left;
            tempP.y = axisYRect.bottom + mScrollY - getPx(i);
            if (tempP.y < axisYRect.bottom) {
                String ss = formatter.formatPrice(yData.get(i));
                helper.drawPointRight(canvas, ss, tempP, mTextPaint);
            }
        }
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldLeft, int oldTop) {
        super.onScrollChanged(l, t, oldLeft, oldTop);
        bmpPoint.x = mainRect.width() * mScrollX / getMaxScrollX();
        bmpPoint.y = mainRect.height() * mScrollY / getMaxScrollY();

        matrix.setScale(bmpScaleX * mScale, bmpScaleY * mScale);
        matrix.postTranslate(mScrollX - getMaxScrollX(), mScrollY - getMaxScrollY());

        invalidate();
    }

    @Override
    protected void onScaleChanged(float scale, float oldScale) {
        mScrollX = (int) (getMaxScrollX() * bmpPoint.x / mainRect.width());
        mScrollY = (int) (getMaxScrollY() * bmpPoint.y / mainRect.height());

        matrix.setScale(bmpScaleX * scale, bmpScaleY * scale);
        matrix.postTranslate(mScrollX - getMaxScrollX(), mScrollY - getMaxScrollY());

        mCandleWidth = bmpScaleX * mScale / 2;

        invalidate();
    }

    private int getAxisX(int i) {
        return (int) (axisXRect.left + getPx(i) - (getMaxScrollX() - mScrollX) - mCandleWidth / 2f);
    }

    private int getAxisY(double value) {
        ArrayList<Double> yData = adapter.getData().getYData();
        double minValue = yData.get(0);
        double maxValue = yData.get(yData.size() - 1);

        float scaleY = (float) ((bmpScaleY * mScale * (yData.size() - 1)) / (maxValue - minValue));
        return (int) ((minValue - value) * scaleY + axisYRect.height() + mScrollY);
    }

    private int getPx(int position) {
        return (int) ((position + 1) * bmpScaleX * mScale);
    }

    public float drawX2X(float x) {
        return (getMaxScrollX() - mScrollX) + x;
    }

    public float drawY2Y(float y) {
        return mScrollY + y;
    }

    public int indexOfX(float x) {
        return indexOfPx(x, 0, adapter.getData().getXSize() - 1);
    }

    public int indexOfY(float x) {
        return indexOfPx(x, 0, adapter.getData().getYSize() - 1);
    }

    /**
     * 二分查找当前值的index
     */
    public int indexOfPx(float px, int start, int end) {
        if (end == start) {
            return start;
        }
        if (end - start == 1) {
            float startX = getPx(start);
            float endX = getPx(end);
            return Math.abs(px - startX) < Math.abs(px - endX) ? start : end;
        }
        int mid = start + (end - start) / 2;
        float midX = getPx(mid);
        if (px < midX) {
            return indexOfPx(px, start, mid);
        } else if (px > midX) {
            return indexOfPx(px, mid, end);
        } else {
            return mid;
        }
    }
}
