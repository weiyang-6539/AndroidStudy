package com.fxbandroid.basejetpack.ext

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.fragment.NavHostFragment
import com.fxbandroid.basejetpack.R

/**
 * @author Yang
 * @date 2024/11/16
 * @desc
 */
fun Fragment.navigate(
    @IdRes resId: Int,
    args: Bundle = Bundle(),
    navOptions: NavOptions? = null,
    @IdRes destinationId: Int = 0,
    inclusive: Boolean = false,
    singleTop: Boolean = false,
    extras: Navigator.Extras? = null
) {
    val options = navOptions ?: NavOptions.Builder()
        .setEnterAnim(R.anim.slide_in_right)
        .setExitAnim(R.anim.slide_out_left)
        .setPopEnterAnim(R.anim.slide_in_left)
        .setPopExitAnim(R.anim.slide_out_right)
        .apply {
            if (singleTop)
                setLaunchSingleTop(true)
            if (destinationId != 0)
                setPopUpTo(destinationId, inclusive)
        }
        .build()
    NavHostFragment.findNavController(this)
        .navigate(resId, args, options, extras)
}

fun Fragment.navigateUp() {
    NavHostFragment.findNavController(this)
        .navigateUp()
}

fun Fragment.popBackStack(@IdRes destinationId: Int = 0, inclusive: Boolean = false) {
    if (destinationId == 0)
        NavHostFragment.findNavController(this)
            .popBackStack()
    else
        NavHostFragment.findNavController(this)
            .popBackStack(destinationId, inclusive)
}

fun <T : Any> Fragment.getByKey(key: String, def: T): T {
    val navController = NavHostFragment.findNavController(this)
    return navController.currentBackStackEntry?.savedStateHandle?.get<T>(key) ?: def
}

/**
 * navigation 参数回传监听
 */
fun <T : Any> Fragment.once(key: String, action: (T) -> Unit) {
    val navController = NavHostFragment.findNavController(this)
    navController.currentBackStackEntry?.savedStateHandle?.getLiveData<T>(key)
        ?.apply {
            removeObservers(this@once)
            observe(navController.currentBackStackEntry!!) {
                action.invoke(it)
            }
        }
}

/**
 * navigation 设置回传参数
 */
fun <T> Fragment.emit(
    key: String,
    value: T,
    isCurrentStack: Boolean = false,
    @IdRes destinationId: Int = 0
) {
    NavHostFragment.findNavController(this)
        .apply {
            when {
                destinationId != 0 -> runCatching {
                    getBackStackEntry(destinationId)
                }.getOrDefault(null)

                isCurrentStack -> currentBackStackEntry

                else -> previousBackStackEntry
            }?.savedStateHandle?.set(key, value)
        }
}