package com.wyang.study.ui.fragment.custom

import com.w6539android.base.base.fragment.BaseFragment
import com.wyang.study.databinding.FragmentWeiboBinding

/**
 * @author Yang
 * @since 2022/8/8 10:01
 * @desc 仿微博话题
 */
class WeiBoFragment : BaseFragment<FragmentWeiboBinding>() {
    override fun getViewBinding() = FragmentWeiboBinding.inflate(layoutInflater)
    override fun initialize() {
        mBinding.mWebView.loadUrl("https://kouyuxia.oss-cn-beijing.aliyuncs.com/adm_13/2022-08-05/94810862ecce67364c40.98742118.png")
    }
}