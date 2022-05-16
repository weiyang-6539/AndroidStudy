package com.wyang.study.ui.fragment_second;

import android.content.Context;
import android.content.Intent;

import com.wyang.study.R;
import com.wyang.study.ui.DownLoadIntentService;
import com.wyang.study.ui.base.BaseFragment;

public class IntentServiceFragment extends BaseFragment {
    private final String path = "https://dldir1.qq.com/weixin/android/weixin8021android2120_arm64.apk";

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_intent_service;
    }

    @Override
    protected void initView() {
        startDownload();
    }

    private void startDownload() {
        Context context = getContext().getApplicationContext();
        Intent intent = new Intent(context, DownLoadIntentService.class);
        intent.putExtra("path", path);
        context.startService(intent);
    }

    private void stopDownload() {
        Context context = getContext().getApplicationContext();
        Intent intent = new Intent(context, DownLoadIntentService.class);
        intent.putExtra("path", path);
        context.stopService(intent);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        stopDownload();
    }
}
