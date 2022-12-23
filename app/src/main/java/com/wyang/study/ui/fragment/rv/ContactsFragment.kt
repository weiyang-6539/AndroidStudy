package com.wyang.study.ui.fragment.rv

import android.util.Log
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.w6539android.baselib.ext.json2List
import com.w6539android.baselib.widget.decoration.StickyDecoration
import com.wyang.study.R
import com.wyang.study.databinding.LayoutRecyclerBinding
import com.wyang.study.ext.chineseChar2EN
import com.wyang.study.ext.openAsset
import com.wyang.study.ui.base.BaseFragment

/**
 * 仿微信通讯录, 首字母获取使用 pinyin4j jar
 */
class ContactsFragment : BaseFragment<LayoutRecyclerBinding>() {

    override fun getViewBinding(): LayoutRecyclerBinding {
        return LayoutRecyclerBinding.inflate(layoutInflater)
    }

    override fun initialize() {
        mBinding.mRecyclerView.adapter =
            object : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_contacts_recycler) {
                override fun convert(helper: BaseViewHolder, item: String) {
                    helper.setText(R.id.tv_nickname, item)
                }
            }.apply {
                setNewData(
                    context?.openAsset("contacts.json")?.json2List<String>()
                )
            }

        val list = requireContext().openAsset("contacts.json").json2List<String>()
        for (i in 0 until list.size) {
            val chineseChar2EN = list[i].chineseChar2EN()
            Log.e(mTag, chineseChar2EN)
        }
    }

}