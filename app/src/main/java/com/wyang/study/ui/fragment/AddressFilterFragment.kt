package com.wyang.study.ui.fragment

import android.text.TextUtils
import android.widget.Toast
import com.w6539android.base.base.fragment.BaseFragment
import com.w6539android.base.ui.bravh.BaseListAdapter
import com.w6539android.base.ui.bravh.BaseViewHolder
import com.wyang.study.R
import com.wyang.study.databinding.FragmentAddressFilterBinding
import com.wyang.study.declare.TreeNode
import com.wyang.study.ui.util.TreeHelper
import java.util.*

class AddressFilterFragment : BaseFragment<FragmentAddressFilterBinding>() {
    private var mTreeHelper: TreeHelper? = null

    override fun getViewBinding() = FragmentAddressFilterBinding.inflate(layoutInflater)

    override fun initialize() {
//        Observable.just("1")
//            .map { AssetUtil.buildAddressTree(context) }
//            .compose(bindUntilEvent(FragmentEvent.DESTROY))
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(object : Observer<TreeHelper> {
//                override fun onSubscribe(d: Disposable) {
//                    Log.e("Idea", "数据解析中..")
//                }
//
//                override fun onNext(treeHelper: TreeHelper) {
//                    Log.e("Idea", "数据解析完成")
//                    mTreeHelper = treeHelper
//                    //数据初始化完才init
//                    mBinding.mRecyclerView.layoutManager = LinearLayoutManager(context)
//                    mAdapter.bindToRecyclerView(mBinding.mRecyclerView)
//                }
//
//                override fun onError(e: Throwable) {
//                    Log.e("Idea", "数据解析失败,$e")
//                }
//
//                override fun onComplete() {}
//            })

        mBinding.tvFilter1.setOnClickListener { onClickFilter1() }
        mBinding.tvFilter2.setOnClickListener { onClickFilter2() }
        mBinding.tvFilter3.setOnClickListener { onClickFilter3() }
        mBinding.tvFilter4.setOnClickListener { onClickFilter4() }
    }

    private fun onClickFilter1() {
        if (!init()) return
        val results = mTreeHelper!!.provincesSeeker()
            .attribute("code", "43")
            .descendants()
            .results()
        mAdapter.setNewData(results)
    }

    private fun onClickFilter2() {
        if (!init()) return
        val results = mTreeHelper!!.rootSeeker()
            .descendants()
            .matchPredicate { treeNode: TreeNode ->
                val name = treeNode.getAttribute<String>("name")
                !TextUtils.isEmpty(name) && name.contains("长沙")
            }
            .results()
        mAdapter.setNewData(results)
    }

    private fun onClickFilter3() {
        if (!init()) return
        val results = mTreeHelper!!.rootSeeker()
            .descendants()
            .matchPredicate { treeNode: TreeNode ->
                val name = treeNode.getAttribute<String>("name")
                val level = treeNode.getAttribute<Int>("level")
                if (name == null || level == null)
                    return@matchPredicate false
                return@matchPredicate name.contains("阳") && (level == 2 || level == 3)
            }
            .results()
        mAdapter.setNewData(results)
    }

    private fun onClickFilter4() {
        if (!init()) return
        val results = mTreeHelper!!.rootSeeker()
            .descendants()
            .matchPredicate { treeNode: TreeNode ->
                val code = treeNode.getAttribute<String>("code")
                return@matchPredicate !TextUtils.isEmpty(code) && code.startsWith("1")
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

    private val mAdapter: BaseListAdapter<TreeNode> = object : BaseListAdapter<TreeNode>(
        R.layout.item_address_filter_recycler
    ) {
        override fun convert(holder: BaseViewHolder, item: TreeNode) {
            holder.setText(R.id.tv_name, item.getAttribute<String>("name"))
            holder.setText(R.id.tv_code, item.getAttribute<String>("code"))
        }
    }
}