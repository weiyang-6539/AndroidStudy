package com.wyang.study.ui.fragment.custom

import com.wyang.study.databinding.FragmentPrivacyBinding
import com.wyang.study.ui.base.BaseFragment

/**
 * @author Yang
 * @since 2023/1/4 15:04
 * @desc 隐私政策自定义控件的实现
 */
class PrivacyFragment : BaseFragment<FragmentPrivacyBinding>() {
    override fun getViewBinding(): FragmentPrivacyBinding {
        return FragmentPrivacyBinding.inflate(layoutInflater)
    }

    override fun initialize() {
    }
}