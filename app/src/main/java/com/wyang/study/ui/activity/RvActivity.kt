package com.wyang.study.ui.activity

import android.annotation.SuppressLint
import androidx.recyclerview.widget.ConcatAdapter
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.w6539android.base.base.activity.BaseVBActivity
import com.w6539android.base.ui.recycler.BaseAdapter
import com.w6539android.base.ui.recycler.BaseDifferAdapter
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

    @SuppressLint("CheckResult")
    override fun initialize() {
        val concatAdapter = ConcatAdapter(ConcatAdapter.Config.DEFAULT)
        concatAdapter.addAdapter(mBannerAdapter)
        concatAdapter.addAdapter(otherAdapter)
        concatAdapter.addAdapter(mAreaAdapter)
        concatAdapter.addAdapter(mPartAdapter)
//        concatAdapter.addAdapter(mContentAdapter)

//        val flexboxLayoutManager = FlexboxLayoutManager(this)
//        flexboxLayoutManager.flexDirection = FlexDirection.ROW
//        flexboxLayoutManager.flexWrap = FlexWrap.WRAP
//        mBinding.mRecyclerView.layoutManager = flexboxLayoutManager
        mBinding.mRecyclerView.adapter = concatAdapter
        mBinding.mRecyclerView.addItemDecoration(
            SpacingDecoration.newDecoration()
                .setPadding(150,0,150,0)
        )
    }

    private val mBannerAdapter = object : BaseAdapter<Banner>(R.layout.layout_home_banner) {

        init {
            add(Banner())
        }

        override fun getItemSpanCount(position: Int) = 100

        override fun convert(holder: BaseViewHolder, item: Banner) {

        }
    }

    private val otherAdapter = object : BaseAdapter<Any>(R.layout.item_test) {

        init {
            add("")
            add("")
            add("")
        }

        override fun convert(holder: BaseViewHolder, item: Any) {
            holder.setText(R.id.tv_title, "测试item${holder.bindingAdapterPosition}")
        }
    }

    private val mAreaAdapter = object : BaseAdapter<Area>(R.layout.layout_home_area) {
        init {
            add(Area())
        }

        override fun getItemSpanCount(position: Int) = 100

        override fun convert(holder: BaseViewHolder, item: Area) {

        }
    }
    private val mPartAdapter = object : BaseAdapter<Any>(R.layout.item_simple_recycler) {
        init {
            for (i in 0 until 8) {
                add("")
            }
        }

        override fun convert(holder: BaseViewHolder, item: Any) {
            holder.setText(R.id.tv_title, "文本标题" + holder.bindingAdapterPosition)
                .setText(R.id.tv_description, "文本内容" + holder.bindingAdapterPosition)
        }
    }
    private val mContentAdapter = object : BaseDifferAdapter<Any>(R.layout.item_simple_recycler) {
        init {

        }

        override fun convert(holder: BaseViewHolder, item: Any) {
        }
    }
}