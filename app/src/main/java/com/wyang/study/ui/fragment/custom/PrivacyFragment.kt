package com.wyang.study.ui.fragment.custom

import com.w6539android.base.base.fragment.BaseFragment
import com.wyang.study.databinding.FragmentPrivacyBinding

/**
 * @author Yang
 * @since 2023/1/4 15:04
 * @desc 隐私政策自定义控件的实现
 */
class PrivacyFragment : BaseFragment<FragmentPrivacyBinding>() {
    override fun getViewBinding() = FragmentPrivacyBinding.inflate(layoutInflater)
    override fun initialize() {
    }
}