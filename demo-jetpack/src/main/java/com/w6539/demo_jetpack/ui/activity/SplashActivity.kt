package com.w6539.demo_jetpack.ui.activity

import android.annotation.SuppressLint
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import androidx.lifecycle.lifecycleScope
import com.w6539.base_jetpack.base.activity.BaseVBActivity
import com.w6539.demo_jetpack.databinding.ActivitySplashBinding
import com.moonisland.texasholdempoker.ext.navigate
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

/**
 * @author Yang
 * @since 2022/12/7 8:51
 * @desc 引导页
 */
@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseVBActivity<ActivitySplashBinding>() {
    private val animDuration = 3000L
    private val alphaAnimation by lazy {
        AlphaAnimation(.5f, 1f).apply {
            duration = animDuration
            fillAfter = true
        }
    }
    private val scaleAnimation by lazy {
        ScaleAnimation(
            1f, 1.05f, 1f, 1.05f,
            Animation.RELATIVE_TO_SELF, .5f,
            Animation.RELATIVE_TO_SELF, .5f
        ).apply {
            duration = animDuration
            fillAfter = true
        }
    }

    override fun getViewBinding(): ActivitySplashBinding {
        return ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun initialize() {Executors.newSingleThreadExecutor()
        mBinding.ivBg.startAnimation(scaleAnimation)
        mBinding.ivLogo.startAnimation(alphaAnimation)
        lifecycleScope.launch {
            delay(animDuration)
            navigate<NavHostActivity>().finish()
        }
    }
}