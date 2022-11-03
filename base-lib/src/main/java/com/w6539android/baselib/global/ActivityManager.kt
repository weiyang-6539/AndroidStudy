package com.w6539android.baselib.global

import android.app.Activity
import android.content.Context
import java.lang.Exception
import java.util.*

/**
 * @author Yang
 * @since 2022/6/30 9:46
 * @desc Activity管理类
 */
open class ActivityManager private constructor() {
    private object Holder {
        val instance = ActivityManager()
    }

    companion object {
        val instance = Holder.instance
    }

    private val activityStack = Stack<Activity>()

    /**
     * 添加Activity到堆栈
     */
    fun addActivity(activity: Activity) {
        activityStack.push(activity)
    }

    /**
     * 从堆栈移除Activity
     */
    fun removeActivity(activity: Activity) {
        activityStack.remove(activity)
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    fun currentActivity(): Activity {
        return activityStack.lastElement()
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    fun finishActivity() {
        finishActivity(activityStack.lastElement())
    }

    /**
     * 结束指定的Activity
     */
    open fun finishActivity(activity: Activity?) {
        if (activity != null) {
            activityStack.remove(activity)
            activity.finish()
        }
    }

    /**
     * 结束指定类名的Activity
     */
    fun finishActivity(cls: Class<*>) {
        val it = activityStack.iterator()
        while (it.hasNext()) {
            val activity = it.next()
            if (activity.javaClass == cls) {
                activity.finish()
                it.remove()
            }
        }
    }

    /**
     * 结束所有Activity
     */
    open fun finishAllActivity() {
        activityStack.forEach { it.finish() }
        activityStack.clear()
    }

    /**
     * 结束除cls之外的所有Activity
     */
    fun finishAllExcept(cls: Class<*>) {
        val it = activityStack.iterator()
        while (it.hasNext()) {
            val activity = it.next()
            if (activity.javaClass != cls) {
                activity.finish()
                it.remove()
            }
        }
    }

    /**
     * 结束所有Activity
     */
    fun finishAllExcept() {
        val it = activityStack.iterator()
        while (it.hasNext()) {
            val activity = it.next()
            activity.finish()
            it.remove()
        }
    }

    /**
     * 退出应用程序
     */
    open fun exit(context: Context) {
        try {
            finishAllActivity()
        } catch (ignored: Exception) {
        }
    }
}