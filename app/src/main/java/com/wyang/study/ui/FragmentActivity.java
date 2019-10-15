package com.wyang.study.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.wyang.study.R;
import com.wyang.study.bean.Simple;
import com.wyang.study.ui.base.BaseActivity;
import com.wyang.study.ui.util.DataProvider;

import butterknife.BindView;

/**
 * Created by weiyang on 2019/6/20.
 */
public class FragmentActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolBar;

    private Simple simple;

    @Override
    public int getLayoutId() {
        return R.layout.activity_fragment;
    }

    @Override
    protected void initData() {
        simple = (Simple) getIntent().getSerializableExtra("simple");
    }

    @Override
    protected void initView() {

        Fragment fragment = DataProvider.createFragmentByName(simple.getClassName());

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fl_container, fragment);
        transaction.commitAllowingStateLoss();

        initToolBar(mToolBar, simple.getTitle(), true, true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar_right, menu);
        return true;
    }
}
