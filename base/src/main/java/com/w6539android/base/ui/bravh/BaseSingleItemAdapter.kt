package com.w6539android.base.ui.bravh

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

/**
 * @author Yang
 * @since 2023/8/22 16:51
 * @desc
 */
abstract class BaseSingleItemAdapter<T>(
    @LayoutRes private val layoutId: Int,
    private var data: T? = null
) : RecyclerView.Adapter<BaseViewHolder>(), IBaseAdapter<T> {

    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BaseViewHolder(layoutId, parent).apply {
        }

    final override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        data?.apply {
            convert(holder, this)
        }
    }

    final override fun getItemCount() = 1

    fun setData(t: T?, payload: Any?) {
        data = t
        notifyItemChanged(0, payload)
    }
}