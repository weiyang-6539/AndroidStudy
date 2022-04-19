package com.wyang.study.ui.fragment_second;

import com.wyang.study.R;
import com.wyang.study.ui.base.BaseFragment;
import com.wyang.study.utils.DownloadTask;

import java.io.File;

public class HandlerThreadFragment extends BaseFragment {

    private DownloadTask mDownLoadTask;

    private String path = "https://dldir1.qq.com/weixin/android/weixin8021android2120_arm64.apk";
    private String filename = "wechat.apk";

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_handler_thread;
    }

    @Override
    protected void initView() {
        mDownLoadTask = new DownloadTask("download");
        //mDownLoadTask.startDownloadTask(path, new File(getContext().getFilesDir(), filename));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mDownLoadTask.quit();
    }
}
