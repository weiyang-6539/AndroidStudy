package com.wyang.study.adapter

import com.w6539android.base.ui.recycler.BaseDifferAdapter
import com.w6539android.base.ui.recycler.BaseViewHolder
import com.wyang.study.R
import com.wyang.study.bean.Simple

class SimpleAdapter : BaseDifferAdapter<Simple>(R.layout.item_simple_recycler) {
    override fun convert(holder: BaseViewHolder, item: Simple) {
        holder.setText(R.id.tv_title, item.title)
            .setText(R.id.tv_description, item.description)
    }
}