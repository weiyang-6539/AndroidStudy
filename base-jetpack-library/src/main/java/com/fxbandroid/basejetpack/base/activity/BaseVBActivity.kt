package com.fxbandroid.basejetpack.base.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.viewbinding.ViewBinding
import com.fxbandroid.basejetpack.ext.inflateBindingWithGeneric

/**
 * @author Yang
 * @date 2024/7/2
 * @desc
 */
open class BaseVBActivity<VB : ViewBinding> : AppCompatActivity() {
    // 日志tag
    protected open val mTag: String = javaClass.simpleName

    // ViewBinding对象
    protected open val mBinding: VB by lazy { inflateBindingWithGeneric(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)

        //修改状态栏
        initImmersionBar()

        initialize()
    }

    open fun initImmersionBar() {}

    open fun initialize() {}
}