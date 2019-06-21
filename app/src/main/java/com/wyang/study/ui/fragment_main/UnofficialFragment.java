package com.wyang.study.ui.fragment_main;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wyang.study.R;
import com.wyang.study.adapter.SimpleAdapter;
import com.wyang.study.bean.Simple;
import com.wyang.study.ui.FragmentActivity;
import com.wyang.study.ui.base.BaseFragment;
import com.wyang.study.ui.util.ActivityUtils;
import com.wyang.study.ui.util.DataProvider;

import butterknife.BindView;

/**
 * Created by weiyang on 2019/6/19.
 */
public class UnofficialFragment extends BaseFragment {
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    private SimpleAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_unofficial;
    }

    @Override
    protected void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mAdapter = new SimpleAdapter();
        mAdapter.bindToRecyclerView(mRecyclerView);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            Simple simple = mAdapter.getData().get(position);

            Bundle bundle = new Bundle();
            bundle.putString("className", simple.getClassName());
            ActivityUtils.startActivity(getContext(), FragmentActivity.class, bundle);
        });

        mAdapter.setNewData(DataProvider.getUnofficialData());
    }
}
