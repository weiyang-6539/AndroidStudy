package com.w6539android.base.ui.recycler.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.w6539android.base.ui.recycler.BaseAdapter

/**
 * @author Yang
 * @since 2022/11/4 15:37
 * @desc RecyclerView Item间隔分割 无绘制逻辑, 适用于卡片式布局, 不限定布局管理器, 水平or垂直方向
 */
open class SpacingDecoration private constructor() : RecyclerView.ItemDecoration() {
    companion object {
        fun newDecoration() = SpacingDecoration()
    }

    private var spacing = 15     // item间距
    private var paddingStart = 15 // 左边距
    private var paddingEnd = 15   // 右边距
    private var paddingTop = 15   // 第一个Item的上边距
    private var paddingBottom = 15// 最后一个Item的下边距

    open fun setPaddingStart(paddingStart: Int) = apply {
        this.paddingStart = paddingStart
    }

    open fun setPaddingTop(paddingTop: Int) = apply {
        this.paddingTop = paddingTop
    }

    open fun setPaddingEnd(paddingEnd: Int) = apply {
        this.paddingEnd = paddingEnd
    }

    open fun setPaddingBottom(paddingBottom: Int) = apply {
        this.paddingBottom = paddingBottom
    }

    open fun setPadding(
        paddingStart: Int,
        paddingTop: Int,
        paddingEnd: Int,
        paddingBottom: Int
    ) = apply {
        this.paddingStart = paddingStart
        this.paddingTop = paddingTop
        this.paddingEnd = paddingEnd
        this.paddingBottom = paddingBottom
    }

    open fun setSpacing(spacing: Int) = apply {
        this.spacing = spacing
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val childPos = parent.getChildAdapterPosition(view)

        //根据适配器设置item间距
        parent.layoutManager?.let {
            when (it) {
                //GridLayoutManager是LinearLayoutManager的子类
                is GridLayoutManager -> {
                    setGridOffset(it, outRect, view, parent)
                }

                is LinearLayoutManager -> {
                    setLinearOffset(it.orientation, outRect, childPos)
                }

                is StaggeredGridLayoutManager -> {
                    val params = view.layoutParams as StaggeredGridLayoutManager.LayoutParams
                    setStaggeredGridOffset(
                        it.orientation,
                        outRect,
                        childPos,
                        it.spanCount,
                        params.spanIndex
                    )
                }
            }
        }
    }

    private fun setLinearOffset(orientation: Int, outRect: Rect, childPos: Int) {
        if (orientation == RecyclerView.VERTICAL) {
            outRect.left = paddingStart
            outRect.right = paddingEnd
            when (childPos) {
                0 -> {
                    outRect.top = paddingTop
                    outRect.bottom = spacing
                }

                else -> outRect.bottom = spacing
            }
        } else {
            outRect.top = paddingTop
            outRect.bottom = paddingBottom
            when (childPos) {
                0 -> {
                    outRect.left = paddingStart
                    outRect.right = spacing
                }

                else -> outRect.right = spacing
            }
        }
    }

    private fun setGridOffset(
        manager: GridLayoutManager,
        outRect: Rect,
        view: View,
        parent: RecyclerView
    ) {
        val adapter = parent.adapter
        val orientation = manager.orientation
        val spanCount = manager.spanCount
        var childPos = parent.getChildAdapterPosition(view)

        when (adapter) {
            is ConcatAdapter -> {
                val pair = adapter.getWrappedAdapterAndPosition(childPos)
                val first = pair.first
                val second = pair.second

                if (first is BaseAdapter<*>) {
                    if (first.isFullSpanAdapter())
                        return
                    if (first.isFullSpanItem(second))
                        return
                }
                childPos = second
            }

            is BaseAdapter<*> -> {
                if (adapter.isFullSpanAdapter())
                    return
                if (adapter.isFullSpanItem(childPos))
                    return
            }
        }

        if (orientation == RecyclerView.VERTICAL) {
            val totalSpace = spacing * (spanCount - 1) + paddingStart + paddingEnd // 总间距
            val eachSpace = totalSpace / spanCount // 平均每个item的间距
            val column = childPos % spanCount
            val row = childPos / spanCount

            outRect.left = column * (eachSpace - paddingStart - paddingEnd) /
                    (spanCount - 1) + paddingStart
            outRect.right = eachSpace - outRect.left

            when {
                childPos < spanCount -> {
                    outRect.top = paddingTop
                    outRect.bottom = spacing
                }
                /*dataCount / spanCount == row -> {
                    outRect.top = 0
                    outRect.bottom = paddingBottom
                }*/
                else -> {
                    outRect.top = 0
                    outRect.bottom = spacing
                }
            }
        } else {
            val totalSpace = spacing * (spanCount - 1) + paddingTop + paddingBottom // 总间距
            val eachSpace = totalSpace / spanCount // 平均每个item的间距
            val column = childPos % spanCount
            val row = childPos / spanCount

            outRect.top = column * (eachSpace - paddingTop - paddingBottom) /
                    (spanCount - 1) + paddingTop
            outRect.bottom = eachSpace - outRect.top

            when {
                childPos < spanCount -> {
                    outRect.left = paddingStart
                    outRect.right = spacing
                }
                /*dataCount / spanCount == row -> {
                    outRect.left = 0
                    outRect.right = paddingEnd
                }*/
                else -> {
                    outRect.left = 0
                    outRect.right = spacing
                }
            }
        }
    }

    private fun setStaggeredGridOffset(
        orientation: Int, outRect: Rect, childPos: Int, spanCount: Int, spanIndex: Int
    ) {
        if (orientation == RecyclerView.VERTICAL) {
            val totalSpace = spacing * (spanCount - 1) + paddingStart + paddingEnd // 总间距
            val eachSpace = totalSpace / spanCount // 平均每个item的间距
            val row = childPos / spanCount

            outRect.left =
                spanIndex * (eachSpace - paddingStart - paddingEnd) / (spanCount - 1) + paddingStart
            outRect.right = eachSpace - outRect.left

            when {
                childPos < spanCount -> {
                    outRect.top = paddingTop
                    outRect.bottom = spacing
                }
                /*dataCount / spanCount == row -> {
                    outRect.top = 0
                    outRect.bottom = paddingBottom
                }*/
                else -> {
                    outRect.top = 0
                    outRect.bottom = spacing
                }
            }
        } else {
            val totalSpace = spacing * (spanCount - 1) + paddingTop + paddingBottom // 总间距
            val eachSpace = totalSpace / spanCount // 平均每个item的间距
            val row = childPos / spanCount

            outRect.top =
                spanIndex * (eachSpace - paddingTop - paddingBottom) / (spanCount - 1) + paddingTop
            outRect.bottom = eachSpace - outRect.top

            when {
                childPos < spanCount -> {
                    outRect.left = paddingStart
                    outRect.right = spacing
                }
                /*dataCount / spanCount == row -> {
                    outRect.left = 0
                    outRect.right = paddingEnd
                }*/
                else -> {
                    outRect.left = 0
                    outRect.right = spacing
                }
            }
        }
    }

}