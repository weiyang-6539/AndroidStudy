@file:Suppress("UNCHECKED_CAST")

package com.fxbandroid.basejetpack.ext

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

/**
 * @author Yang
 * @since 2022/10/26 14:30
 * @desc mvvm 获取泛型实例
 */
fun <VM> Any.getVMCls(): VM {
    return (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as VM
}

@JvmName("inflateWithGeneric")
fun <VB : ViewBinding> Any.inflateBindingWithGeneric(layoutInflater: LayoutInflater): VB =
    withGenericBindingClass { clazz ->
        clazz.getMethod("inflate", LayoutInflater::class.java)
            .invoke(null, layoutInflater) as VB
    }

@JvmName("inflateWithGeneric")
fun <VB : ViewBinding> Any.inflateBindingWithGeneric(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?,
    attachToParent: Boolean
): VB = withGenericBindingClass { clazz ->
    if (parent == null)
        clazz.getMethod(
            "inflate",
            LayoutInflater::class.java,
        )
            .invoke(null, layoutInflater) as VB
    else
        clazz.getMethod(
            "inflate",
            LayoutInflater::class.java,
            ViewGroup::class.java,
            Boolean::class.java
        )
            .invoke(null, layoutInflater, parent, attachToParent) as VB
}

private fun <VB : ViewBinding> Any.withGenericBindingClass(block: (Class<VB>) -> VB): VB {
    var genericSuperclass = javaClass.genericSuperclass
    var superclass = javaClass.superclass
    while (superclass != null) {
        if (genericSuperclass is ParameterizedType) {
            return block.invoke(genericSuperclass.actualTypeArguments[genericSuperclass.actualTypeArguments.size - 1] as Class<VB>)
        }
        genericSuperclass = superclass.genericSuperclass
        superclass = superclass.superclass
    }
    throw IllegalArgumentException("There is no generic of ViewBinding.")
}