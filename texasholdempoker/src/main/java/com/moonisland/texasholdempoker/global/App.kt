package com.moonisland.texasholdempoker.global

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * @author yang
 * @date 2024/6/20
 * @desc
 */
@HiltAndroidApp
class App : Application() {

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}