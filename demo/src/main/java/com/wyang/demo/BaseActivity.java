package com.wyang.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.ButterKnife;

/**
 * Created by weiyang on 2019/6/18.
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected final String TAG = getClass().getSimpleName();
    private boolean isShowRight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);

        //初始化数据
        initData();
        //view与数据绑定
        initView();
        //设置监听
        initListener();
    }

    public abstract int getLayoutId();

    protected void initData() {
    }

    protected void initView() {
    }

    protected void initListener() {
    }

    public void initToolBar(Toolbar toolbar, String name, boolean showHomeAsUp) {
        initToolBar(toolbar, name, showHomeAsUp, false);
    }

    public void initToolBar(Toolbar toolbar, String name, boolean showHomeAsUp, boolean isShowRight) {
        this.isShowRight = isShowRight;
        toolbar.setTitle(name);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(showHomeAsUp);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
