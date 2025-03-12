package com.example.kline.core;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.util.AttributeSet;

import androidx.annotation.NonNull;

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
        return (int) (getWidth() * mScale - getWidth());
    }

    @Override
    public int getMinScrollY() {
        return 0;
    }

    @Override
    public int getMaxScrollY() {
        return (int) (getHeight() * mScale - getHeight());
    }

    @Override
    public float getScaleMax() {
        return 20f;
    }

    @Override
    public float getScaleMin() {
        return 1;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = (int) (width / adapter.getRatio());

        // 设置图片缩放
        IData data = adapter.getData();
        float scale = 1f * width / data.getXSize();
        matrix.setScale(scale, scale);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        canvas.drawColor(Color.parseColor("#3e0b50"));

        canvas.drawBitmap(adapter.buildBitmap(), matrix, null);

    }

    @Override
    protected void onScaleChanged(float scale, float oldScale) {
        // 设置图片缩放
        IData data = adapter.getData();
        float newScale = 1f * getWidth() / data.getXSize() * scale;
        matrix.setScale(newScale, newScale);

        invalidate();
    }
}
