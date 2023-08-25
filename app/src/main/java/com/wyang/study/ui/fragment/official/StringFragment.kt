package com.wyang.study.ui.fragment.official

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.BackgroundColorSpan
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Toast
import com.w6539android.base.base.fragment.BaseFragment
import com.wyang.study.databinding.FragmentStringBinding

/*
 * @author Yang
 * @since 2023/1/6 15:40
 * @desc
 */
class StringFragment : BaseFragment<FragmentStringBinding>() {
    private val ssb = SpannableStringBuilder()
    private val s1 = "点击确定即代表您已知悉该基金的"
    private val s2 = "产品概要"
    private val s3 = "和"
    private val s4 = "投资人权益须知"
    private val s5 = "等相关内容，从"
    private val s6 = "中国银行储蓄卡（尾号5324）"
    private val s7 = "扣款，卖出费率详见"
    private val s8 = "交易规则"
    private val s9 = "。"

    override fun getViewBinding() = FragmentStringBinding.inflate(layoutInflater)

    override fun initialize() {
        ssb.append(s1)
            .append(s2)
            .append(s3)
            .append(s4)
            .append(s5)
            .append(s6)
            .append(s7)
            .append(s8)
            .append(s9)

        ssb.setSpan(BackgroundColorSpan(Color.RED), 15, 19, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        ssb.setSpan(object : ClickableSpan() {
            override fun onClick(widget: View) {
                Toast.makeText(requireContext(), "点击Span", Toast.LENGTH_SHORT).show()
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = false
            }
        }, 15, 19, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)

        mBinding.tvPrivacy.movementMethod = LinkMovementMethod.getInstance()
        mBinding.tvPrivacy.highlightColor = Color.TRANSPARENT
        mBinding.tvPrivacy.text = ssb
    }
}