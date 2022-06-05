package com.wyang.study.ui.fragment_second

import android.os.Build
import android.os.Looper
import android.util.Printer
import androidx.annotation.RequiresApi
import com.wyang.study.databinding.FragmentOpenGlEsBinding
import com.wyang.study.ui.base.BaseFragment

open class OpenGLESFragment : BaseFragment<FragmentOpenGlEsBinding>() {

    override fun getViewBinding(): FragmentOpenGlEsBinding {
        return FragmentOpenGlEsBinding.inflate(layoutInflater)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun initialize() {
        // H264/avc的编码器
    }


}