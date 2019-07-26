package com.wyang.study.ui.fragment_second;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wyang.study.R;
import com.wyang.study.adapter.DiscoverAdapter;
import com.wyang.study.ui.base.BaseFragment;
import com.wyang.study.ui.util.DataProvider;

import butterknife.BindView;

/**
 * Created by weiyang on 2019/6/21.
 * 仿微信朋友圈
 */
public class WeChatFragment extends BaseFragment {
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    private DiscoverAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wechat;
    }

    @Override
    protected void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mAdapter = new DiscoverAdapter();
        mAdapter.bindToRecyclerView(mRecyclerView);
        mAdapter.setNewData(DataProvider.getDiscoverData());
    }
}
