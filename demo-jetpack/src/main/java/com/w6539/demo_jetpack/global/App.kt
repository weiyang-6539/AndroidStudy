package com.w6539.demo_jetpack.global

import android.app.Application
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

        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}