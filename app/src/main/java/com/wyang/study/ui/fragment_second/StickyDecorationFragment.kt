package com.wyang.study.ui.fragment_second

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.GradientDrawable
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.gavin.com.library.PowerfulStickyDecoration
import com.gavin.com.library.listener.PowerGroupListener
import com.wyang.study.R
import com.wyang.study.databinding.FragmentStickyDecorationBinding
import com.wyang.study.databinding.WidgetRecyclerBinding
import com.wyang.study.ui.base.BaseFragment

class StickyDecorationFragment : BaseFragment<FragmentStickyDecorationBinding>() {
    private var mRecyclerBinding: WidgetRecyclerBinding? = null
    private var mAdapter: StickyAdapter? = null

    override fun getViewBinding(): FragmentStickyDecorationBinding {
        return FragmentStickyDecorationBinding.inflate(layoutInflater)
    }

    override fun initialize() {
        mBinding?.root?.let { mRecyclerBinding = WidgetRecyclerBinding.bind(it) }

        val listener: PowerGroupListener = object : PowerGroupListener {
            override fun getGroupName(position: Int): String? {
                val item: String? = mAdapter?.data?.get(position)
                return item?.substring(0, 3)
            }

            override fun getGroupView(position: Int): View {
                //获取自定定义的组View
                val view: View = layoutInflater.inflate(R.layout.sticky_header, null, false)
                val tv_type = view.findViewById<TextView>(R.id.tv_type)
                tv_type.text = getGroupName(position)
                return view
            }
        }

        val v: View = layoutInflater.inflate(R.layout.sticky_header, null, false)
        measureWidthAndHeight(v)

        val decoration = PowerfulStickyDecoration.Builder
            .init(listener)
            .setDivideHeight(0)
            .setDivideColor(-0x1d1d1e)
            .setGroupHeight(dp2px(40f)) //分组的高度
            .setGroupBackground(0x00000000) //重置span（注意：使用GridLayoutManager时必须调用）
            //.resetSpan(mRecyclerView, (GridLayoutManager) manager)
            .build()

        mRecyclerBinding?.mRecyclerView?.addItemDecoration(ItemDecoration())
        mRecyclerBinding?.mRecyclerView?.addItemDecoration(decoration)

        mAdapter = StickyAdapter()
        mAdapter?.bindToRecyclerView(mRecyclerBinding?.mRecyclerView)

        val list: MutableList<String> = ArrayList()
        list.add("分组1-AAAAA")
        list.add("分组1-BBBBB")
        list.add("分组1-CCCCC")

        list.add("分组2-AAAAA")
        list.add("分组2-BBBBB")
        list.add("分组2-CCCCC")

        list.add("分组3-AAAAA")
        list.add("分组3-AAAAA")
        list.add("分组3-AAAAA")

        list.add("分组4-DDDDD")
        list.add("分组4-EEEEE")
        list.add("分组4-EEEEE")

        list.add("分组5-FFFFF")
        list.add("分组5-FFFFF")
        list.add("分组5-FFFFF")
        list.add("分组5-GGGGG")
        mAdapter?.setNewData(list as List<String?>?)
    }

    fun measureWidthAndHeight(view: View) {
        val w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        val h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        view.measure(w, h)
    }

    /**
     * 判断是不是分组第一个
     */
    private fun isFirst(pos: Int): Boolean {
        if (pos == 0) return true
        val c: String? = mAdapter?.data?.get(pos)
        val p: String? = mAdapter?.data?.get(pos - 1)
        return !TextUtils.equals(c?.substring(0, 3), p?.substring(0, 3))
    }

    /**
     * 判断是不是分组最后一个
     */
    private fun isFooter(pos: Int): Boolean {
        val size: Int? = mAdapter?.data?.size
        if (size != null) {
            if (pos == size - 1) return true
        }
        val c: String? = mAdapter?.data?.get(pos)
        val n: String? = mAdapter?.data?.get(pos + 1)
        if (c != null) {
            return !TextUtils.equals(c.substring(0, 3), n?.substring(0, 3))
        }
        return false
    }

    class StickyAdapter : BaseQuickAdapter<String?, BaseViewHolder>(R.layout.item_sticky_recycler) {
        override fun convert(helper: BaseViewHolder, item: String?) {
            helper.setText(R.id.tv_content, item)
        }
    }

    fun dp2px(dp: Float): Int {
        val scale = resources.displayMetrics.density
        return (dp * scale + 0.5f).toInt()
    }

    inner class ItemDecoration : RecyclerView.ItemDecoration() {
        private val topCorner: GradientDrawable
        private val bottomCorner: GradientDrawable
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            val position = parent.getChildAdapterPosition(view)
            when {
                position == 0 -> {
                    outRect.top = dp2px(20f)
                }
                isFirst(position) -> {
                    outRect.top = dp2px(30f)
                }
                else -> {
                    outRect.top = dp2px(0f)
                }
            }
            if (position == mAdapter?.data?.size?.minus(1) ?: 0) {
                outRect.bottom = dp2px(20f)
            } else {
                outRect.bottom = 0
            }
        }

        override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
            super.onDraw(canvas, parent, state)
            val childCount = parent.childCount
            for (i in 0 until childCount) {
                val childAt = parent.getChildAt(i)
                val position = parent.getChildLayoutPosition(childAt)
                val left = parent.paddingLeft
                val right = parent.width - parent.paddingRight
                var top: Int = childAt.top - dp2px(40f) //减去分组的高度
                if (position == 0 || position == mAdapter?.data?.size?.minus(1) ?: 0) {
                    if (position == 0) {
                        top -= dp2px(20f)
                        topCorner.setBounds(left, top + dp2px(10f), right, top + dp2px(20f))
                        topCorner.draw(canvas)
                    } else {
                        top = childAt.bottom
                        bottomCorner.setBounds(left, top, right, top + dp2px(10f))
                        bottomCorner.draw(canvas)
                    }
                } else {
                    top -= dp2px(30f)
                    if (isFirst(position)) {   // 是分组第一个，则绘制
                        bottomCorner.setBounds(left, top, right, top + dp2px(10f))
                        bottomCorner.draw(canvas)
                        topCorner.setBounds(left, top + dp2px(20f), right, top + dp2px(30f))
                        topCorner.draw(canvas)
                    }
                }
            }
        }

        init {
            val radius: Int = dp2px(10f)
            topCorner = GradientDrawable()
            topCorner.shape = GradientDrawable.RECTANGLE
            topCorner.cornerRadii = floatArrayOf(
                radius.toFloat(), radius.toFloat(),
                radius.toFloat(), radius.toFloat(), 0f, 0f, 0f, 0f
            )
            topCorner.setColor(Color.WHITE)
            bottomCorner = GradientDrawable()
            bottomCorner.shape = GradientDrawable.RECTANGLE
            bottomCorner.cornerRadii = floatArrayOf(
                0f, 0f, 0f, 0f,
                radius.toFloat(), radius.toFloat(),
                radius.toFloat(), radius
                    .toFloat()
            )
            bottomCorner.setColor(Color.WHITE)
        }
    }
}