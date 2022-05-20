package com.wyang.study.ui.fragment_second

import com.wyang.study.databinding.FragmentNullBinding
import com.wyang.study.ui.base.BaseFragment

class NullFragment: BaseFragment<FragmentNullBinding>() {

    override fun getViewBinding(): FragmentNullBinding {
        return FragmentNullBinding.inflate(layoutInflater)
    }
}