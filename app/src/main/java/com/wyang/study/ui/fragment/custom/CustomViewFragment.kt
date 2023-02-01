package com.wyang.study.ui.fragment.custom

import com.wyang.study.databinding.FragmentCustomViewBinding
import com.wyang.study.ui.base.BaseFragment

class CustomViewFragment : BaseFragment<FragmentCustomViewBinding>() {
    override fun getViewBinding(): FragmentCustomViewBinding {
        return FragmentCustomViewBinding.inflate(layoutInflater)
    }

    override fun initialize() {
        mBinding.cf1.setDrawLine(false)
        mBinding.cf2.setDrawLine(true)
    }
}