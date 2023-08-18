package com.w6539android.base.base.activity

import androidx.viewbinding.ViewBinding

/**
 * @author Yang
 * @since 2022/10/26 13:57
 * @desc 支持ViewBindingActivity
 */
abstract class BaseVBActivity<VB : ViewBinding> : BaseActivity() {
    protected open val mBinding by lazy { getViewBinding() }

    abstract fun getViewBinding(): VB

    override fun setContentView() {
        setContentView(mBinding.root)
    }
}