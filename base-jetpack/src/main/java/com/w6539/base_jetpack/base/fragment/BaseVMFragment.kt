package com.w6539.base_jetpack.base.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.w6539.base_jetpack.base.vm.BaseViewModel
import com.w6539.base_jetpack.net.LoadState
import java.lang.reflect.ParameterizedType

/**
 * @author Yang
 * @since 2022/12/6 11:37
 * @desc MVVM-Fragment基类
 */
abstract class BaseVMFragment<VM : BaseViewModel, T : ViewBinding> : BaseVBFragment<T>() {
    protected open val mViewModel: VM by lazy { createViewModel() }

    //反射创建ViewModel, 子类也可以重写用其他方式实例化
    protected open fun createViewModel(): VM = ViewModelProvider(this).get(
        (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VM>
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.getLoadState().observe(viewLifecycleOwner) { loadState ->
            if (loadState != null) {
                when (loadState.state) {
                    LoadState.NORMAL -> onStateNormal()
                    LoadState.LOADING -> onStateLoading()
                    LoadState.SUCCESS -> onStateSuccess(getString(loadState.strId))
                    LoadState.EMPTY -> onStateEmpty()
                    LoadState.FAILED -> onStateFailed(getString(loadState.strId))
                }
            }
        }
        startObserver()
    }

    abstract fun startObserver()

    protected open fun onStateNormal() {}
    protected open fun onStateLoading() {}
    protected open fun onStateSuccess(message: String) {}
    protected open fun onStateEmpty() {}
    protected open fun onStateFailed(message: String) {}
}