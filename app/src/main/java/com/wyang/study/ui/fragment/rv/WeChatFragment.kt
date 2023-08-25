package com.wyang.study.ui.fragment.rv

import com.w6539android.base.base.fragment.BaseFragment
import com.wyang.study.adapter.DiscoverAdapter
import com.wyang.study.databinding.FragmentWechatBinding
import com.wyang.study.databinding.WidgetRecyclerBinding
import com.wyang.study.ui.util.DataProvider

class WeChatFragment : BaseFragment<FragmentWechatBinding>() {
    private val mRecyclerBinding by lazy {
        WidgetRecyclerBinding.bind(mBinding.root)
    }
    private val mAdapter = DiscoverAdapter()

    override fun getViewBinding() = FragmentWechatBinding.inflate(layoutInflater)

    override fun initialize() {
        mRecyclerBinding.mRecyclerView.adapter = mAdapter
        mAdapter.submitList(DataProvider.getDiscoverData())
    }

}