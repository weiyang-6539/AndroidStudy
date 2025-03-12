package com.example.kline.core;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
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
        return 0;
    }

    @Override
    public int getMinScrollY() {
        return 0;
    }

    @Override
    public int getMaxScrollY() {
        return 0;
    }

    @Override
    public float getScaleMax() {
        return 0;
    }

    @Override
    public float getScaleMin() {
        return 0;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = (int) (width / adapter.getRatio());
        rect.set(0, 0, width, height);
        setMeasuredDimension(width, height);
    }

    private final Rect rect = new Rect();

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        canvas.drawColor(Color.parseColor("#3e0b50"));

        canvas.drawBitmap(adapter.buildBitmap(), null, rect, null);
    }
}
