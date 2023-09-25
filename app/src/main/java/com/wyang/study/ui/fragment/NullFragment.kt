package com.wyang.study.ui.fragment

import com.w6539android.base.base.fragment.BaseFragment
import com.wyang.study.databinding.FragmentNullBinding

class NullFragment : BaseFragment<FragmentNullBinding>() {

    override fun getViewBinding() = FragmentNullBinding.inflate(layoutInflater)
}