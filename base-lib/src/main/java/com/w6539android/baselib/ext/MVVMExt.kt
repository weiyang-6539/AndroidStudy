package com.w6539android.baselib.ext

import java.lang.reflect.ParameterizedType

/**
 * @author Yang
 * @since 2022/10/26 14:30
 * @desc
 */
//获取泛型的实例
fun <VM> getVMCls(cls: Any): VM {
    return (cls.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as VM
}