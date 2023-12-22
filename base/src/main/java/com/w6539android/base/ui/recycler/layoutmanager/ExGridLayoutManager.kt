package com.w6539android.base.ui.recycler.layoutmanager

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.w6539android.base.ui.recycler.BaseAdapter
import kotlin.math.min

/**
 * @author Yang
 * @since 2023/9/12 10:44
 * @desc
 */
class ExGridLayoutManager : GridLayoutManager {
    constructor(
        context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)

    constructor(context: Context?, spanCount: Int) : super(context, spanCount)
    constructor(
        context: Context?,
        spanCount: Int,
        orientation: Int,
        reverseLayout: Boolean
    ) : super(context, spanCount, orientation, reverseLayout)

    private var adapter: RecyclerView.Adapter<*>? = null
    private var originalSpanSizeLookup: SpanSizeLookup = DefaultSpanSizeLookup()
    private val spanSizeLookup = object : SpanSizeLookup() {

        override fun getSpanSize(position: Int): Int {
            val adapter = adapter ?: return originalSpanSizeLookup.getSpanSize(position)
            if (adapter is ConcatAdapter) {
                val pair = adapter.getWrappedAdapterAndPosition(position)
                val first = pair.first
                val second = pair.second
                if (first is BaseAdapter<*>) {
                    return min(spanCount, first.getItemSpanCount(second))
                }
                return 1
            } else {
                if (adapter is BaseAdapter<*>) {
                    return min(spanCount, adapter.getItemSpanCount(position))
                }
                return 1
            }
        }
    }

    init {
        super.setSpanSizeLookup(spanSizeLookup)
    }

    override fun onAdapterChanged(
        oldAdapter: RecyclerView.Adapter<*>?,
        newAdapter: RecyclerView.Adapter<*>?
    ) {
        adapter = newAdapter
    }

    override fun setSpanSizeLookup(spanSizeLookup: SpanSizeLookup) {
        originalSpanSizeLookup = spanSizeLookup
    }
}