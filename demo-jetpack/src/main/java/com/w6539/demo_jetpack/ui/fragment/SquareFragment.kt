package com.w6539.demo_jetpack.ui.fragment

import com.w6539.base_jetpack.base.fragment.BaseVBFragment
import com.w6539.demo_jetpack.databinding.FragmentSquareBinding

/**
 * @author Yang
 * @since 2022/12/8 11:56
 * @desc 广场
 */
class SquareFragment : BaseVBFragment<FragmentSquareBinding>() {
    override fun getViewBinding(): FragmentSquareBinding {
        return FragmentSquareBinding.inflate(layoutInflater)
    }
}