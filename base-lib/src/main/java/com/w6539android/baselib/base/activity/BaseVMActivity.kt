package com.w6539android.baselib.base.activity

import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.w6539android.baselib.base.vm.BaseViewModel
import com.w6539android.baselib.ext.getVMCls
import com.w6539android.baselib.net.LoadState

/**
 * @author Yang
 * @since 2022/10/26 14:08
 * @desc
 */
abstract class BaseVMActivity<VM : BaseViewModel, VB : ViewBinding> : BaseActivity() {
    protected open val mViewModel by lazy { createViewModel() }
    protected open val mBinding by lazy { getViewBinding() }

    protected open fun createViewModel(): VM {
        return ViewModelProvider(this).get(getVMCls(this))
    }

    abstract fun getViewBinding(): VB

    override fun setContentView() {
        //ViewModel添加观察
        mViewModel.getLoadState().observe(this) {
            when (it.state) {
                LoadState.NORMAL -> onStateNormal()
                LoadState.LOADING -> onStateLoading()
                LoadState.SUCCESS -> onStateSuccess(it.message)
                LoadState.EMPTY -> onStateEmpty()
                LoadState.FAILED -> onStateFailed(it.message)
            }
        }
        startObserver()

        setContentView(mBinding.root)
    }

    abstract fun startObserver()

    protected open fun onStateNormal() {}
    protected open fun onStateLoading() {}
    protected open fun onStateSuccess(message: String) {}
    protected open fun onStateEmpty() {}
    protected open fun onStateFailed(message: String) {}
}