@file:Suppress("UNCHECKED_CAST")

package com.w6539android.base.ext

/**
 * @author WeiYang
 * @date 2024/6/18
 * @desc
 */

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType


@JvmName("inflateWithGeneric")
fun <VB : ViewBinding> Any.inflateBindingWithGeneric(layoutInflater: LayoutInflater): VB =
    withGenericBindingClass<VB> { clazz ->
        clazz.getMethod("inflate", LayoutInflater::class.java).invoke(null, layoutInflater) as VB
    }

@JvmName("inflateWithGeneric")
fun <VB : ViewBinding> Any.inflateBindingWithGeneric(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?,
    attachToParent: Boolean
): VB = withGenericBindingClass<VB> { clazz ->
    if (parent == null)
        clazz.getMethod(
            "inflate",
            LayoutInflater::class.java,
        ).invoke(null, layoutInflater) as VB
    else
        clazz.getMethod(
            "inflate",
            LayoutInflater::class.java,
            ViewGroup::class.java,
            Boolean::class.java
        ).invoke(null, layoutInflater, parent, attachToParent) as VB
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