package com.w6539android.base.ui.recycler.interfaces

import androidx.annotation.IdRes
import com.w6539android.base.ui.recycler.OnClickListener
import com.w6539android.base.ui.recycler.OnLongClickListener

/**
 * @author Yang
 * @since 2023/9/1 10:46
 * @desc
 */
interface IListener<Adapter> {
    /** event */
    fun setItemClickListener(listener: OnClickListener<Adapter>)
    fun setItemLongClickListener(listener: OnLongClickListener<Adapter>)
    fun addItemChildClickListener(@IdRes id: Int, listener: OnClickListener<Adapter>)
    fun addItemChildLongClickListener(@IdRes id: Int, listener: OnLongClickListener<Adapter>)
}