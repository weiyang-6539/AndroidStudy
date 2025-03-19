package com.fxbandroid.basejetpack.ext

import android.animation.ArgbEvaluator

/**
 * @author Yang
 * @date 2024/11/19
 * @desc
 */
fun interpolateColor(fraction: Float, startColor: Int, endColor: Int) =
    ArgbEvaluator().evaluate(fraction, startColor, endColor) as Int

fun interpolateValue(maxValue: Float, minValue: Float, p: Float): Float {
    return maxValue - (maxValue - minValue) * p
}