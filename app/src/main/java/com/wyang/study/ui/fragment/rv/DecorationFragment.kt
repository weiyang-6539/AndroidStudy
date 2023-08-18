package com.wyang.study.ui.fragment.rv

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.w6539android.base.widget.decoration.SpacingDecoration
import com.wyang.study.R
import com.wyang.study.databinding.LayoutRecyclerBinding
import com.wyang.study.ui.base.BaseFragment

/**
 * @author Yang
 * @since 2022/11/3 15:37
 * @desc
 */
class DecorationFragment : BaseFragment<LayoutRecyclerBinding>() {
    override fun getViewBinding(): LayoutRecyclerBinding {
        return LayoutRecyclerBinding.inflate(layoutInflater)
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
            )
        )
    }

    private val mAdapter =
        object : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_decoration_recycler) {
            override fun convert(helper: BaseViewHolder, item: String) {
                helper.setText(R.id.tv_name, item)
            }
        }
}