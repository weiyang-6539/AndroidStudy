package com.w6539.demo_jetpack.ui.utils

import android.graphics.Typeface
import com.w6539.demo_jetpack.global.App

/**
 * @author Yang
 * @since 2022/12/21 10:38
 * @desc
 */
object TypefaceUtil {
    const val FZLL_TYPEFACE = 1

    const val FZDB1_TYPEFACE = 2

    const val FUTURA_TYPEFACE = 3

    const val DIN_TYPEFACE = 4

    const val LOBSTER_TYPEFACE = 5

    val fzlLTypeface by lazy {
        Typeface.createFromAsset(App.instance.assets, "fonts/FZLanTingHeiS-L-GB-Regular.TTF")
    }

    val fzdb1Typeface by lazy {
        Typeface.createFromAsset(App.instance.assets, "fonts/FZLanTingHeiS-DB1-GB-Regular.TTF")
    }

    val futuraTypeface by lazy {
        Typeface.createFromAsset(App.instance.assets, "fonts/Futura-CondensedMedium.ttf")
    }

    val dinTypeface by lazy {
        Typeface.createFromAsset(App.instance.assets, "fonts/DIN-Condensed-Bold.ttf")
    }

    val lobsterTypeface by lazy {
        Typeface.createFromAsset(App.instance.assets, "fonts/Lobster-1.4.otf")
    }

    fun getTypeface(typefaceValue: Int?): Typeface = when (typefaceValue) {
        FZLL_TYPEFACE -> fzlLTypeface
        FZDB1_TYPEFACE -> fzdb1Typeface
        FUTURA_TYPEFACE -> futuraTypeface
        DIN_TYPEFACE -> dinTypeface
        LOBSTER_TYPEFACE -> lobsterTypeface
        else -> Typeface.DEFAULT
    }
}