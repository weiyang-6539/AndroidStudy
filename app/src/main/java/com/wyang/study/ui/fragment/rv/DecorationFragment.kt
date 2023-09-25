package com.wyang.study.ui.fragment.rv

import com.w6539android.base.base.fragment.BaseFragment
import com.w6539android.base.ui.recycler.BaseDifferAdapter
import com.w6539android.base.ui.recycler.BaseViewHolder
import com.w6539android.base.ui.recycler.decoration.SpacingDecoration
import com.wyang.study.R
import com.wyang.study.databinding.LayoutRecyclerBinding

/**
 * @author Yang
 * @since 2022/11/3 15:37
 * @desc
 */
class DecorationFragment : BaseFragment<LayoutRecyclerBinding>() {
    override fun getViewBinding() = LayoutRecyclerBinding.inflate(layoutInflater)

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

    private val mAdapter = object : BaseDifferAdapter<String>(
        R.layout.item_decoration_recycler
    ) {
        override fun convert(holder: BaseViewHolder, item: String) {
            holder.setText(R.id.tv_name, item)
        }
    }
}