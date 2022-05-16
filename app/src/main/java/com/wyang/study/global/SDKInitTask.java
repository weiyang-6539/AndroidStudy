package com.wyang.study.global;

import android.annotation.SuppressLint;
import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;

public class SDKInitTask implements Runnable {
    private final Context application;

    public SDKInitTask(Context application) {
        this.application = application;
    }

    @SuppressLint("VisibleForTests")
    @Override
    public void run() {
        Glide.init(application, new GlideBuilder());
    }
}
