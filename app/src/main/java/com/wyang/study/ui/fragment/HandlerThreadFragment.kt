package com.wyang.study.ui.fragment

import com.w6539android.base.base.fragment.BaseFragment
import com.wyang.study.databinding.FragmentHandlerThreadBinding
import com.wyang.study.utils.DownloadTask
import java.io.File

class HandlerThreadFragment : BaseFragment<FragmentHandlerThreadBinding>() {
    private var mDownLoadTask = DownloadTask("download")

    override fun initialize() {
        val path = "https://dldir1.qq.com/weixin/android/weixin8021android2120_arm64.apk"
        val filename = "wechat.apk"
        if (context != null)
            mDownLoadTask.startDownloadTask(path, File(requireContext().filesDir, filename))
    }

    override fun onDestroy() {
        super.onDestroy()
        mDownLoadTask.quit()
    }
}