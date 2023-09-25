package com.wyang.study.ui.fragment.rv

import android.util.Log
import com.w6539android.base.base.fragment.BaseFragment
import com.w6539android.base.ext.json2List
import com.w6539android.base.ui.recycler.BaseDifferAdapter
import com.w6539android.base.ui.recycler.BaseViewHolder
import com.wyang.study.R
import com.wyang.study.databinding.LayoutRecyclerBinding
import com.wyang.study.ext.chineseChar2EN
import com.wyang.study.ext.openAsset

/**
 * 仿微信通讯录, 首字母获取使用 pinyin4j jar
 */
class ContactsFragment : BaseFragment<LayoutRecyclerBinding>() {

    override fun getViewBinding() = LayoutRecyclerBinding.inflate(layoutInflater)

    override fun initialize() {
        mBinding.mRecyclerView.adapter = object : BaseDifferAdapter<String>(R.layout.item_contacts_recycler) {
                override fun convert(holder: BaseViewHolder, item: String) {
                    holder.setText(R.id.tv_nickname, item)
                }
            }.apply {
                setNewData(
                    context?.openAsset("contacts.json")?.json2List<String>()!!.toList()
                )
            }

        val list = requireContext().openAsset("contacts.json").json2List<String>()
        for (i in 0 until list.size) {
            val chineseChar2EN = list[i].chineseChar2EN()
            Log.e(mTag, chineseChar2EN)
        }
    }

}