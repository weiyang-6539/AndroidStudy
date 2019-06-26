package com.wyang.study.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weiyang on 2019/6/25.
 * 图片九宫格显示,最大支持显示9张,超过9张图片最后会显示未显示的数目
 */
public class NineGridLayout extends ViewGroup {
    private Context mContext;
    private int mWidth;

    private int rows, columns;//行数,列数
    private int mSpacing = 5;//图片间隙

    private ImageLoaderInterface imageLoader;
    private List<String> mUrls = new ArrayList<>();

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

        if (!mUrls.isEmpty()) {
            setVisibility(VISIBLE);
            initRowAndColumn();

            for (int i = 0; i < mUrls.size(); i++) {
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
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();

        if (!mUrls.isEmpty()) {//有图片才向布局中添加子View
            int childCount = mUrls.size();
            int childWidth = (mWidth - 2 * mSpacing) / 3;
            if (columns == 1) {
                childWidth = (int) (childWidth * 2.2f);
            }
            for (int i = 0; i < childCount; i++) {
                int[] position = findPosition(i);
                int left = (childWidth + mSpacing) * position[1];
                int top = (childWidth + mSpacing) * position[0];
                int right = left + childWidth;//当padding值为0时,可能纯在留白
                int bottom = top + childWidth;

                left += getPaddingLeft();
                top += getPaddingTop();
                right += getPaddingRight();
                bottom += getPaddingBottom();

                ImageView child = (ImageView) getChildAt(i);
                if (childCount == 1) {
                    //只有一张图片
                    child.setScaleType(ImageView.ScaleType.CENTER_CROP);
                } else {
                    child.setScaleType(ImageView.ScaleType.CENTER_CROP);
                }
                child.layout(left, top, right, bottom);
                //布局后加载图片
                imageLoader.displayImage(mContext, child, mUrls.get(i));
            }

            //根据子view数量确定高度
            LayoutParams params = getLayoutParams();
            params.height = childWidth * rows + mSpacing * (rows - 1) + getPaddingTop() + getPaddingBottom();
            setLayoutParams(params);
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

    public interface ImageLoaderInterface {
        ImageView createImageView(Context context, int count, int pos);

        void displayImage(Context context, ImageView imageView, String url);
    }
}
