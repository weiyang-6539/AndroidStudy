package com.w6539.base_jetpack.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import org.greenrobot.eventbus.EventBus
import java.util.concurrent.atomic.AtomicBoolean

/**
 * @author Yang
 * @since 2022/12/5 14:08
 * @desc 支持ViewBinding的Fragment基类
 */
abstract class BaseVBFragment<T : ViewBinding> : Fragment() {
    // 日志tag
    protected open val mTag: String = javaClass.simpleName

    // ViewBinding对象
    protected open val mBinding by lazy { getViewBinding() }

    // 仅初始化一次的标识
    private val isInitialize = AtomicBoolean(false)

    // 是否开启EventBus
    protected open fun isEventBusEnabled(): Boolean = false

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (isEventBusEnabled())
            EventBus.getDefault().register(this)
    }

    override fun onResume() {
        super.onResume()
        safeInit()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isEventBusEnabled())
            EventBus.getDefault().unregister(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return mBinding.root
    }

    abstract fun getViewBinding(): T

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        safeInit()
    }

    private fun safeInit() {
        if (!isHidden) {
            if (isInitialize.compareAndSet(false, true))
                initialize()
        }
    }

    open fun initialize() {}
}