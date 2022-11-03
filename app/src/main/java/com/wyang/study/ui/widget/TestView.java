package com.wyang.study.ui.widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.wyang.study.utils.LogUtils;

/**
 * @author Yang
 * @desc
 * @since 2022/8/24 8:48
 */
public class TestView extends AppCompatTextView {
    private final int[] iconIds = new int[]{0, 0, 0, 0};

    public TestView(@NonNull Context context) {
        super(context);
    }

    public TestView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);

        LogUtils.e("TestView", "text:" + text + " start:" + start + " lengthBefore:" + lengthBefore + " lengthAfter:" + lengthAfter);
        LogUtils.e("TestView", iconIds == null ? "当前数组为null" : "数组大小:" + iconIds.length);
    }
}
