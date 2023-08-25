package com.wyang.study.ui.fragment

import android.os.Build
import androidx.annotation.RequiresApi
import com.w6539android.base.base.fragment.BaseFragment
import com.wyang.study.databinding.FragmentOpenGlEsBinding

open class OpenGLESFragment : BaseFragment<FragmentOpenGlEsBinding>() {

    override fun getViewBinding() = FragmentOpenGlEsBinding.inflate(layoutInflater)

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun initialize() {
        // H264/avc的编码器
    }
}