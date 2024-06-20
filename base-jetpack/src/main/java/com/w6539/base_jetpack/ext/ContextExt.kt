package com.w6539.base_jetpack.ext

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher

/**
 * @author Yang
 * @since 2023/9/8 16:09
 * @desc
 */
fun Context.openActivity(cls: Class<out Activity>) {
    openActivity(cls, null)
}

fun Context.openActivity(cls: Class<out Activity>, bundle: Bundle?) {
    Intent(this, cls).apply {
        bundle?.let { putExtras(bundle) }
        startActivity(this)
    }
}

fun Context.openActivityForResult(
    cls: Class<out Activity>,
    launcher: ActivityResultLauncher<Intent>
) {
    openActivityForResult(cls, null, launcher)
}

fun Context.openActivityForResult(
    cls: Class<out Activity>,
    bundle: Bundle?,
    launcher: ActivityResultLauncher<Intent>
) {
    Intent(this, cls).apply {
        bundle?.let { putExtras(it) }
        launcher.launch(this)
    }
}