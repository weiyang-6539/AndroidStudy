package com.wyang.study.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by weiyang on 2019-09-26.
 */
public class ChineseFlag extends View {
    private final String TAG = getClass().getSimpleName();
    private final Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint starPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private boolean isDrawLine = true;

    public ChineseFlag(Context context) {
        this(context, null);
    }

    public ChineseFlag(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChineseFlag(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint.setStrokeWidth(0.3f);
        mPaint.setStyle(Paint.Style.STROKE);

        starPaint.setColor(0xffffff00);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthSpecMode == MeasureSpec.EXACTLY && heightSpecMode == MeasureSpec.AT_MOST) {
            heightSpecSize = (int) (widthSpecSize * 2f / 3);
        } else if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.EXACTLY) {
            widthSpecSize = (int) (heightSpecSize * 3f / 2);
        } else if (widthSpecMode == MeasureSpec.EXACTLY && heightSpecMode == MeasureSpec.EXACTLY) {
            heightSpecSize = (int) (widthSpecSize * 2f / 3);
        }

        setMeasuredDimension(widthSpecSize, heightSpecSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.RED);

        //绘制主五角星
        canvas.save();
        canvas.translate(getHeight() >> 2, getHeight() >> 2);
        canvas.rotate(-18);
        canvas.drawPath(getCompletePath(getMainOutRadius(), calcInnerRadius(getMainOutRadius())), starPaint);
        if (isDrawLine)
            canvas.drawCircle(0, 0, getMainOutRadius(), mPaint);
        canvas.restore();

        canvas.save();
        canvas.translate(getWidth() * 10f / 30, getHeight() * 2f / 20);
        canvas.rotate(-18 - 180 + atan(4, 3));
        canvas.drawPath(getCompletePath(getSecondOutRadius(), calcInnerRadius(getSecondOutRadius())), starPaint);
        if (isDrawLine)
            canvas.drawCircle(0, 0, getSecondOutRadius(), mPaint);
        canvas.restore();

        canvas.save();
        canvas.translate(getWidth() * 12f / 30, getHeight() * 4f / 20);
        canvas.rotate(-18 - 180 + atan(6, 1));
        canvas.drawPath(getCompletePath(getSecondOutRadius(), calcInnerRadius(getSecondOutRadius())), starPaint);
        if (isDrawLine)
            canvas.drawCircle(0, 0, getSecondOutRadius(), mPaint);
        canvas.restore();

        canvas.save();
        canvas.translate(getWidth() * 12f / 30, getHeight() * 7f / 20);
        canvas.rotate(-18 - atan(6, 2));
        canvas.drawPath(getCompletePath(getSecondOutRadius(), calcInnerRadius(getSecondOutRadius())), starPaint);
        if (isDrawLine)
            canvas.drawCircle(0, 0, getSecondOutRadius(), mPaint);
        canvas.restore();

        canvas.save();
        canvas.translate(getWidth() * 10f / 30, getHeight() * 9f / 20);
        canvas.rotate(-18 - atan(5, 4));
        canvas.drawPath(getCompletePath(getSecondOutRadius(), calcInnerRadius(getSecondOutRadius())), starPaint);
        if (isDrawLine)
            canvas.drawCircle(0, 0, getSecondOutRadius(), mPaint);
        canvas.restore();

        if (isDrawLine) {//中心横线
            canvas.drawLine(0, getHeight() >> 1, getWidth(), getHeight() >> 1, mPaint);
            //中心数线
            canvas.drawLine(getWidth() >> 1, 0, getWidth() >> 1, getHeight(), mPaint);

            //网格横线
            for (int i = 1; i < 10; i++) {
                int y = getHeight() / 20 * i;
                canvas.drawLine(0, y, getWidth() >> 1, y, mPaint);
            }

            //网格竖线
            for (int i = 1; i < 15; i++) {
                int x = getWidth() / 30 * i;
                canvas.drawLine(x, 0, x, getHeight() >> 1, mPaint);
            }

            canvas.drawLine(getMin() * 5, getMin() * 5, getMin() * 10, getMin() * 2, mPaint);
            canvas.drawLine(getMin() * 5, getMin() * 5, getMin() * 12, getMin() * 4, mPaint);
            canvas.drawLine(getMin() * 5, getMin() * 5, getMin() * 12, getMin() * 7, mPaint);
            canvas.drawLine(getMin() * 5, getMin() * 5, getMin() * 10, getMin() * 9, mPaint);
        }
    }

    private float getMin() {
        return getHeight() / 20f;
    }

    private float getMainOutRadius() {
        return 1.f * getHeight() * 3 / 20;
    }

    private float getSecondOutRadius() {
        return 1.f * getHeight() / 20;
    }

    float calcInnerRadius(float outRadius) {
        return outRadius * sin(18) / sin(180 - 36 - 18);
    }

    private Path getCompletePath(float outR, float inR) {
        Path path = new Path();
        path.moveTo(outR * cos(360f / 5 * 0), outR * sin(360f / 5 * 0));
        for (int i = 0; i < 5; i++) {
            path.lineTo(inR * cos(360f / 5 * i + 36), inR * sin(360f / 5 * i + 36));
            path.lineTo(outR * cos(360f / 5 * (i + 1)), outR * sin(360f / 5 * (i + 1)));
        }
        path.close();
        return path;
    }

    public void setDrawLine(boolean drawLine) {
        isDrawLine = drawLine;

        invalidate();
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

    float atan(float y, float x) {
        return (float) (Math.atan2(y, x) * 180 / Math.PI);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }

    @Override
    public void setOnTouchListener(OnTouchListener l) {
        super.setOnTouchListener(l);
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        super.setOnClickListener(l);
    }

    @Override
    public void setOnKeyListener(OnKeyListener l) {
        super.setOnKeyListener(l);
    }
}
