package com.wyang.study.global;

import android.app.Application;
import android.content.Context;
import androidx.multidex.MultiDex;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

public class App extends Application {
    private RefWatcher refWatcher;

    public static RefWatcher getRefWatcher(Context context) {
        App app = (App) context.getApplicationContext();
        return app.refWatcher;
    }

    @Override
    public void registerActivityLifecycleCallbacks(ActivityLifecycleCallbacks callback) {
        super.registerActivityLifecycleCallbacks(callback);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        InitIntentService.start(this);
        if (!LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        refWatcher = LeakCanary.install(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
