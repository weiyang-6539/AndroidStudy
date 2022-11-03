package com.wyang.study.ui.fragment_second

import com.wyang.study.databinding.FragmentBottomBarBinding
import com.wyang.study.ui.base.BaseFragment

/**
 * @author Yang
 * @since 2022/10/27 11:12
 * @desc
 */
class BottomBarFragment: BaseFragment<FragmentBottomBarBinding>() {
    override fun getViewBinding(): FragmentBottomBarBinding {
        return FragmentBottomBarBinding.inflate(layoutInflater)
    }
}