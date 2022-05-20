package com.wyang.study.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.wyang.study.R
import com.wyang.study.bean.Simple

class SimpleAdapter:BaseQuickAdapter<Simple,BaseViewHolder>(R.layout.item_simple_recycler) {
    override fun convert(helper: BaseViewHolder, item: Simple) {
        helper.setText(R.id.tv_title, item.title)
        helper.setText(R.id.tv_description, item.description)
    }
}