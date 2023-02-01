package com.example.avatardemo.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

/*
 * @author Yang
 * @since 2023/2/1 9:32
 * @desc 多功能头像View
 */
public class MixtureAvatarView extends ViewGroup {
    private AppCompatImageView ivBackground;//背景
    private AvatarView ivAvatar;//头像

    public MixtureAvatarView(@NonNull Context context) {
        this(context, null);
    }

    public MixtureAvatarView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MixtureAvatarView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        //设置背景色
        setBackgroundColor(Color.WHITE);

        //添加背景View
        ivBackground = new AppCompatImageView(getContext());
        addView(ivBackground, generateDefaultLayoutParams());

        //添加头像View
        ivAvatar = new AvatarView(getContext());
        addView(ivAvatar, generateDefaultLayoutParams());
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        ivBackground.layout(0, 0, width, height);
        ivAvatar.layout(0, 0, width, height);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int wSize = MeasureSpec.getSize(widthMeasureSpec);
        int hSize = MeasureSpec.getSize(heightMeasureSpec);

        int wMode = MeasureSpec.getMode(widthMeasureSpec);
        int hMode = MeasureSpec.getMode(heightMeasureSpec);

        if (wMode != MeasureSpec.EXACTLY && hMode != MeasureSpec.EXACTLY) {
            wSize = hSize = getSuggestedMinimumWidth();
        } else if (wMode == MeasureSpec.EXACTLY) {
            hSize = wSize;
        } else {
            wSize = hSize;
        }
        setMeasuredDimension(wSize, hSize);

        measureChildren(MeasureSpec.makeMeasureSpec(wSize, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(hSize, MeasureSpec.EXACTLY));
    }

    public AppCompatImageView getIvBackground() {
        return ivBackground;
    }

    public AvatarView getIvAvatar() {
        return ivAvatar;
    }
}
