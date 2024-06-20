package com.wyang.study.ui.fragment.custom

import com.w6539android.base.base.fragment.BaseFragment
import com.wyang.study.databinding.FragmentCustomViewBinding

class CustomViewFragment : BaseFragment<FragmentCustomViewBinding>() {

    override fun initialize() {
        mBinding.cf1.setDrawLine(false)
        mBinding.cf2.setDrawLine(true)
    }
}