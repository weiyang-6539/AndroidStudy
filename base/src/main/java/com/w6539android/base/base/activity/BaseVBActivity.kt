package com.w6539android.base.base.activity

import androidx.viewbinding.ViewBinding
import com.w6539android.base.ext.inflateBindingWithGeneric

/**
 * @author Yang
 * @since 2022/10/26 13:57
 * @desc 支持ViewBindingActivity
 */
abstract class BaseVBActivity<VB : ViewBinding> : BaseActivity() {
    protected open val mBinding by lazy { inflateBindingWithGeneric<VB>(layoutInflater) }

    override fun setContentView() {
        setContentView(mBinding.root)
    }
}