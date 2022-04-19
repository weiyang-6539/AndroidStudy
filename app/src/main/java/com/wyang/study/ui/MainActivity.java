package com.wyang.study.ui;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;

import com.wyang.study.R;
import com.wyang.study.ui.base.BaseActivity;
import com.wyang.study.ui.util.FragmentFactory;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolBar;
    @BindView(R.id.navigationView)
    NavigationView mNavigationView;
    @BindView(R.id.drawerLayout)
    DrawerLayout mDrawerLayout;

    private FragmentFactory mFactory;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mFactory = new FragmentFactory(this);

        initToolBar(mToolBar, "Android Study", true);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                mToolBar, 0, 0);
        drawerToggle.syncState();
    }

    @Override
    protected void initListener() {
        mNavigationView.setNavigationItemSelectedListener(item -> {
            mDrawerLayout.closeDrawer(Gravity.START);
            switch (item.getItemId()) {
                case R.id.navigation_0:
                    mFactory.selectItem(0);
                    break;
                case R.id.navigation_1:
                    mFactory.selectItem(1);
                    break;
                case R.id.navigation_2:
                    mFactory.selectItem(2);
                    break;
                case R.id.navigation_3:
                    mFactory.selectItem(3);
                    break;
                case R.id.navigation_4:
                    mFactory.selectItem(4);
                    break;
                case R.id.navigation_5:
                    mFactory.selectItem(5);
                    break;
            }
            return false;
        });

        mFactory.selectItem(0);
    }
}
