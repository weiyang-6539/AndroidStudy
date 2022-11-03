package com.w6539android.baselib.base.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.w6539android.baselib.global.ActivityManager
import org.greenrobot.eventbus.EventBus

/**
 * @author Yang
 * @since 2022/10/25 10:03
 * @desc 基类Activity
 */
abstract class BaseActivity : AppCompatActivity() {
    protected open fun isEventBusEnabled() = false
    protected open fun onBack(v: View) = finish()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityManager.instance.addActivity(this)
        if (isEventBusEnabled())
            EventBus.getDefault().register(this)
        setContentView()
        //修改状态栏
        initImmersionBar()

        initialize()
    }

    abstract fun setContentView()

    open fun initImmersionBar() {}

    open fun initialize() {}

    override fun onDestroy() {
        super.onDestroy()
        ActivityManager.instance.removeActivity(this)
        if (isEventBusEnabled())
            EventBus.getDefault().unregister(this)
    }
}