package com.w6539.demo_jetpack.ui.fragment

import com.w6539.base_jetpack.base.fragment.BaseVBFragment
import com.w6539.demo_jetpack.databinding.FragmentMineBinding

/**
 * @author Yang
 * @since 2022/12/8 11:56
 * @desc 我的
 */
class MineFragment : BaseVBFragment<FragmentMineBinding>() {
    override fun getViewBinding(): FragmentMineBinding {
        return FragmentMineBinding.inflate(layoutInflater)
    }
}