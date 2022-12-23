package com.wyang.study.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.trello.rxlifecycle4.components.support.RxFragment
import com.wyang.study.utils.LogUtils
import java.util.concurrent.atomic.AtomicBoolean

abstract class BaseFragment<T : ViewBinding> : RxFragment() {
    protected open val mTag: String = javaClass.simpleName
    protected open val mBinding by lazy { getViewBinding() }
    private val isInitialize = AtomicBoolean(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        log("onCreate")
    }

    override fun onDestroy() {
        super.onDestroy()
        log("onDestroy")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        log("onAttach")
    }

    override fun onDetach() {
        super.onDetach()
        log("onDetach")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        log("onActivityCreated")
    }

    override fun onStart() {
        super.onStart()
        log("onStart")
    }

    override fun onResume() {
        super.onResume()
        log("onResume")
    }

    override fun onPause() {
        super.onPause()
        log("onPause")
    }

    override fun onStop() {
        super.onStop()
        log("onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        log("onDestroyView")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        log("onCreateView")
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        log("onViewCreated")

        safeInit()
    }

    abstract fun getViewBinding(): T

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        safeInit()
    }

    private fun safeInit() {
        if (!isHidden) {
            if (isInitialize.compareAndSet(false, true))
                initialize()
        }
    }

    open fun initialize() {

    }

    protected fun log(method: String) {
        /*if (method.contains("on"))
            return*/
        LogUtils.e(mTag, method)
    }
}