package com.wyang.study.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;

import com.wyang.study.R;
import com.wyang.study.ui.base.BaseActivity;
import com.wyang.study.ui.fragment_second.ContactsFragment;
import com.wyang.study.ui.fragment_second.DragSortFragment;
import com.wyang.study.ui.fragment_second.NineGridLayoutFragment;
import com.wyang.study.ui.fragment_second.WeChatFragment;
import com.wyang.study.ui.util.DataProvider;

import butterknife.BindView;

/**
 * Created by weiyang on 2019/6/20.
 */
public class FragmentActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolBar;
    private String className;

    @Override
    public int getLayoutId() {
        return R.layout.activity_fragment;
    }

    @Override
    protected void initData() {
        className = getIntent().getStringExtra("className");
    }

    @Override
    protected void initView() {
        Fragment fragment = null;
        Class<?> aClass = DataProvider.getFragmentClass(className);
        if (aClass == DragSortFragment.class) {
            fragment = new DragSortFragment();
        } else if (aClass == WeChatFragment.class) {
            fragment = new WeChatFragment();
        } else if (aClass == ContactsFragment.class) {
            fragment = new ContactsFragment();
        } else if (aClass == NineGridLayoutFragment.class) {
            fragment = new NineGridLayoutFragment();
        }

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fl_container, fragment);
        transaction.commitAllowingStateLoss();

        initToolBar(mToolBar, DataProvider.getTitle(className), true);
    }
}
