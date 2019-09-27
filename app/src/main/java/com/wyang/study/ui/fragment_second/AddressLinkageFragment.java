package com.wyang.study.ui.fragment_second;

import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.wyang.common.utils.node.NodeSeeker;
import com.wyang.common.utils.node.TreeNode;
import com.wyang.study.R;
import com.wyang.study.ui.base.BaseFragment;
import com.wyang.study.ui.util.TreeHelper;
import com.wyang.study.utils.AssetUtil;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by weiyang on 2019-09-22.
 */
public class AddressLinkageFragment extends BaseFragment {
    @BindView(R.id.mTabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    private TreeHelper mTreeHelper;
    private SparseArray<List<TreeNode>> data = new SparseArray<>();
    private SparseIntArray selectPos = new SparseIntArray();

    @Override

    protected int getLayoutId() {
        return R.layout.fragment_address_linkage;
    }

    @Override
    protected void initView() {
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                mAdapter.setNewData(data.get(position));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mAdapter.bindToRecyclerView(mRecyclerView);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            //记录每页选中的position
            setSelectPosition(position);

            TreeNode node = mAdapter.getData().get(position);
            List<TreeNode> results = NodeSeeker.newInstance(node).children().results();
            if (!results.isEmpty()) {
                int next = mTabLayout.getSelectedTabPosition() + 1;
                selectPos.put(next, -1);
                data.put(next, results);

                while (next + 1 < selectPos.size()) {
                    selectPos.removeAt(selectPos.size() - 1);
                    data.remove(data.size() - 1);
                }
            }

            setData();
        });

        Observable.just("1")
                .map(o -> AssetUtil.buildAddressTree(getContext()))
                .compose(this.bindUntilEvent(FragmentEvent.DESTROY))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TreeHelper>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("Idea", "数据解析中..");
                    }

                    @Override
                    public void onNext(TreeHelper treeHelper) {
                        Log.e("Idea", "数据解析完成");

                        mTreeHelper = treeHelper;

                        setData();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Idea", "数据解析失败," + e.toString());

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private int getSelectPosition() {
        return selectPos.get(mTabLayout.getSelectedTabPosition(), -1);
    }

    private void setSelectPosition(int position) {
        selectPos.put(mTabLayout.getSelectedTabPosition(), position);
    }

    private void setData() {
        int tabCount = mTabLayout.getTabCount();
        if (tabCount == 0) {
            selectPos.put(0, -1);
            data.put(0, mTreeHelper.rootSeeker().children().results());
            mAdapter.setNewData(data.get(0));
        }

        mTabLayout.removeAllTabs();
        for (int i = 0; i < selectPos.size(); i++) {
            int key = selectPos.keyAt(i);
            int position = selectPos.valueAt(i);

            if (position == -1) {
                mTabLayout.addTab(mTabLayout.newTab().setText("请选择"));
            } else {
                List<TreeNode> list = data.get(key);
                mTabLayout.addTab(mTabLayout.newTab().setText(list.get(position).getAttribute("name")));
            }
        }
        TabLayout.Tab tab = mTabLayout.getTabAt(selectPos.size() - 1);
        if (tab != null)
            tab.select();
    }

    private BaseQuickAdapter<TreeNode, BaseViewHolder> mAdapter =
            new BaseQuickAdapter<TreeNode, BaseViewHolder>(R.layout.item_address_recycler) {
                @Override
                protected void convert(BaseViewHolder helper, TreeNode item) {
                    helper.setText(R.id.tv_name, item.getAttribute("name"));

                    if (getSelectPosition() == -1)
                        helper.setVisible(R.id.iv_icon, false);
                    else if (getSelectPosition() == helper.getAdapterPosition())
                        helper.setVisible(R.id.iv_icon, true);
                    else
                        helper.setVisible(R.id.iv_icon, false);
                }
            };
}
