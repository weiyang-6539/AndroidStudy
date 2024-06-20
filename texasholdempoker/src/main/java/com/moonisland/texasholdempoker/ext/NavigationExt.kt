package com.moonisland.texasholdempoker.ext

import android.app.Activity
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment

fun Fragment.nav(): NavController {
    return NavHostFragment.findNavController(this)
}

fun Fragment.navigate(@IdRes resId: Int) {
    navigate(resId, null)
}

fun Fragment.navigate(@IdRes resId: Int, args: Bundle?) {
    navigate(resId, args, null)
}

fun Fragment.navigate(@IdRes resId: Int, args: Bundle?, navOptions: NavOptions?) {
    NavHostFragment.findNavController(this).navigate(resId, args, navOptions)
}

fun Fragment.navigateUp() {
    NavHostFragment.findNavController(this).navigateUp()
}

/**
 * navigation 参数回传监听
 */
fun <T : Any> Fragment.once(key: String, action: (T) -> Unit) {
    nav().currentBackStackEntry?.savedStateHandle?.getLiveData<T>(key)?.apply {
        removeObservers(this@once)
        observe(nav().currentBackStackEntry!!) {
            action.invoke(it)
        }
    }
}

/**
 * navigation 设置回传参数
 */
fun <T> Fragment.emit(key: String, value: T) {
    nav().previousBackStackEntry?.savedStateHandle?.set(key, value)
}

fun Activity.nav(@IdRes id: Int): NavController {
    return Navigation.findNavController(this, id)
}
