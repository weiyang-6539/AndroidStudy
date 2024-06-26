package com.w6539.demo_jetpack.ui.fragment

import com.w6539.base_jetpack.PagerFragmentAdapter
import com.w6539.base_jetpack.base.fragment.BaseVBFragment
import com.w6539.demo_jetpack.R
import com.w6539.demo_jetpack.databinding.FragmentHomeBinding
import com.w6539.demo_jetpack.ext.bind
import com.w6539.demo_jetpack.ui.daily.DailyFragment
import com.w6539.demo_jetpack.ui.home.RecommendFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author Yang
 * @since 2022/12/7 17:07
 * @desc 首页
 */
@AndroidEntryPoint
class HomeFragment : BaseVBFragment<FragmentHomeBinding>() {

    override fun onResume() {
        super.onResume()
        onHiddenChanged(false)
    }

    override fun onStart() {
        super.onStart()
        mBinding.mViewPager.apply {
            adapter?.notifyItemChanged(currentItem)
        }
    }

    override fun initialize() {
        mBinding.mViewPager.adapter = PagerFragmentAdapter(this)
            .apply {
                fragments.add(RecommendFragment())
                fragments.add(SquareFragment())
                fragments.add(DailyFragment())
            }
        mBinding.mViewPager.isSaveEnabled = false
        mBinding.mTabLayout.bind(
            mBinding.mViewPager, mutableListOf(
                getString(R.string.tab_recommend),
                getString(R.string.tab_follow),
                getString(R.string.tab_daily),
            )
        )
    }
}