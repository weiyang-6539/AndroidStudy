package com.w6539.base_jetpack.base.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.w6539.base_jetpack.ActivityManager
import org.greenrobot.eventbus.EventBus

/**
 * @author Yang
 * @since 2022/12/2 11:31
 * @desc 基类Activity, 单Activity或多Activity均可用
 */
abstract class BaseActivity : AppCompatActivity() {
    protected open fun isEventBusEnabled(): Boolean = false
    protected open fun onBack(view: View) = finish()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityManager.instance.addActivity(this)
        if (isEventBusEnabled())
            EventBus.getDefault().register(this)
        initImmersionBar()

        setContentView()

        initialize()
    }

    open fun initImmersionBar() {}

    abstract fun setContentView()

    open fun initialize() {}

    override fun onDestroy() {
        super.onDestroy()
        ActivityManager.instance.removeActivity(this)
        if (isEventBusEnabled())
            EventBus.getDefault().unregister(this)
    }
}