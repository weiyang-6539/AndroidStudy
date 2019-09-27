package com.wyang.study.ui.fragment_second;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.wyang.common.utils.node.NodeSeeker;
import com.wyang.common.utils.node.TreeNode;
import com.wyang.study.R;
import com.wyang.study.ui.base.BaseFragment;
import com.wyang.study.ui.util.TreeHelper;
import com.wyang.study.utils.AssetUtil;

import java.io.FileFilter;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by weiyang on 2019-09-27.
 */
public class AddressFilterFragment extends BaseFragment {
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    private TreeHelper mTreeHelper;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_address_filter;
    }

    @Override
    protected void initView() {

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

                        //数据初始化完才init
                        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                        mAdapter.bindToRecyclerView(mRecyclerView);
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

    @OnClick(R.id.tv_filter1)
    public void onClickFilter1() {
        if (!init())
            return;

        List<TreeNode> results = mTreeHelper.provincesSeeker()
                .attribute("code", "43")
                .descendants()
                .results();

        mAdapter.setNewData(results);
    }

    @OnClick(R.id.tv_filter2)
    public void onClickFilter2() {
        if (!init())
            return;

        List<TreeNode> results = mTreeHelper.rootSeeker()
                .descendants()
                .matchPredicate(treeNode -> {
                    String name = treeNode.getAttribute("name");
                    return !TextUtils.isEmpty(name) && name.contains("长沙");
                })
                .results();

        mAdapter.setNewData(results);

    }

    @OnClick(R.id.tv_filter3)
    public void onClickFilter3() {
        if (!init())
            return;

        List<TreeNode> results = mTreeHelper.streetsSeeker()
                .attribute("name", "新开镇")
                .results();

        mAdapter.setNewData(results);
    }

    private boolean init() {
        if (mTreeHelper == null) {
            Toast.makeText(getContext(), "数据初始化中..", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private BaseQuickAdapter<TreeNode, BaseViewHolder> mAdapter =
            new BaseQuickAdapter<TreeNode, BaseViewHolder>(R.layout.item_address_filter_recycler) {
                @Override
                protected void convert(BaseViewHolder helper, TreeNode item) {
                    helper.setText(R.id.tv_name, item.getAttribute("name"));
                    helper.setText(R.id.tv_code, item.getAttribute("code"));
                }
            };
}
