package com.wyang.study.ui.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weiyang on 2019/6/25.
 * 图片九宫格显示,最大支持显示9张,超过9张图片最后会显示未显示的数目
 */
public class NineGridLayout extends ViewGroup {
    private Context mContext;
    private int childCount;//子View数量
    private int childSize;//子View尺寸大小
    private int mode;//留白像素值

    private int rows, columns;//行数,列数
    private int mSpacing = 5;//图片间隙

    private ImageLoaderInterface imageLoader;
    private final List<String> mUrls = new ArrayList<>();

    public NineGridLayout(Context context) {
        this(context, null);
    }

    public NineGridLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NineGridLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    private void init(Context context) {
        mContext = context;

        if (mUrls.isEmpty()) {
            setVisibility(GONE);
        }
    }

    public void setImageUrls(List<String> urls, ImageLoaderInterface imageLoader) {
        if (imageLoader == null) {
            throw new NullPointerException("imageLoader is null");
        }
        this.imageLoader = imageLoader;

        mUrls.clear();
        mUrls.addAll(urls);
        removeAllViews();

        if (mUrls.isEmpty()) {
            setVisibility(GONE);
        } else {
            setVisibility(VISIBLE);
            initRowAndColumn();

            //确定添加到布局中的子View个数
            childCount = Math.min(rows * columns, mUrls.size());
            for (int i = 0; i < childCount; i++) {
                //考虑到拓展ImageView,这里通过接口的形式创建ImageView
                View child = imageLoader.createImageView(mContext, mUrls.size(), i);
                addView(child, generateDefaultLayoutParams());
            }
            requestLayout();
        }
    }

    /**
     * 初始化行列数
     */
    private void initRowAndColumn() {
        if (mUrls.size() <= 3) {
            rows = 1;
            columns = mUrls.size();
        } else if (mUrls.size() == 4) {
            rows = 2;
            columns = 2;
        } else if (mUrls.size() <= 6) {
            rows = 2;
            columns = 3;
        } else {
            rows = 3;
            columns = 3;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.e("NineGridLayout", "调用onMeasure()");

        int mWidth = MeasureSpec.getSize(widthMeasureSpec);
        int width = mWidth - getPaddingLeft() - getPaddingRight();
        //计算子View的最小宽度
        childSize = (width - 2 * mSpacing) / 3;
        mode = (width - 2 * mSpacing) % 3;

        //仅一个子View时,放大其尺寸
        if (columns == 1) {
            childSize = (int) (childSize * 2.2f);
        }

        //根据子View确定控件的高度
        int mHeight = childSize * rows + mSpacing * (rows - 1) + getPaddingTop() + getPaddingBottom() + mode;

        measureChildren(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.e("NineGridLayout", "调用onLayout()");
        //有图片才向布局中添加子View
        if (!mUrls.isEmpty()) {
            for (int i = 0; i < childCount; i++) {
                int[] position = findPosition(i);
                int left = (childSize + mSpacing) * position[1] + getPaddingLeft();
                int top = (childSize + mSpacing) * position[0] + getPaddingTop();
                int right = left + childSize;
                int bottom = top + childSize;

                //处理留白问题
                if (position[0] + 1 == rows) {
                    bottom += mode;
                }
                if (position[1] + 1 == columns) {
                    right += mode;
                }

                ImageView child = (ImageView) getChildAt(i);
                child.setScaleType(ImageView.ScaleType.CENTER_CROP);
                child.layout(left, top, right, bottom);
                //布局后加载图片
                imageLoader.displayImage(mContext, child, mUrls.get(i));

                if (i == childCount - 1 && mUrls.size() > 9) {
                    View view = getMoreView();
                    view.layout(left, top, right, bottom);
                    if (view.getParent() == null)
                        addView(view);
                }
            }
        }
    }

    private int[] findPosition(int childNum) {
        int[] position = new int[2];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if ((i * columns + j) == childNum) {
                    position[0] = i;//行
                    position[1] = j;//列
                    break;
                }
            }
        }
        return position;
    }

    public void setSpacing(int mSpacing) {
        this.mSpacing = mSpacing;
        requestLayout();
    }

    private FrameLayout moreView;

    @SuppressLint("SetTextI18n")
    private View getMoreView() {
        if (moreView == null) {
            moreView = new FrameLayout(mContext);
            moreView.setBackgroundColor(Color.BLACK);
            moreView.getBackground().setAlpha(128);

            TextView textView = new TextView(mContext);
            textView.setTextColor(Color.WHITE);
            textView.setTextSize(15);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.CENTER;
            textView.setLayoutParams(params);

            moreView.addView(textView);
        }

        TextView textView = (TextView) moreView.getChildAt(0);
        textView.setText("+" + (mUrls.size() - 9));

        return moreView;
    }

    private int getFontHeight(float fontSize) {
        Paint paint = new Paint();
        paint.setTextSize(fontSize);
        Paint.FontMetrics fm = paint.getFontMetrics();
        return (int) Math.ceil(fm.descent - fm.ascent);
    }

    public interface ImageLoaderInterface {
        ImageView createImageView(Context context, int count, int pos);

        void displayImage(Context context, ImageView imageView, String url);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }
}
