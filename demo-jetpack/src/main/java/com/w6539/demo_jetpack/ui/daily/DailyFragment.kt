package com.w6539.demo_jetpack.ui.daily

import com.w6539.base_jetpack.base.fragment.BaseVMFragment
import com.w6539.demo_jetpack.databinding.FragmentRecommendBinding
import com.w6539.demo_jetpack.mvvm.vm.DailyViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author Yang
 * @since 2022/12/22 16:31
 * @desc
 */
@AndroidEntryPoint
class DailyFragment : BaseVMFragment<DailyViewModel, FragmentRecommendBinding>() {
    override fun getViewBinding(): FragmentRecommendBinding {
        return FragmentRecommendBinding.inflate(layoutInflater)
    }

    override fun initialize() {
        with(mBinding) {
        }


        mViewModel.queryDaily()
    }

    override fun startObserver() {
    }
}