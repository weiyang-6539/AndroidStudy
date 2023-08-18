package com.w6539android.base.widget.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

/**
 * @author Yang
 * @since 2022/11/4 15:37
 * @desc RecyclerView Item间隔分割 无绘制逻辑, 适用于卡片式布局, 不限定布局管理器, 水平or垂直方向
 */
open class SpacingDecoration private constructor() : RecyclerView.ItemDecoration() {
    companion object {
        fun newDecoration(): SpacingDecoration {
            return SpacingDecoration()
        }
    }

    private var headerCount = 0 // 头布局数
    private var dataCount = 0   // 条目数(model数据,不包含header 和 footer)
    private var spacing = 15     // item间距
    private var paddingStart = 15 // 左边距
    private var paddingEnd = 15   // 右边距
    private var paddingTop = 15   // 第一个Item的上边距
    private var paddingBottom = 15// 最后一个Item的下边距

    open fun setPaddingStart(paddingStart: Int): SpacingDecoration {
        this.paddingStart = paddingStart
        return this
    }

    open fun setPaddingTop(paddingTop: Int): SpacingDecoration {
        this.paddingTop = paddingTop
        return this
    }

    open fun setPaddingEnd(paddingEnd: Int): SpacingDecoration {
        this.paddingEnd = paddingEnd
        return this
    }

    open fun setPaddingBottom(paddingBottom: Int): SpacingDecoration {
        this.paddingBottom = paddingBottom
        return this
    }

    open fun setPadding(
        paddingStart: Int,
        paddingTop: Int,
        paddingEnd: Int,
        paddingBottom: Int
    ): SpacingDecoration {
        setPaddingStart(paddingStart)
        setPaddingTop(paddingTop)
        setPaddingEnd(paddingEnd)
        setPaddingBottom(paddingBottom)
        return this
    }

    open fun setSpacing(spacing: Int): SpacingDecoration {
        this.spacing = spacing
        return this
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        // RecyclerView可能添加header, 需要用适配器实现DecorationDelegate
        parent.adapter.let {
            if (it is SpacingDecorationDelegate) {
                headerCount = it.getHeaderCount()
                dataCount = it.getDataCount()
            } else {
                headerCount = 0
                dataCount = it?.itemCount ?: 0
            }
        }

        val childPos = parent.getChildAdapterPosition(view)

        //根据适配器设置item间距
        parent.layoutManager?.let {
            when (it) {
                //GridLayoutManager是LinearLayoutManager的子类
                is GridLayoutManager -> {
                    setGridOffset(it.orientation, outRect, childPos, it.spanCount)
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
            if (isDataItem(childPos)) {
                when {
                    childPos - headerCount == 0 -> {
                        outRect.top = paddingTop
                        outRect.bottom = spacing
                    }
                    childPos - headerCount + 1 == dataCount -> outRect.bottom = paddingBottom
                    else -> outRect.bottom = spacing
                }
            }
        } else {
            outRect.top = paddingTop
            outRect.bottom = paddingBottom
            if (isDataItem(childPos)) {
                when {
                    childPos - headerCount == 0 -> {
                        outRect.left = paddingStart
                        outRect.right = spacing
                    }
                    childPos - headerCount + 1 == dataCount -> outRect.right = paddingEnd
                    else -> outRect.right = spacing
                }
            }
        }
    }

    private fun setGridOffset(orientation: Int, outRect: Rect, childPos: Int, spanCount: Int) {
        if (orientation == RecyclerView.VERTICAL) {
            val totalSpace = spacing * (spanCount - 1) + paddingStart + paddingEnd // 总间距
            val eachSpace = totalSpace / spanCount // 平均每个item的间距
            val column = (childPos - headerCount) % spanCount
            val row = (childPos - headerCount) / spanCount

            outRect.left =
                column * (eachSpace - paddingStart - paddingEnd) / (spanCount - 1) + paddingStart
            outRect.right = eachSpace - outRect.left

            when {
                childPos - headerCount < spanCount -> {
                    outRect.top = paddingTop
                    outRect.bottom = spacing
                }
                dataCount / spanCount == row -> {
                    outRect.top = 0
                    outRect.bottom = paddingBottom
                }
                else -> {
                    outRect.top = 0
                    outRect.bottom = spacing
                }
            }
        } else {
            val totalSpace = spacing * (spanCount - 1) + paddingTop + paddingBottom // 总间距
            val eachSpace = totalSpace / spanCount // 平均每个item的间距
            val column = (childPos - headerCount) % spanCount
            val row = (childPos - headerCount) / spanCount

            outRect.top =
                column * (eachSpace - paddingTop - paddingBottom) / (spanCount - 1) + paddingTop
            outRect.bottom = eachSpace - outRect.top

            when {
                childPos - headerCount < spanCount -> {
                    outRect.left = paddingStart
                    outRect.right = spacing
                }
                dataCount / spanCount == row -> {
                    outRect.left = 0
                    outRect.right = paddingEnd
                }
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
            val row = (childPos - headerCount) / spanCount

            outRect.left =
                spanIndex * (eachSpace - paddingStart - paddingEnd) / (spanCount - 1) + paddingStart
            outRect.right = eachSpace - outRect.left

            when {
                childPos - headerCount < spanCount -> {
                    outRect.top = paddingTop
                    outRect.bottom = spacing
                }
                dataCount / spanCount == row -> {
                    outRect.top = 0
                    outRect.bottom = paddingBottom
                }
                else -> {
                    outRect.top = 0
                    outRect.bottom = spacing
                }
            }
        } else {
            val totalSpace = spacing * (spanCount - 1) + paddingTop + paddingBottom // 总间距
            val eachSpace = totalSpace / spanCount // 平均每个item的间距
            val row = (childPos - headerCount) / spanCount

            outRect.top =
                spanIndex * (eachSpace - paddingTop - paddingBottom) / (spanCount - 1) + paddingTop
            outRect.bottom = eachSpace - outRect.top

            when {
                childPos - headerCount < spanCount -> {
                    outRect.left = paddingStart
                    outRect.right = spacing
                }
                dataCount / spanCount == row -> {
                    outRect.left = 0
                    outRect.right = paddingEnd
                }
                else -> {
                    outRect.left = 0
                    outRect.right = spacing
                }
            }
        }
    }

    private fun isDataItem(position: Int): Boolean {
        return position - headerCount >= 0 && position - headerCount + 1 <= dataCount
    }
}