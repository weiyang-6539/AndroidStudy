package com.moonisland.texasholdempoker.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import androidx.lifecycle.lifecycleScope
import com.moonisland.texasholdempoker.databinding.ActivitySplashBinding
import com.w6539android.base.base.activity.BaseVBActivity
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

    override fun initialize() {
        Executors.newSingleThreadExecutor()
        mBinding.ivBg.startAnimation(scaleAnimation)
        lifecycleScope.launch {
            delay(animDuration)
            startActivity(Intent(this@SplashActivity, NavHostActivity::class.java))
        }
    }
}