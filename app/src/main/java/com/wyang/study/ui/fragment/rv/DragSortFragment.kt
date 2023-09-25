package com.wyang.study.ui.fragment.rv

import androidx.recyclerview.widget.ItemTouchHelper
import com.google.android.material.snackbar.Snackbar
import com.w6539android.base.base.fragment.BaseFragment
import com.w6539android.base.ui.recycler.decoration.SpacingDecoration
import com.w6539android.base.ui.recycler.layoutmanager.ExGridLayoutManager
import com.wyang.study.R
import com.wyang.study.adapter.DragSortAdapter
import com.wyang.study.databinding.FragmentDragSortBinding
import com.wyang.study.databinding.WidgetRecyclerBinding
import com.wyang.study.ui.helper.ItemDragHelperCallback
import com.wyang.study.ui.util.DataProvider

class DragSortFragment : BaseFragment<FragmentDragSortBinding>() {
    private val mRecyclerBinding by lazy {
        WidgetRecyclerBinding.bind(mBinding.root)
    }
    private lateinit var mAdapter: DragSortAdapter

    override fun getViewBinding() = FragmentDragSortBinding.inflate(layoutInflater)

    override fun initialize() {
        mRecyclerBinding.mRecyclerView.layoutManager = ExGridLayoutManager(context, 4)
        mRecyclerBinding.mRecyclerView.addItemDecoration(
            SpacingDecoration.newDecoration()
                .setPadding(10, 10, 10, 10)
                .setSpacing(0)
        )

        //使用ItemTouchHelper
        val callback = ItemDragHelperCallback(context)
        callback.setDragListener(object : ItemDragHelperCallback.DragListener {
            override fun deleteState(isDelete: Boolean) {
                //Log.e(TAG, "isDelete:" + isDelete);
                /*if (isDelete) {
                    //tv_prompt.setBackgroundResource(R.color.holo_red_dark);
                    tv_prompt.setText(getResources().getString(R.string.post_delete_tv_s));
                } else {
                    tv_prompt.setText(getResources().getString(R.string.post_delete_tv_d));
                    //tv_prompt.setBackgroundResource(R.color.holo_red_light);
                }*/
            }

            override fun dragState(isDrag: Boolean) {
                //tv_prompt.setVisibility(isDrag ? View.VISIBLE : View.INVISIBLE);
            }
        })
        val helper = ItemTouchHelper(callback) //实现item的拖拽和滑动

        helper.attachToRecyclerView(mRecyclerBinding.mRecyclerView) //通过attachToRecyclerView方法绑定RecyclerView

        mAdapter = DragSortAdapter(helper)
        mRecyclerBinding.mRecyclerView.adapter = mAdapter
        mAdapter.setItemClickListener { _, _, position ->
            val channel = mAdapter.get(position).channel ?: return@setItemClickListener
            if (mAdapter.isEdit()) {
                if (channel.isMine) {
                    if (channel.isActivated) {
                        channel.isMine = false
                        mAdapter.removeMine(position)
                    }
                } else {
                    channel.isMine = true
                    mAdapter.addMine(position)
                }
            } else {
                if (channel.isMine) {
                    mAdapter.setCurrentPos(position)
                    mRecyclerBinding.mRecyclerView.let {
                        Snackbar.make(it, "选中频道-" + channel.name, Snackbar.LENGTH_LONG)
                            .show()
                    }
                } else {
                    channel.isMine = true
                    mAdapter.addMine(position)
                }
            }
        }

        mAdapter.addItemChildClickListener(R.id.tv_edit) { _, _, _ ->
            mAdapter.enableEdit()
        }

        var minActivatedPos = 0 //最小可拖拽的position

        var maxActivatedPos = 0 //最大可拖拽的position

        val dragSortData = DataProvider.getDragSortData()
        dragSortData.indices.forEach { i ->
            val channel = dragSortData[i].channel
            if (channel != null && channel.isMine && channel.isActivated) {
                if (minActivatedPos == 0) minActivatedPos = i
                maxActivatedPos = i
            }
        }
        mAdapter.setNewData(dragSortData)
        mAdapter.setMinActivatedPos(minActivatedPos)
        mAdapter.setMaxActivatedPos(maxActivatedPos)
    }
}