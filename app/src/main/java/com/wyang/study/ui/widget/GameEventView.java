package com.wyang.study.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.OverScroller;

import com.wyang.study.R;

import java.util.List;

abstract class ScrollAndScaleView extends ViewGroup implements
        GestureDetector.OnGestureListener,
        ScaleGestureDetector.OnScaleGestureListener {
    protected int mScrollX = 0;
    protected int mScrollY = 0;
    protected GestureDetectorCompat mDetector;
    protected ScaleGestureDetector mScaleDetector;

    private OverScroller mScroller;

    protected boolean touch = false;

    protected float mScale = 1;

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
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        if (!isMultipleTouch()) {
            scrollBy(Math.round(distanceX), Math.round(distanceY));
            return true;
        }
        return false;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (!isTouch() && isScrollEnable()) {
            mScroller.fling(mScrollX, mScrollY,
                    Math.round(velocityX / mScale), Math.round(velocityY / mScale),
                    Integer.MIN_VALUE, Integer.MAX_VALUE,
                    Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        return true;
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            if (!isTouch()) {
                scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            } else {
                mScroller.forceFinished(true);
            }
        }
    }

    @Override
    public void scrollBy(int x, int y) {
        scrollTo(mScrollX - Math.round(x / mScale), mScrollY - Math.round(y / mScale));
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

        Log.e("Game", "mScrollX=" + mScrollX + "--- min=" + getMinScrollX());
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
    public boolean onScale(ScaleGestureDetector detector) {
        if (!isScaleEnable()) {
            return false;
        }
        float oldScale = mScale;
        mScale *= detector.getScaleFactor();

        Log.e("Game", "scale=" + mScale + "_min=" + getScaleMin());
        if (mScale < getScaleMin()) {
            mScale = getScaleMin();
        } else if (mScale > getScaleMax()) {
            mScale = getScaleMax();
        } else {
            onScaleChanged(mScale, oldScale);
        }
        return true;
    }

    protected void onScaleChanged(float scale, float oldScale) {
        invalidate();
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        return true;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {

    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                touch = true;
                break;
            case MotionEvent.ACTION_POINTER_UP:
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                touch = false;
                invalidate();
                break;
            default:
                break;
        }
        mMultipleTouch = event.getPointerCount() > 1;
        this.mDetector.onTouchEvent(event);
        this.mScaleDetector.onTouchEvent(event);
        return true;
    }


    /**
     * 是否在触摸中
     *
     * @return
     */
    public boolean isTouch() {
        return touch;
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

    public abstract float getScaleMax();

    public abstract float getScaleMin();

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
}

/**
 * Created by weiyang on 2019-10-17.
 */
public class GameEventView extends ScrollAndScaleView {
    private int total;//树形结构节点总计，可根据参赛队伍计算
    private int rows;//多少列
    private GameEventAdapter mAdapter;
    private float mTranslateX = Float.MIN_VALUE;
    private float mTranslateY = Float.MIN_VALUE;

    private int mStrokeWidth = 1;
    private int mStrokeColor = Color.GRAY;
    private int mStrokeMargin = 5;

    private int w, h;//队伍的宽高
    private int space = 20;//小组队伍间距
    private int margin = 40;//小组间距

    private int marginRow = 30;//列的间距
    private float multiple = 3.0f;//第一 第二列间距与平均间距的倍数
    private int mPadding = 50;

    private final Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public GameEventView(Context context) {
        this(context, null);
    }

    public GameEventView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GameEventView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.GameEventView);
        mStrokeWidth = typedArray.getDimensionPixelOffset(R.styleable.GameEventView_gStrokeWidth, dp2px(mStrokeWidth));
        mStrokeColor = typedArray.getColor(R.styleable.GameEventView_gStrokeColor, mStrokeColor);
        mStrokeMargin = typedArray.getDimensionPixelOffset(R.styleable.GameEventView_gStrokeMargin, dp2px(mStrokeMargin));

        space = typedArray.getDimensionPixelOffset(R.styleable.GameEventView_gSpace, dp2px(space));
        margin = typedArray.getDimensionPixelOffset(R.styleable.GameEventView_gMargin, dp2px(margin));
        marginRow = typedArray.getDimensionPixelOffset(R.styleable.GameEventView_gMarginRow, dp2px(marginRow));
        multiple = typedArray.getFloat(R.styleable.GameEventView_gMultiple, multiple);
        mPadding = typedArray.getDimensionPixelOffset(R.styleable.GameEventView_gPadding, dp2px(mPadding));

        typedArray.recycle();

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setColor(mStrokeColor);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < total; i++) {
            View item = getChildAt(i);

            int[] point = findPosition(i);
            int left = point[0] + getPaddingLeft();
            int top = point[1] + getPaddingTop();
            int right = left + item.getMeasuredWidth();
            int bottom = top + item.getMeasuredHeight();
            item.layout(left, top, right, bottom);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        measureChildren(widthMeasureSpec, heightMeasureSpec);
        if (getChildCount() != 0) {
            View child = getChildAt(0);
            w = child.getMeasuredWidth();
            h = child.getMeasuredHeight();
        }
        mScale = Math.max(mScale, getScaleMin());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(mTranslateX, mTranslateY);
        canvas.scale(mScale, mScale);

        int index = 0;
        //绘制线的逻辑，每组比赛胜出的节点对应一个二进一的线，8组，则从第17个节点开始计算，4组则从第9个开始
        for (int i = rows - 1; i > 0; i--) {//3 2 1
            //例：16个队伍 8组,index = 8
            index += get2Power(i);

            int m = rows - i;// 1 2 3
            for (int j = 0; j < get2Power(i - 1); j++) {
                int index1 = index - 2;//6

                int index2 = index - 1;//7

                if (m < rows) {
                    index1 -= (get2Power(rows - 1 - m) - j - 1) * 2;
                    index2 -= (get2Power(rows - 1 - m) - j - 1) * 2;
                } else
                    break;

                //当前列的第一个节点的前2个节点为当前列的最后一个的子节点
                View child1 = getChildAt(index1);
                View child2 = getChildAt(index2);

                View cChild = getChildAt(index + j);//当前节点
                //绘制线段，拆分3段

                int left = child1.getLeft() - (i == rows - 1 ? 0 : mStrokeMargin);
                int top = child1.getTop() + child1.getBottom() >> 1;
                int bottom = child2.getTop() + child2.getBottom() >> 1;
                int right = cChild.getLeft() - mStrokeMargin;
                canvas.drawLine(left, top, right, top, mPaint);
                canvas.drawLine(right, top - (mStrokeWidth >> 1), right, bottom + (mStrokeWidth >> 1), mPaint);
                canvas.drawLine(left, bottom, right, bottom, mPaint);
            }
        }

        //绘制总冠军节点的线
        View child = getChildAt(total - 1);
        int x = child.getLeft() - mStrokeMargin;
        int y = child.getTop() + child.getBottom() >> 1;
        canvas.drawLine(x, y, x + child.getMeasuredWidth(), y, mPaint);
    }

    public void setLowestCount(int count, GameEventAdapter adapter) {
        if (!check2Index(count))
            throw new IllegalArgumentException("数量为2的指数方，请检查");

        rows = get2Index(count) + 1;//计算有多少列
        total = (int) Math.pow(2, rows) - 1;//计算总节点数

        mAdapter = adapter;

        //添加所有节点
        for (int i = 0; i < total; i++) {
            View item = mAdapter.getView(i);
            addView(item);
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(super.generateDefaultLayoutParams());
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        setTranslateXByScrollX(mScrollX);
        setTranslateYByScrollY(mScrollY);
    }

    @Override
    protected void onScaleChanged(float scale, float oldScale) {
        checkAndFixScroll();
        setTranslateXByScrollX(mScrollX);
        setTranslateYByScrollY(mScrollY);
        super.onScaleChanged(scale, oldScale);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        setTranslateXByScrollX(mScrollX);
        setTranslateYByScrollY(mScrollY);
    }

    private void setTranslateXByScrollX(int scrollX) {
        mTranslateX = scrollX + mPadding * mScale;
    }

    private void setTranslateYByScrollY(int scrollY) {
        mTranslateY = scrollY + mPadding * mScale;
    }

    @Override
    public float getScaleMin() {
        float scaleW = 1.0f * getMeasuredWidth() / getDrawWidth();
        float scaleH = 1.0f * getMeasuredHeight() / getDrawHeight();

        if (scaleW > mScale && scaleH > mScale)
            return Math.max(scaleW, scaleH);
        if (scaleW > mScale)
            return scaleW;
        if (scaleH > mScale)
            return scaleH;
        return 0.5f;
    }

    @Override
    public float getScaleMax() {
        return Math.max(2.0f, getScaleMin());
    }

    @Override
    public int getMinScrollX() {
        return (int) (-mScale * getDrawWidth() + getMeasuredWidth());
    }

    @Override
    public int getMaxScrollX() {
        return 0;
    }

    @Override
    public int getMinScrollY() {
        return (int) (-mScale * getDrawHeight() + getMeasuredHeight());
    }

    @Override
    public int getMaxScrollY() {
        return 0;
    }

    /**
     * 画布绘制的最大宽度
     */
    private int getDrawWidth() {
        return (int) ((w + marginRow) * rows + (multiple - 2) * marginRow + mPadding * 2);
    }

    /**
     * 画布绘制的最大高度
     */
    private int getDrawHeight() {
        int power = get2Power(rows - 1);
        return power * h + power / 2 * space + (power - 1) / 2 * margin + mPadding * 2;
    }

    /**
     * 找到每个节点的左上角
     */
    private int[] findPosition(int index) {
        int[] point = new int[2];

        int m = 0, n = 0;//确定节点位置 m表示第几列 n表示第几个
        int off = 0;
        for (int i = rows - 1; i >= 0; i--) {
            //总冠军为最后一行
            if (i == 0) {
                m = rows - 1;
                n = 0;
                break;
            } else {
                //当前列节点的个数
                int power = get2Power(i);
                if (index < power + off) {
                    m = rows - i - 1;
                    n = index - off;
                    break;
                } else {
                    off += power;
                }
            }
        }

        if (m == 1) {
            point[0] = (int) (w + marginRow * multiple);//第一列与第二列的间距值较大，暂定为平均间距的4倍
        } else if (m > 0) {
            point[0] = (int) (m * (w + marginRow) + (multiple - 1) * marginRow);
        }

        if (index < get2Power(rows - 1)) {//求第一列每一个节点的y
            point[1] = index * h + (index + 1) / 2 * space + index / 2 * margin;
        } else {
            //除第一列节点以外的y需要通过前两个节点计算

            int index1 = off - 2;

            int index2 = off - 1;

            if (m < rows) {//总冠军节点即为前2个
                index1 -= (get2Power(rows - 1 - m) - n - 1) * 2;
                index2 -= (get2Power(rows - 1 - m) - n - 1) * 2;
            }

            //当前列的第一个节点的前2个节点为当前列的最后一个的子节点
            View child1 = getChildAt(index1);
            View child2 = getChildAt(index2);

            point[1] = (child1.getTop() + child2.getBottom()) / 2 - h / 2;
        }
        return point;
    }

    /**
     * 判断 n 是不是 2 的指数次方
     */
    private boolean check2Index(int n) {
        return (n & (n - 1)) == 0;
    }

    /**
     * 已知一个数，求这个数对应2的指数
     */
    private int get2Index(int n) {
        return (int) (Math.log(n) / Math.log(2));
    }

    private int get2Power(int i) {
        return (int) Math.pow(2, i);
    }

    public int dp2px(float dp) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public abstract static class GameEventAdapter<T> {
        private List<T> mData;

        public GameEventAdapter(List<T> mData) {
            this.mData = mData;
        }

        public T getItem(int position) {
            return mData.get(position);
        }

        public int getCount() {
            return mData == null ? 0 : mData.size();
        }

        public abstract View getView(int position);
    }
}


