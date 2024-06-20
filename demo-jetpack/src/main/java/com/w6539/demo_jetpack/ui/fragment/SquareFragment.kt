package com.w6539.demo_jetpack.ui.fragment

import androidx.navigation.Navigation
import com.w6539.base_jetpack.base.fragment.BaseVBFragment
import com.w6539.demo_jetpack.R
import com.w6539.demo_jetpack.databinding.FragmentSquareBinding

/**
 * @author Yang
 * @since 2022/12/8 11:56
 * @desc 广场
 */
class SquareFragment : BaseVBFragment<FragmentSquareBinding>() {
    override fun initialize() {
        mBinding.tvSquare.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_fragment_main_to_fragment_second)
        )
    }
}