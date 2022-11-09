package com.wyang.study.ui.fragment_second

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.w6539android.baselib.widget.decoration.SpacingDecoration
import com.wyang.study.R
import com.wyang.study.databinding.FragmentDecoration06Binding
import com.wyang.study.ui.base.BaseFragment

/**
 * @author Yang
 * @since 2022/11/8 11:33
 * @desc
 */
class Decoration06Fragment : BaseFragment<FragmentDecoration06Binding>() {
    override fun getViewBinding(): FragmentDecoration06Binding {
        return FragmentDecoration06Binding.inflate(layoutInflater)
    }

    override fun initialize() {
        mBinding.mRecyclerView.addItemDecoration(SpacingDecoration.newDecoration())
        mBinding.mRecyclerView.adapter = mAdapter

        mAdapter.setNewData(
            mutableListOf(
                "1.测试数据",
                "2.测试数据",
                "3.测试数据",
                "4.测试数据",
                "5.测试数据",
                "6.测试数据",
                "7.测试数据",
                "8.测试数据",
                "9.测试数据",
                "10.测试数据",
                "11.测试数据",
                "12.测试数据",
                "13.测试数据",
                "14.测试数据",
                "15.测试数据",
                "16.测试数据",
            )
        )
    }

    private val mAdapter =
        object : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_decoration06_recycler) {
            override fun convert(helper: BaseViewHolder, item: String) {
                helper.setText(R.id.tv_name, item)

                val layoutParams = helper.itemView.layoutParams
                layoutParams.width = if (helper.layoutPosition % 2 == 0) 200 else 320
                helper.itemView.layoutParams = layoutParams
            }
        }
}