package com.w6539.base_jetpack.base.dialog

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding
import com.w6539.base_jetpack.ext.inflateBindingWithGeneric
import org.greenrobot.eventbus.EventBus
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.math.min

/**
 * @author Yang
 * @date 2024/6/13
 * @desc
 */
abstract class BaseVBDialog<VB : ViewBinding> : DialogFragment() {
    // 日志tag
    protected open val mTag: String = javaClass.simpleName

    // ViewBinding对象
    protected open val mBinding: VB by lazy { inflateBindingWithGeneric(layoutInflater) }

    // 仅初始化一次的标识
    private val isInitialize = AtomicBoolean(false)

    // 是否开启EventBus
    protected open fun isEventBusEnabled(): Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (isEventBusEnabled())
            EventBus.getDefault().register(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isEventBusEnabled())
            EventBus.getDefault().unregister(this)
    }

    override fun onResume() {
        super.onResume()
        safeInit()
    }

    override fun onStart() {
        super.onStart()
        if (dialog != null) {
            resetDialog()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireDialog().requestWindowFeature(Window.FEATURE_NO_TITLE)
        return mBinding.root
    }

    private fun safeInit() {
        if (!isHidden) {
            if (isInitialize.compareAndSet(false, true))
                initialize()
        }
    }

    open fun initialize() {}

    private fun resetDialog() {
        val config = getConfig()
        requireDialog().window?.apply {
            // 重设属性
            attributes.gravity = config.gravity
            attributes.windowAnimations = config.animation
            // 重设宽高
            val dm = DisplayMetrics().apply {
                requireActivity().windowManager.defaultDisplay.getMetrics(this)
            }
            val width = (dm.widthPixels * min(1f, config.widthScale)).toInt()
            val height = (dm.heightPixels * min(1f, config.heightScale)).toInt()
            setLayout(width, if (height == 0) ViewGroup.LayoutParams.WRAP_CONTENT else height)
            // 支持圆角
            setBackgroundDrawableResource(android.R.color.transparent)
        }
        requireDialog().setCanceledOnTouchOutside(config.outsideFlag)
    }

    protected open fun getConfig() = Config()

    inner class Config {
        var gravity = Gravity.NO_GRAVITY
        var animation = 0
        var widthScale = .8f
        var heightScale = 0f
        var outsideFlag = true
    }
}