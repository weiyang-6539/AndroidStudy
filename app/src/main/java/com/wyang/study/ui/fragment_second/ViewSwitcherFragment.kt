package com.wyang.study.ui.fragment_second

import com.wyang.study.databinding.FragmentViewSwitcherBinding
import com.wyang.study.ui.base.BaseFragment

class ViewSwitcherFragment : BaseFragment<FragmentViewSwitcherBinding>() {
    override fun getViewBinding(): FragmentViewSwitcherBinding {
        return FragmentViewSwitcherBinding.inflate(layoutInflater)
    }

    override fun initialize() {

    }
}