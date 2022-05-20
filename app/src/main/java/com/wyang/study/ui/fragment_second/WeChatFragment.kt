package com.wyang.study.ui.fragment_second

import com.wyang.study.adapter.DiscoverAdapter
import com.wyang.study.databinding.FragmentWechatBinding
import com.wyang.study.databinding.WidgetRecyclerBinding
import com.wyang.study.ui.base.BaseFragment
import com.wyang.study.ui.util.DataProvider

class WeChatFragment : BaseFragment<FragmentWechatBinding>() {
    private var mRecyclerBinding: WidgetRecyclerBinding? = null
    private val mAdapter: DiscoverAdapter = DiscoverAdapter()

    override fun getViewBinding(): FragmentWechatBinding {
        return FragmentWechatBinding.inflate(layoutInflater)
    }

    override fun initialize() {
        mBinding?.root?.let { mRecyclerBinding = WidgetRecyclerBinding.bind(it) }

        mAdapter.bindToRecyclerView(mRecyclerBinding?.mRecyclerView)
        mAdapter.setNewData(DataProvider.getDiscoverData())
    }

}