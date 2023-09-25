package com.wyang.study.ui.fragment.rv

import android.annotation.SuppressLint
import android.content.Context
import android.util.DisplayMetrics
import android.view.MotionEvent
import android.view.View
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.view.WindowManager
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.w6539android.base.base.fragment.BaseFragment
import com.w6539android.base.ui.recycler.BaseAdapter
import com.w6539android.base.ui.recycler.BaseViewHolder
import com.wyang.study.R
import com.wyang.study.bean.Album
import com.wyang.study.bean.AlbumBase
import com.wyang.study.databinding.FragmentAlbumBinding
import com.wyang.study.ui.util.DataProvider
import com.wyang.study.ui.util.GlideImageLoader

class AlbumFragment : BaseFragment<FragmentAlbumBinding>() {

    private val mAdapter = AlbumAdapter()

    /**
     * 三个关键值
     */
    //RecyclerView的高
    private var mHeight = 0

    //纵向滑动距离
    private var scrollY = 0f

    //纵向最大滑动距离
    private var maxScrollY = 0f

    private var maxDateOffsetY = 0f

    private val onScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            if (maxScrollY == 0f) return
            scrollY += dy.toFloat()
            println("scrollY:${scrollY} maxScrollY:${maxScrollY} ")
            val percent = scrollY * 1f / maxScrollY
            setClDateParentBias(percent)

            val layoutManager = recyclerView.layoutManager
            if (layoutManager is GridLayoutManager) {
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                val base: AlbumBase = mAdapter.get(firstVisibleItemPosition)
                mBinding.tvDate.text = base.date
            }
        }
    }

    override fun getViewBinding() = FragmentAlbumBinding.inflate(layoutInflater)

    @SuppressLint("ClickableViewAccessibility")
    override fun initialize() {
        mBinding.clDateParent.setOnTouchListener { _: View?, event: MotionEvent ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> downY = event.y
                MotionEvent.ACTION_MOVE -> {
                    val dy = ((event.y - downY) / maxDateOffsetY * maxScrollY).toInt()
                    mBinding.clDateParent.post { smoothScrollBy(dy) }
                }
                else -> {
                }
            }
            true
        }

        mBinding.mRecyclerView.addOnScrollListener(onScrollListener)
        mBinding.mRecyclerView.viewTreeObserver.addOnGlobalLayoutListener(
            object : OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    mHeight = mBinding.mRecyclerView.height

                    //计算RecyclerView 纵向最大滑动距离, 在适配器设置数据之后
                    calculateMaxScrollY(mHeight, 4)
                    mBinding.mRecyclerView.scrollTo(0, 400)
                    mBinding.mRecyclerView.viewTreeObserver.removeGlobalOnLayoutListener(this)
                }
            })

        mBinding.mRecyclerView.adapter = mAdapter
//        mAdapter.setSpanSizeLookup { _: GridLayoutManager?, position: Int ->
//            val item = mAdapter.getItem(position)
//            if (item is Album) 1 else 4
//        }
        mAdapter.setNewData(DataProvider.getAlbumList())

        mBinding.tvDate.text = "01月03日"

        mBinding.tvDate.parent.requestDisallowInterceptTouchEvent(true)

    }

    private fun calculateMaxScrollY(height: Int, spanCount: Int) {
        maxScrollY = 0f

        //计算每行图片所占高度，即单个图片的宽 （屏幕宽 - RecyclerView左右padding）/ spanCount
        val mItemHeight = (getScreenWidth(context) - dp2px(7f) * 2f) / 4
//        val data = mAdapter.data
        val data = mutableListOf<AlbumBase>()
        var count = 0 //同一个日期组计数
        for (i in data.indices) {
            val base = data[i]
            if (base is Album) {
                count++
                if (count % spanCount == 0) {
                    maxScrollY += mItemHeight
                    count = 0
                }
            } else {
                //日期分组item
                maxScrollY += dp2px(40f).toFloat()
                if (count != 0) {
                    maxScrollY += mItemHeight
                    count = 0
                }
            }
        }
        if (count != 0) {
            maxScrollY += mItemHeight
        }

        //最后减去RecyclerView的高度
        maxScrollY -= height.toFloat()

        //滑块最大拖动的位置
        maxDateOffsetY = (height - mBinding.clDateParent.height).toFloat()
    }

    private var downY = 0f

    private fun smoothScrollBy(dy: Int) {
        mBinding.mRecyclerView.smoothScrollBy(0, dy)
    }

    private fun setClDateParentBias(bias: Float) {
        val params = mBinding.clDateParent.layoutParams as ConstraintLayout.LayoutParams
        params.verticalBias = bias
        mBinding.clDateParent.layoutParams = params
    }

    private fun dp2px(dp: Float): Int {
        val scale = resources.displayMetrics.density
        return (dp * scale + .5f).toInt()
    }

    private fun getScreenWidth(context: Context?): Int {
        val wm = context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val outMetrics = DisplayMetrics()
        wm.defaultDisplay.getMetrics(outMetrics)
        return outMetrics.widthPixels
    }

    inner class AlbumAdapter : BaseAdapter<AlbumBase>() {
        override fun convert(holder: BaseViewHolder, item: AlbumBase) {
            if (item is Album) {
                val ivAlbum = holder.getView<ImageView>(R.id.iv_album)
                GlideImageLoader.load(
                    requireContext(),
                    ivAlbum,
                    item.url,
                    R.drawable.ic_album_default,
                    R.drawable.ic_album_default
                )
            } else {
                holder.setText(R.id.tv_date, item.date)
            }
        }

        init {
            addItemType(AlbumBase.DATE, R.layout.item_album_date)
            addItemType(AlbumBase.ALBUM, R.layout.item_album_recycler)
        }
    }
}