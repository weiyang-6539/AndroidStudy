package com.wyang.study.ui.fragment

import android.graphics.Color
import android.util.TypedValue
import androidx.appcompat.widget.AppCompatTextView
import com.w6539android.base.base.fragment.BaseFragment
import com.wyang.study.databinding.FragmentFlexboxBinding

/**
 * @author Yang
 * @since 2023/11/9 11:21
 * @desc
 */
class FlexBoxFragment : BaseFragment<FragmentFlexboxBinding>() {
    private val list = arrayListOf(
        "MainActivity",
        "花开富贵",
        "1111",
        "1111",
        "滴答滴答滴答滴答滴答滴答",
        "大伤是大的的",
        "撒旦的",
        "1111",
        "1111",
        "1111",
        "1111",
        "发阿 的发是",
        "1111",
        "是的",
        "大萨达",
        "苟富贵 发发",
        "搜索",
        "大萨达都是撒旦"
    )

    override fun initialize() {
        list.forEach {
            val child = AppCompatTextView(requireContext())
            child.text = it
            child.setTextSize(TypedValue.COMPLEX_UNIT_SP,15f)
            child.setPadding(10,10,10,10)
            child.setBackgroundColor(Color.DKGRAY)
            mBinding.root.addView(child)
        }
    }
}