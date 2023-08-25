package com.w6539.demo_jetpack.ui.home

import androidx.paging.LoadState
import com.scwang.smart.refresh.layout.constant.RefreshState
import com.w6539.base_jetpack.base.fragment.BaseVMFragment
import com.w6539.demo_jetpack.databinding.FragmentRecommendBinding
import com.w6539.demo_jetpack.mvvm.vm.HomeViewModel
import com.w6539.demo_jetpack.ui.FooterAdapter
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author Yang
 * @since 2022/12/9 10:22
 * @desc
 */
@AndroidEntryPoint
class RecommendFragment : BaseVMFragment<HomeViewModel, FragmentRecommendBinding>() {
    private val mAdapter = RecommendAdapter()

    override fun getViewBinding(): FragmentRecommendBinding {
        return FragmentRecommendBinding.inflate(layoutInflater)
    }

    override fun initialize() {
        mBinding.mRecyclerView.setHasFixedSize(true)
        mBinding.mRecyclerView.adapter =
            mAdapter.withLoadStateFooter(FooterAdapter(mAdapter::retry))
        mBinding.mRefreshLayout.setOnRefreshListener {
            mAdapter.refresh()
        }
        addLoadStateListener()

        mViewModel.inject {
            mAdapter.submitData(it)
        }
        mAdapter.snapshot()
    }

    override fun startObserver() {}

    private fun addLoadStateListener() {
        mAdapter.addLoadStateListener {
            when (it.refresh) {
                is LoadState.NotLoading -> {
                    onStateSuccess("")
                    if (it.source.append.endOfPaginationReached) {
                        mBinding.mRefreshLayout.setEnableLoadMore(true)
                        mBinding.mRefreshLayout.finishLoadMoreWithNoMoreData()
                    } else {
                        mBinding.mRefreshLayout.setEnableLoadMore(false)
                    }
                }
                is LoadState.Loading -> {
                    if (mBinding.mRefreshLayout.state != RefreshState.Refreshing) {
                        onStateLoading()
                    }
                }
                is LoadState.Error -> {
                    val state = it.refresh as LoadState.Error
                    onStateFailed("报错")
                }
            }
        }

        mAdapter.addLoadStateListener {
            when (it.append) {
                is LoadState.NotLoading -> {
                    if (it.source.append.endOfPaginationReached) {
                        mBinding.mRefreshLayout.setEnableLoadMore(true)
                        mBinding.mRefreshLayout.finishLoadMoreWithNoMoreData()
                    } else {
                        mBinding.mRefreshLayout.setEnableLoadMore(false)
                    }
                }
                is LoadState.Loading -> {

                }
                is LoadState.Error -> {
                    val state = it.append as LoadState.Error

                }
            }
        }
    }

    override fun onStateSuccess(message: String) {
        super.onStateSuccess(message)
        mBinding.mRefreshLayout.finishRefresh()
    }

    override fun onStateFailed(message: String) {
        super.onStateFailed(message)
        mBinding.mRefreshLayout.finishRefresh()
    }
}