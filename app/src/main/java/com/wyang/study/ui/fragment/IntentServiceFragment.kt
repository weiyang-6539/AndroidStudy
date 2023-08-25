package com.wyang.study.ui.fragment

import android.content.Intent
import com.w6539android.base.base.fragment.BaseFragment
import com.wyang.study.databinding.FragmentIntentServiceBinding
import com.wyang.study.ui.DownLoadIntentService

class IntentServiceFragment : BaseFragment<FragmentIntentServiceBinding>() {
    private val path = "https://dldir1.qq.com/weixin/android/weixin8021android2120_arm64.apk"

    override fun getViewBinding() = FragmentIntentServiceBinding.inflate(layoutInflater)

    override fun initialize() {
        startDownload()
    }

    private fun startDownload() {
        val context = requireContext().applicationContext
        val intent = Intent(context, DownLoadIntentService::class.java)
        intent.putExtra("path", path)
        context.startService(intent)
    }

    private fun stopDownload() {
        val context = requireContext().applicationContext
        val intent = Intent(context, DownLoadIntentService::class.java)
        intent.putExtra("path", path)
        context.stopService(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        stopDownload()
    }
}