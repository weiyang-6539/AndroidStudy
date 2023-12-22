package com.wyang.study.ui.fragment

import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.w6539android.base.base.fragment.BaseFragment
import com.w6539android.base.ui.recycler.BaseDifferAdapter
import com.w6539android.base.ui.recycler.BaseViewHolder
import com.wyang.study.R
import com.wyang.study.TreeNode
import com.wyang.study.databinding.FragmentAddressFilterBinding
import com.wyang.study.ui.util.TreeHelper
import com.wyang.study.utils.AssetUtil
import java.util.concurrent.Executors

class AddressFilterFragment : BaseFragment<FragmentAddressFilterBinding>() {
    private var mTreeHelper: TreeHelper? = null

    override fun getViewBinding() = FragmentAddressFilterBinding.inflate(layoutInflater)

    override fun initialize() {
        Executors.newSingleThreadExecutor()
            .execute {
                val treeHelper = AssetUtil.buildAddressTree(context)
                Handler(Looper.getMainLooper()).post {
                    mTreeHelper = treeHelper
                    //数据初始化完才init
                    mBinding.mRecyclerView.layoutManager = LinearLayoutManager(context)
                    mBinding.mRecyclerView.adapter = mAdapter
                }
            }

        mBinding.tvFilter1.setOnClickListener { onClickFilter1() }
        mBinding.tvFilter2.setOnClickListener { onClickFilter2() }
        mBinding.tvFilter3.setOnClickListener { onClickFilter3() }
        mBinding.tvFilter4.setOnClickListener { onClickFilter4() }
    }

    private fun onClickFilter1() {
        if (!init()) return
        val results = mTreeHelper!!.provincesSeeker()
            .descendants()
            .match {
                return@match it["code"] as String == "43"
            }
            .results()
        mAdapter.setNewData(results)
    }

    private fun onClickFilter2() {
        if (!init()) return
        val results = mTreeHelper!!.rootSeeker()
            .descendants()
            .match {
                val name = it["name"] as String
                return@match !TextUtils.isEmpty(name) && name.contains("长沙")
            }
            .results()
        mAdapter.setNewData(results)
    }

    private fun onClickFilter3() {
        if (!init()) return
        val results = mTreeHelper!!.rootSeeker()
            .descendants()
            .match {
                val name = it["name"] as? String
                val level = it["level"]
                return@match name != null && level != null && name.contains("阳") && (level == 2 || level == 3)
            }
            .results()
        mAdapter.setNewData(results.toList())
    }

    private fun onClickFilter4() {
        if (!init()) return
        val results = mTreeHelper!!.rootSeeker()
            .descendants()
            .match {
                val code = it["code"] as String
                return@match !TextUtils.isEmpty(code) && code.startsWith("1")
            }
            .results()
        mAdapter.setNewData(results)
    }

    private fun init(): Boolean {
        if (mTreeHelper == null) {
            Toast.makeText(context, "数据初始化中..", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private val mAdapter: BaseDifferAdapter<TreeNode> = object : BaseDifferAdapter<TreeNode>(
        R.layout.item_address_filter_recycler
    ) {
        override fun convert(holder: BaseViewHolder, item: TreeNode) {
            holder.setText(R.id.tv_name, item["name"] as String)
            holder.setText(R.id.tv_code, item["code"] as String)
        }
    }
}