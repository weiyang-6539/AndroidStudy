package com.fxbandroid.basejetpack.base.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.fxbandroid.basejetpack.base.vm.BaseViewModel
import com.fxbandroid.basejetpack.ext.getVMCls
import com.fxbandroid.basejetpack.net.LoadState
import java.util.concurrent.atomic.AtomicBoolean

/**
 * @author Yang
 * @date 2024/5/20
 * @desc MVVM-Fragment基类
 */
abstract class BaseVMFragment<VM : BaseViewModel, T : ViewBinding> : BaseVBFragment<T>() {
    protected open val mViewModel: VM by lazy { createViewModel() }

    //反射创建ViewModel, 子类也可以重写用其他方式实例化
    protected open fun createViewModel(): VM = ViewModelProvider(this)[getVMCls()]

    // 仅初始化一次的标识
    private val isObserver = AtomicBoolean(false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.getLoadState()
            .observe(viewLifecycleOwner) { loadState ->
                if (loadState != null) {
                    when (loadState.state) {
                        LoadState.NORMAL -> onStateNormal()
                        LoadState.LOADING -> onStateLoading(getMessage(loadState))
                        LoadState.SUCCESS -> onStateSuccess(getMessage(loadState))
                        LoadState.EMPTY -> onStateEmpty()
                        LoadState.FAILED -> onStateFailed(loadState.throwable)
                    }
                    mViewModel.getLoadState().value = null
                }
            }
        if (isObserver.compareAndSet(false, true)) {
            startObserver()
        }
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