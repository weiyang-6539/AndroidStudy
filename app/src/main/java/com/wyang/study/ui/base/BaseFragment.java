package com.wyang.study.ui.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle2.components.support.RxFragment;

import butterknife.ButterKnife;

/**
 * Created by weiyang on 2019/6/19.
 */
public abstract class BaseFragment extends RxFragment {
    protected final String TAG = getClass().getSimpleName();
    protected View mRootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(getLayoutId(), container, false);
            ButterKnife.bind(this, mRootView);

            initView();
        }
        return mRootView;
    }

    protected abstract int getLayoutId();

    protected abstract void initView();
}
