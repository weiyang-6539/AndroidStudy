package com.wyang.study.ui.activity

import androidx.recyclerview.widget.ConcatAdapter
import com.w6539android.base.base.activity.BaseVBActivity
import com.w6539android.base.ui.recycler.BaseAdapter
import com.w6539android.base.ui.recycler.BaseViewHolder
import com.w6539android.base.ui.recycler.decoration.SpacingDecoration
import com.wyang.study.R
import com.wyang.study.bean.Area
import com.wyang.study.bean.Banner
import com.wyang.study.databinding.ActivityRecyclerBinding

/**
 * @author Yang
 * @since 2023/9/8 16:20
 * @desc
 */
class RvActivity : BaseVBActivity<ActivityRecyclerBinding>() {
    override fun getViewBinding() = ActivityRecyclerBinding.inflate(layoutInflater)
    override fun initialize() {
        val concatAdapter = ConcatAdapter(ConcatAdapter.Config.DEFAULT)
        concatAdapter.addAdapter(mBannerAdapter)
        concatAdapter.addAdapter(mAreaAdapter)
        concatAdapter.addAdapter(mContentAdapter)

        mBinding.mRecyclerView.adapter = concatAdapter
        mBinding.mRecyclerView.addItemDecoration(
            SpacingDecoration.newDecoration()
        )
    }

    private val mBannerAdapter = object : BaseAdapter<Banner>(R.layout.layout_home_banner) {

        init {
            add(Banner())
            add(Banner())
            add(Banner())
        }

        override fun isFullSpanAdapter() = true

        override fun isFullSpanItem(position: Int): Boolean {
            return super.isFullSpanItem(position)
        }

        override fun convert(holder: BaseViewHolder, item: Banner) {

        }
    }
    private val mAreaAdapter = object : BaseAdapter<Area>(R.layout.layout_home_area) {
        init {
            add(Area())
            add(Area())
            add(Area())
        }

        override fun isFullSpanAdapter() = true

        override fun convert(holder: BaseViewHolder, item: Area) {

        }
    }
    private val mContentAdapter = object : BaseAdapter<Any>(R.layout.item_simple_recycler) {
        init {
            for (i in 0 until 100) {
                add("")
            }
        }

        override fun isFullSpanItem(position: Int): Boolean {
            return super.isFullSpanItem(position)
        }

        override fun convert(holder: BaseViewHolder, item: Any) {
            holder.setText(R.id.tv_title, "文本标题")
                .setText(R.id.tv_description, "文本内容")
        }
    }
}