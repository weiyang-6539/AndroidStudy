package com.wyang.study.ui.fragment_second

import com.wyang.study.databinding.FragmentExampleBinding
import com.wyang.study.ui.base.BaseFragment

class BaseExampleFragment: BaseFragment<FragmentExampleBinding>() {
    override fun getViewBinding(): FragmentExampleBinding {
        return FragmentExampleBinding.inflate(layoutInflater)
    }

    override fun initialize() {

    }
}