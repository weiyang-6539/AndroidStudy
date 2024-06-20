package com.wyang.study.ui.fragment.rv

import androidx.recyclerview.widget.ConcatAdapter
import com.w6539android.base.base.fragment.BaseFragment
import com.w6539android.base.ui.recycler.BaseAdapter
import com.w6539android.base.ui.recycler.BaseViewHolder
import com.wyang.study.R
import com.wyang.study.databinding.LayoutRecyclerBinding

/**
 * @author Yang
 * @since 2023/8/31 15:46
 * @desc
 */
class AppHomeFragment : BaseFragment<LayoutRecyclerBinding>() {

  override fun initialize() {
        val concatAdapter = ConcatAdapter(ConcatAdapter.Config.DEFAULT)
        concatAdapter.addAdapter(mBannerAdapter)
        concatAdapter.addAdapter(mAreaAdapter)
        concatAdapter.addAdapter(mContentAdapter)

        mBinding.mRecyclerView.adapter = concatAdapter
    }

    private val mBannerAdapter = object : BaseAdapter<Any>(R.layout.layout_home_banner) {
        init {
            add("")
        }

        override fun convert(holder: BaseViewHolder, item: Any) {

        }
    }
    private val mAreaAdapter = object : BaseAdapter<Any>(R.layout.layout_home_area) {
        init {
            add("")
        }

        override fun convert(holder: BaseViewHolder, item: Any) {

        }
    }
    private val mContentAdapter = object : BaseAdapter<Any>(R.layout.item_simple_recycler) {
        init {
            add("")
            add("")
            add("")
            add("")
            add("")
            add("")
        }

        override fun convert(holder: BaseViewHolder, item: Any) {
            holder.setText(R.id.tv_title, "文本标题")
                .setText(R.id.tv_description, "文本内容")
        }
    }
}