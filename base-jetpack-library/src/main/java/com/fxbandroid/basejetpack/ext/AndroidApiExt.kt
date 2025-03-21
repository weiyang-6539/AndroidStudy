package com.fxbandroid.basejetpack.ext

import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment

/**
 * @author yang
 * @date 2024/12/26
 * @desc
 */
fun Fragment.registerForActivityResult(callback: ActivityResultCallback<ActivityResult>) =
    registerForActivityResult(ActivityResultContracts.StartActivityForResult(), callback)