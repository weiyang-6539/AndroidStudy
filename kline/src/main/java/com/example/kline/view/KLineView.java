package com.example.kline.view;

import android.content.Context;
import android.util.AttributeSet;

/**
 * @author yang
 * @date 2025/3/11
 * @desc
 */
public class KLineView extends ScrollAndScaleView {

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
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
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

}
