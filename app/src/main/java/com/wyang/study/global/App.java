package com.wyang.study.global;

import android.app.Application;

import com.wyang.study.utils.LogUtils;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        LogUtils.e("App", "onCreate()方法调用");

        InitIntentService.start(this);
    }
}
