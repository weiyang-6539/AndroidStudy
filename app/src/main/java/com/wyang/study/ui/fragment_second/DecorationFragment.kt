package com.wyang.study.ui.fragment_second

import androidx.recyclerview.widget.DividerItemDecoration
import com.wyang.study.databinding.FragmentDecorationBinding
import com.wyang.study.ui.base.BaseFragment

/**
 * @author Yang
 * @since 2022/11/3 15:37
 * @desc
 */
class DecorationFragment : BaseFragment<FragmentDecorationBinding>() {
    override fun getViewBinding(): FragmentDecorationBinding {
        return FragmentDecorationBinding.inflate(layoutInflater)
    }

    override fun initialize() {
    }
}