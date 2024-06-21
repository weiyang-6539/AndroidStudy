package com.moonisland.texasholdempoker.ui.base

import androidx.viewbinding.ViewBinding
import com.moonisland.texasholdempoker.ext.toast
import com.w6539.base_jetpack.base.fragment.BaseVMFragment
import com.w6539.base_jetpack.base.vm.BaseViewModel

/**
 * @author Yang
 * @date 2024/6/21
 * @desc
 */
open class BaseTexasFragment<VB : BaseViewModel, VM : ViewBinding> : BaseVMFragment<VB, VM>() {
    override fun onStateFailed(exception: Exception?) {
        // 异常统一处理
        toast(exception?.message.toString())
    }
}