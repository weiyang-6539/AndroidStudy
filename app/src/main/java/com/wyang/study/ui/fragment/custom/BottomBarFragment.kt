package com.wyang.study.ui.fragment.custom

import com.w6539android.base.base.fragment.BaseFragment
import com.wyang.study.databinding.FragmentBottomBarBinding

/**
 * @author Yang
 * @since 2022/10/27 11:12
 * @desc
 */
class BottomBarFragment : BaseFragment<FragmentBottomBarBinding>() {
    override fun getViewBinding() = FragmentBottomBarBinding.inflate(layoutInflater)

}