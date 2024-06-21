package com.w6539.demo_jetpack.ui.daily

import androidx.paging.LoadState
import com.scwang.smart.refresh.layout.constant.RefreshState
import com.w6539.base_jetpack.base.fragment.BaseVMFragment
import com.w6539.demo_jetpack.databinding.FragmentRecommendBinding
import com.w6539.demo_jetpack.mvvm.vm.DailyViewModel
import com.w6539.demo_jetpack.ui.FooterAdapter
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author Yang
 * @since 2022/12/22 16:31
 * @desc
 */
@AndroidEntryPoint
class DailyFragment : BaseVMFragment<DailyViewModel, FragmentRecommendBinding>() {
    private val mAdapter = DailyAdapter()

    override fun initialize() {
        mBinding.mRecyclerView.setHasFixedSize(true)
        mBinding.mRecyclerView.adapter =
            mAdapter.withLoadStateFooter(FooterAdapter(mAdapter::retry))
        mBinding.mRefreshLayout.setOnRefreshListener {
            mAdapter.refresh()
        }

        mViewModel.inject { mAdapter.submitData(it) }
    }

    override fun startObserver() {
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
                        onStateLoading("")
                    }
                }
                is LoadState.Error -> {
                    val state = it.refresh as LoadState.Error
                    onStateFailed(Exception(state.error))
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
                    onStateFailed(Exception(state.error))
                }
            }
        }
    }


    override fun onStateSuccess(message: String) {
        super.onStateSuccess(message)
        mBinding.mRefreshLayout.finishRefresh()
    }

    override fun onStateFailed(exception: Exception?) {
        super.onStateFailed(exception)
        mBinding.mRefreshLayout.finishRefresh()
    }
}