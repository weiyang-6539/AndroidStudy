package com.w6539android.base.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.w6539android.base.ext.inflateBindingWithGeneric
import org.greenrobot.eventbus.EventBus
import java.util.concurrent.atomic.AtomicBoolean

abstract class BaseFragment<T : ViewBinding> : Fragment() {
    protected open val mTag: String = javaClass.simpleName
    protected open val mBinding by lazy { inflateBindingWithGeneric<T>(layoutInflater) }
    protected open val isInitialize = AtomicBoolean(false)

    override fun onCreate(savedInstanceState: Bundle?) {
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
        savedInstanceState: Bundle?,
    ): View? {
        return mBinding.root
    }


    private fun safeInit() {
        if (!isHidden) {
            if (isInitialize.compareAndSet(false, true))
                initialize()
        }
    }

    open fun initialize() {}

    protected open fun isEventBusEnabled() = false
}