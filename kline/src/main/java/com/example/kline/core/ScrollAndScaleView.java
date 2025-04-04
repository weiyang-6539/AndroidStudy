package com.example.kline.core;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ViewGroup;
import android.widget.OverScroller;

import androidx.annotation.NonNull;
import androidx.core.view.GestureDetectorCompat;

/**
 * @author yang
 * @date 2025/3/11
 * @desc
 */
abstract class ScrollAndScaleView extends ViewGroup implements GestureDetector.OnGestureListener,
        ScaleGestureDetector.OnScaleGestureListener {
    protected final String TAG = this.getClass().getSimpleName();
    protected int mScrollX = 0;
    protected int mScrollY = 0;
    protected GestureDetectorCompat mDetector;
    protected ScaleGestureDetector mScaleDetector;

    private OverScroller mScroller;

    protected boolean isTouch = false;
    protected boolean isLongPress = false;//长按的标记

    protected float mScale = 1f;

    private boolean mMultipleTouch = false;

    private boolean mScrollEnable = true;

    private boolean mScaleEnable = true;

    public ScrollAndScaleView(Context context) {
        super(context);
        init();
    }

    public ScrollAndScaleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ScrollAndScaleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setWillNotDraw(false);
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        mDetector = new GestureDetectorCompat(getContext(), this);
        mScaleDetector = new ScaleGestureDetector(getContext(), this);
        mScroller = new OverScroller(getContext());
    }

    @Override
    public boolean onDown(@NonNull MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(@NonNull MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(@NonNull MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, @NonNull MotionEvent e2, float distanceX, float distanceY) {
        if (!isMultipleTouch()) {
            scrollBy(Math.round(distanceX), Math.round(distanceY));
            return true;
        }
        return false;
    }

    @Override
    public boolean onFling(MotionEvent e1, @NonNull MotionEvent e2, float velocityX, float velocityY) {
        if (!isTouch && !isLongPress && isScrollEnable()) {
            mScroller.fling(mScrollX, mScrollY,
                    Math.round(velocityX), Math.round(velocityY),
                    Integer.MIN_VALUE, Integer.MAX_VALUE,
                    Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        return true;
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            if (!isTouch) {
                scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            } else {
                mScroller.forceFinished(true);
            }
        }
    }

    @Override
    public void scrollBy(int x, int y) {
        scrollTo(mScrollX - x, mScrollY - y);
    }

    @Override
    public void scrollTo(int x, int y) {
        if (!isScrollEnable()) {
            mScroller.forceFinished(true);
            return;
        }
        int oldX = mScrollX;
        int oldY = mScrollY;
        mScrollX = x;
        mScrollY = y;

        //控件基准点为左上角，故只能往右下滑动
        if (mScrollX > getMaxScrollX()) {
            mScrollX = getMaxScrollX();
            mScroller.forceFinished(true);
        } else if (mScrollX < getMinScrollX()) {
            mScrollX = getMinScrollX();
            mScroller.forceFinished(true);
        }

        if (mScrollY > getMaxScrollY()) {
            mScrollY = getMaxScrollY();
            mScroller.forceFinished(true);
        } else if (mScrollY < getMinScrollY()) {
            mScrollY = getMinScrollY();
            mScroller.forceFinished(true);
        }

        onScrollChanged(mScrollX, mScrollY, oldX, oldY);
        invalidate();
    }

    @Override
    public boolean onScale(@NonNull ScaleGestureDetector detector) {
        if (!isScaleEnable()) {
            return false;
        }
        float oldScale = mScale;
        mScale *= detector.getScaleFactor();

        if (mScale < getMinScale()) {
            mScale = getMinScale();
        } else if (mScale > getMaxScale()) {
            mScale = getMaxScale();
        } else {
            onScaleChanged(mScale, oldScale);
        }
        return true;
    }

    protected void onScaleChanged(float scale, float oldScale) {
    }

    @Override
    public boolean onScaleBegin(@NonNull ScaleGestureDetector detector) {
        return true;
    }

    @Override
    public void onScaleEnd(@NonNull ScaleGestureDetector detector) {

    }

    @Override
    public void onLongPress(@NonNull MotionEvent e) {

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    private float downX;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mMultipleTouch = event.getPointerCount() > 1;
        if (mMultipleTouch)
            isLongPress = false;
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                isTouch = true;
                downX = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                if (isLongPress)
                    onLongPress(event);
                break;
            case MotionEvent.ACTION_POINTER_UP:
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                if (downX == event.getX() && isLongPress) {
                    isLongPress = false;
                }
                isTouch = false;
                invalidate();
                break;
            case MotionEvent.ACTION_CANCEL:
                isLongPress = false;
                isTouch = false;
                invalidate();
                break;
            default:
                break;
        }
        this.mDetector.onTouchEvent(event);
        this.mScaleDetector.onTouchEvent(event);
        return true;
    }

    /**
     * 设置ScrollX
     *
     * @param scrollX
     */
    public void setScrollX(int scrollX, int scrollY) {
        this.mScrollX = scrollX;
        this.mScrollY = scrollY;
        scrollTo(scrollX, scrollY);
    }

    /**
     * 是否是多指触控
     *
     * @return
     */
    public boolean isMultipleTouch() {
        return mMultipleTouch;
    }

    protected void checkAndFixScroll() {
        int minScrollX = getMinScrollX();
        int maxScrollX = getMaxScrollX();
        if (mScrollX > maxScrollX) {
            mScrollX = maxScrollX;
            mScroller.forceFinished(true);
        } else if (mScrollX < minScrollX) {
            mScrollX = minScrollX;
            mScroller.forceFinished(true);
        }

        int minScrollY = getMinScrollY();
        int maxScrollY = getMaxScrollY();
        if (mScrollY > maxScrollY) {
            mScrollY = maxScrollY;
            mScroller.forceFinished(true);
        } else if (mScrollY < minScrollY) {
            mScrollY = minScrollY;
            mScroller.forceFinished(true);
        }
    }

    public abstract int getMinScrollX();

    public abstract int getMaxScrollX();

    public abstract int getMinScrollY();

    public abstract int getMaxScrollY();

    public abstract float getMaxScale();

    public abstract float getMinScale();

    public boolean isScrollEnable() {
        return mScrollEnable;
    }

    public boolean isScaleEnable() {
        return mScaleEnable;
    }

    /**
     * 设置是否可以滑动
     */
    public void setScrollEnable(boolean scrollEnable) {
        mScrollEnable = scrollEnable;
    }

    /**
     * 设置是否可以缩放
     */
    public void setScaleEnable(boolean scaleEnable) {
        mScaleEnable = scaleEnable;
    }

    @Override
    public float getScaleX() {
        return mScale;
    }

    @Override
    public float getScaleY() {
        return mScale;
    }
}

