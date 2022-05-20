package com.wyang.study.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.trello.rxlifecycle4.components.support.RxFragment

abstract class BaseFragment<T : ViewBinding> : RxFragment() {
    protected open val mTag: String = javaClass.simpleName
    protected open var mBinding: T? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (mBinding == null) {
            mBinding = getViewBinding()
        }
        return mBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize();
    }

    abstract fun getViewBinding(): T

    open fun initialize() {

    }
}