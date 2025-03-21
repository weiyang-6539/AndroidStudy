package com.fxbandroid.basejetpack.base.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.fxbandroid.basejetpack.base.vm.BaseViewModel
import com.fxbandroid.basejetpack.ext.getVMCls
import com.fxbandroid.basejetpack.net.LoadState

/**
 * @author Yang
 * @date 2024/7/2
 * @desc
 */
open class BaseVMActivity<VM : BaseViewModel, VB : ViewBinding> : BaseVBActivity<VB>() {
    protected open val mViewModel: VM by lazy { createViewModel() }
    protected open fun createViewModel(): VM {
        return ViewModelProvider(this)[getVMCls()]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel.getLoadState()
            .observe(this) { loadState ->
                if (loadState != null) {
                    when (loadState.state) {
                        LoadState.NORMAL -> onStateNormal()
                        LoadState.LOADING -> onStateLoading(getMessage(loadState))
                        LoadState.SUCCESS -> onStateSuccess(getMessage(loadState))
                        LoadState.EMPTY -> onStateEmpty()
                        LoadState.FAILED -> onStateFailed(loadState.throwable)
                    }
                }
            }
        startObserver()
    }

    private fun getMessage(loadState: LoadState): String {
        return if (loadState.strId != 0) getString(loadState.strId) else ""
    }

    protected open fun startObserver() {}
    protected open fun onStateNormal() {}
    protected open fun onStateLoading(message: String) {}
    protected open fun onStateSuccess(message: String) {}
    protected open fun onStateEmpty() {}
    protected open fun onStateFailed(throwable: Throwable?) {}
}