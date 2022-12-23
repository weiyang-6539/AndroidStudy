package com.w6539.base_jetpack.base.activity

import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.w6539.base_jetpack.base.vm.BaseViewModel
import com.w6539.base_jetpack.net.LoadState
import java.lang.reflect.ParameterizedType

/**
 * @author Yang
 * @since 2022/12/5 10:45
 * @desc MVVM-Activity基类
 */
abstract class BaseVMActivity<VM : BaseViewModel, VB : ViewBinding> : BaseActivity() {
    protected open val mViewModel by lazy { createViewModel() }
    protected open val mBinding by lazy { getViewBinding() }

    //反射创建ViewModel, 子类也可以重写用其他方式实例化
    protected open fun createViewModel(): VM = ViewModelProvider(this)[
            (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VM>
    ]

    abstract fun getViewBinding(): VB

    override fun setContentView() {
        //ViewModel添加观察
        mViewModel.getLoadState().observe(this) {
            when (it.state) {
                LoadState.NORMAL -> onStateNormal()
                LoadState.LOADING -> onStateLoading()
                LoadState.SUCCESS -> onStateSuccess(getString(it.strId))
                LoadState.EMPTY -> onStateEmpty()
                LoadState.FAILED -> onStateFailed(getString(it.strId))
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