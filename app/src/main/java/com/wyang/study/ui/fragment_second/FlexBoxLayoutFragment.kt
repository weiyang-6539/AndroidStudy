package com.wyang.study.ui.fragment_second

import com.wyang.study.databinding.FragmentFlexBoxBinding
import com.wyang.study.ui.base.BaseFragment

class FlexBoxLayoutFragment : BaseFragment<FragmentFlexBoxBinding>() {
    override fun getViewBinding(): FragmentFlexBoxBinding {
        return FragmentFlexBoxBinding.inflate(layoutInflater)
    }
}