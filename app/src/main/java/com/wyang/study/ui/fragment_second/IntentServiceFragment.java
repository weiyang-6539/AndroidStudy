package com.wyang.study.ui.fragment_second;

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
        Intent intent = new Intent(getActivity(), DownLoadIntentService.class);
        intent.putExtra("path", path);
        getActivity().startService(intent);
    }

    private void stopDownload() {
        Intent intent = new Intent(getActivity(), DownLoadIntentService.class);
        intent.putExtra("path", path);
        getActivity().stopService(intent);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        stopDownload();
    }
}
