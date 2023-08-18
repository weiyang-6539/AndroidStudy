package com.w6539android.base.widget.decoration

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.IllegalArgumentException

/**
 * @author Yang
 * @since 2022/11/25 17:27
 * @desc 分组分割线
 */
class StickyDecoration : RecyclerView.ItemDecoration() {
    @ColorInt
    private var mGroupBgColor = Color.TRANSPARENT
    private var mGroupHeight = 120

    private var mDividerHeight = 3

    private val mDividerPaint
        get() = Paint().apply {
            color = Color.LTGRAY
        }

    private var mSticky = true
    private val gestureListener = object : GestureDetector.OnGestureListener {
        override fun onDown(e: MotionEvent?) = false

        override fun onShowPress(e: MotionEvent?) {}

        override fun onSingleTapUp(e: MotionEvent?): Boolean {
            return false
        }

        override fun onScroll(
            e1: MotionEvent?,
            e2: MotionEvent?,
            distanceX: Float,
            distanceY: Float
        ) = false

        override fun onLongPress(e: MotionEvent?) {}

        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent?,
            velocityX: Float,
            velocityY: Float
        ) = false
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        if (parent.adapter !is StickyDelegate) {
            throw IllegalArgumentException("")
        }

        val childPos = parent.getChildAdapterPosition(view)
        parent.layoutManager.let {
            when (it) {
                is LinearLayoutManager -> {
                    setLinearOffset(it, outRect, childPos)
                }
            }
        }
    }

    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(canvas, parent, state)
        val itemCount = state.itemCount
        val childCount = parent.childCount
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        /*for (i in 0 until childCount) {
            val childView = parent.getChildAt(i)
            val position = parent.getChildAdapterPosition(childView)
            val realPosition: Int = position
            if (isFirstInGroup(realPosition) || isFirstInRecyclerView(realPosition, i)) {
                val viewBottom = childView.bottom
                //top 决定当前顶部第一个悬浮Group的位置
                var bottom = childView.top
                if (mSticky)
                    bottom = mGroupHeight.coerceAtLeast(childView.top + parent.paddingTop)
                if (mSticky && position + 1 < itemCount) {
                    //下一组的第一个View接近头部
                    if (isLastLineInGroup(parent, realPosition) && viewBottom < bottom) {
                        bottom = viewBottom
                    }
                }
                drawDecoration(canvas, realPosition, left, right, bottom)
            } else {
                drawDivide(canvas, parent, childView, realPosition, left, right)
            }
        }*/
    }

    private fun isFirstInGroup(position: Int): Boolean {
        return false
    }

    private fun setLinearOffset(layoutManager: LinearLayoutManager, outRect: Rect, childPos: Int) {
        if (layoutManager.orientation == RecyclerView.VERTICAL) {

        }
    }

}