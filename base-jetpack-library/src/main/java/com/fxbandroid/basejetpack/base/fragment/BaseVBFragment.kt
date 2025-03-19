package com.fxbandroid.basejetpack.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.fxbandroid.basejetpack.ext.inflateBindingWithGeneric
import org.greenrobot.eventbus.EventBus
import java.util.concurrent.atomic.AtomicBoolean

/**
 * @author yang
 * @date 2024/5/17
 * @desc 支持ViewBinding的Fragment基类
 */
abstract class BaseVBFragment<VB : ViewBinding> : Fragment() {
    // 日志tag
    protected open val mTag: String = javaClass.simpleName

    // ViewBinding对象
    protected open val mBinding: VB by lazy { inflateBindingWithGeneric(layoutInflater) }

    // 仅初始化一次的标识
    protected val isInitialize = AtomicBoolean(false)

    fun getRoot() = mBinding.root

    // 是否开启EventBus
    protected open fun isEventBusEnabled(): Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (isEventBusEnabled())
            EventBus.getDefault()
                .register(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isEventBusEnabled())
            EventBus.getDefault()
                .unregister(this)
    }

    override fun onResume() {
        super.onResume()
        safeInit()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return mBinding.root
    }

    private fun safeInit() {
        if (!isHidden) {
            if (isInitialize.compareAndSet(false, true))
                initialize()
        }
    }

    open fun initialize() {}
}