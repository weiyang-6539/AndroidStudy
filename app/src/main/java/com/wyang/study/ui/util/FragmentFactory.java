package com.wyang.study.ui.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.wyang.study.R;
import com.wyang.study.ui.fragment_main.MainFragment;

/**
 * Created by weiyang on 2019/6/18.
 */
public class FragmentFactory {
    private FragmentActivity mActivity;
    private Fragment currentFragment;
    private int currentPos = -1;

    public FragmentFactory(FragmentActivity mActivity) {
        this.mActivity = mActivity;
    }

    public void selectItem(int pos) {
        //点击的正是当前正在显示的，直接返回
        if (currentPos == pos)
            return;
        FragmentManager manager = mActivity.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if (currentFragment != null) {
            //隐藏当前正在显示的fragment
            transaction.hide(currentFragment);
        }
        currentPos = pos;
        Fragment fragment = manager.findFragmentByTag(getTag(pos));
        //通过findFragmentByTag判断是否已存在目标fragment，若存在直接show，否则去add
        if (fragment != null) {
            currentFragment = fragment;
            transaction.show(fragment);
        } else {
            currentFragment = createFragment(pos);
            transaction.add(R.id.fl_container, currentFragment, getTag(pos));
        }
        transaction.commitAllowingStateLoss();
    }

    private String getTag(int pos) {
        return "fg_tag_" + pos;
    }

    private Fragment createFragment(int pos) {
        return MainFragment.newInstance(pos);
    }

}
