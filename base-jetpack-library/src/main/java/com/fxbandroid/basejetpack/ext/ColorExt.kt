package com.fxbandroid.basejetpack.ext

import android.graphics.Color

/**
 * @author yang
 * @date 2024/11/26
 * @desc
 */
fun addAlphaToColor(color: Int, alpha: Int): Int {
    // alpha 值应在 0 到 255 之间
    return Color.argb(alpha, Color.red(color), Color.green(color), Color.blue(color))
}