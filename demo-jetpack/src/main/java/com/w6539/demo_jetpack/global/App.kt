package com.w6539.demo_jetpack.global

import android.app.Application
import com.scwang.smart.refresh.header.MaterialHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.w6539.demo_jetpack.R
import dagger.hilt.android.HiltAndroidApp

/**
 * @author Yang
 * @since 2022/12/15 10:28
 * @desc
 */
@HiltAndroidApp
class App : Application() {
    companion object {
        lateinit var instance: App

        init {
            SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
                layout.setEnableHeaderTranslationContent(true)
                MaterialHeader(context).setColorSchemeResources(
                    R.color.blue,
                    R.color.blue,
                    R.color.blue
                )
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}