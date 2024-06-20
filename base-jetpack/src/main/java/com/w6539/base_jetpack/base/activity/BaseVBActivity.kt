package com.w6539.base_jetpack.base.activity

import androidx.viewbinding.ViewBinding
import com.w6539.base_jetpack.ext.inflateBindingWithGeneric

/**
 * @author Yang
 * @since 2022/12/5 10:30
 * @desc 支持ViewBinding的Activity
 */
abstract class BaseVBActivity<VB : ViewBinding> : BaseActivity() {
    protected open val mBinding by lazy { inflateBindingWithGeneric<VB>(layoutInflater) }

    override fun setContentView() {
        setContentView(mBinding.root)
    }
}