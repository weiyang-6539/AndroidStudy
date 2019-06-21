package com.wyang.study.ui.fragment_second;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wyang.study.R;
import com.wyang.study.adapter.DragSortAdapter;
import com.wyang.study.bean.Channel;
import com.wyang.study.bean.SectionItem;
import com.wyang.study.ui.base.BaseFragment;
import com.wyang.study.ui.helper.ItemDragHelperCallback;
import com.wyang.study.ui.util.DataProvider;

import java.util.List;

import butterknife.BindView;

/**
 * Created by weiyang on 2019/6/20.
 * 仿今日头条拖拽排序效果
 */
public class DragSortFragment extends BaseFragment {
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    private DragSortAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_drag_sort;
    }

    @Override
    protected void initView() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));

        //使用ItemTouchHelper
        ItemDragHelperCallback callback = new ItemDragHelperCallback(getContext());
        callback.setDragListener(new ItemDragHelperCallback.DragListener() {
            @Override
            public void deleteState(boolean isDelete) {
                //Log.e(TAG, "isDelete:" + isDelete);
                /*if (isDelete) {
                    //tv_prompt.setBackgroundResource(R.color.holo_red_dark);
                    tv_prompt.setText(getResources().getString(R.string.post_delete_tv_s));
                } else {
                    tv_prompt.setText(getResources().getString(R.string.post_delete_tv_d));
                    //tv_prompt.setBackgroundResource(R.color.holo_red_light);
                }*/
            }

            @Override
            public void dragState(boolean isDrag) {
                //tv_prompt.setVisibility(isDrag ? View.VISIBLE : View.INVISIBLE);
            }
        });
        final ItemTouchHelper helper = new ItemTouchHelper(callback);//实现item的拖拽和滑动
        helper.attachToRecyclerView(mRecyclerView);//通过attachToRecyclerView方法绑定RecyclerView

        mAdapter = new DragSortAdapter(helper);
        mAdapter.bindToRecyclerView(mRecyclerView);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            Channel channel = mAdapter.getData().get(position).t;
            if (channel == null)
                return;

            if (mAdapter.isEdit()) {
                if (channel.isMine()) {
                    if (channel.isActivated()) {
                        channel.setMine(false);
                        mAdapter.removeMine(position);
                    }
                } else {
                    channel.setMine(true);
                    mAdapter.addMine(position);
                }
            } else {
                if (channel.isMine()) {
                    mAdapter.setCurrentPos(position);

                    Snackbar.make(mRecyclerView, "选中频道-" + channel.getName(), Snackbar.LENGTH_LONG)
                            .show();
                } else {
                    channel.setMine(true);
                    mAdapter.addMine(position);
                }
            }

        });
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()) {
                case R.id.tv_edit:
                    mAdapter.enableEdit();
                    break;
                default:
                    break;
            }
        });

        int minActivatedPos = 0;//最小可拖拽的position
        int maxActivatedPos = 0;//最大可拖拽的position
        List<SectionItem> dragSortData = DataProvider.getDragSortData();
        for (int i = 0; i < dragSortData.size(); i++) {
            Channel channel = dragSortData.get(i).t;
            if (channel != null && channel.isMine() && channel.isActivated()) {
                if (minActivatedPos == 0)
                    minActivatedPos = i;

                maxActivatedPos = i;
            }
        }
        mAdapter.setNewData(dragSortData);
        mAdapter.setMinActivatedPos(minActivatedPos);
        mAdapter.setMaxActivatedPos(maxActivatedPos);

        Log.e(TAG, "最小编辑pos:" + minActivatedPos + ",最大编辑pos:" + maxActivatedPos);
    }
}
