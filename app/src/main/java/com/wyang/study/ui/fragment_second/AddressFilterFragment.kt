package com.wyang.study.ui.fragment_second

import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.trello.rxlifecycle4.android.FragmentEvent
import com.wyang.study.R
import com.wyang.study.databinding.FragmentAddressFilterBinding
import com.wyang.study.ui.base.BaseFragment
import com.wyang.study.ui.util.TreeHelper
import com.wyang.study.utils.AssetUtil
import com.wyang.study.utils.TreeNode
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class AddressFilterFragment : BaseFragment<FragmentAddressFilterBinding>() {
    private var mTreeHelper: TreeHelper? = null

    override fun getViewBinding(): FragmentAddressFilterBinding {
        return FragmentAddressFilterBinding.inflate(layoutInflater)
    }

    override fun initialize() {
        Observable.just("1")
            .map {
                AssetUtil.buildAddressTree(
                    context
                )
            }
            .compose(bindUntilEvent(FragmentEvent.DESTROY))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<TreeHelper> {
                override fun onSubscribe(d: Disposable) {
                    Log.e("Idea", "数据解析中..")
                }

                override fun onNext(treeHelper: TreeHelper) {
                    Log.e("Idea", "数据解析完成")
                    mTreeHelper = treeHelper
                    //数据初始化完才init
                    mBinding!!.mRecyclerView.layoutManager = LinearLayoutManager(context)
                    mAdapter.bindToRecyclerView(mBinding!!.mRecyclerView)
                }

                override fun onError(e: Throwable) {
                    Log.e("Idea", "数据解析失败,$e")
                }

                override fun onComplete() {}
            })

        mBinding!!.tvFilter1.setOnClickListener { onClickFilter1() }
        mBinding!!.tvFilter2.setOnClickListener { onClickFilter2() }
        mBinding!!.tvFilter3.setOnClickListener { onClickFilter3() }
        mBinding!!.tvFilter4.setOnClickListener { onClickFilter4() }
    }


    fun onClickFilter1() {
        if (!init()) return
        val results = mTreeHelper!!.provincesSeeker()
            .attribute("code", "43")
            .descendants()
            .results()
        mAdapter.setNewData(results)
    }

    fun onClickFilter2() {
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

    fun onClickFilter3() {
        if (!init()) return
        val results = mTreeHelper!!.rootSeeker()
            .descendants()
            .matchPredicate { treeNode: TreeNode ->
                val name = treeNode.getAttribute<String>("name")
                val level = treeNode.getAttribute<Int>("level")
                if (name == null || level == null) return@matchPredicate false
                name.contains("阳") && (level == 2 || level == 3)
            }
            .results()
        mAdapter.setNewData(results)
    }

    fun onClickFilter4() {
        if (!init()) return
        val results = mTreeHelper!!.rootSeeker()
            .descendants()
            .matchPredicate { treeNode: TreeNode ->
                val code = treeNode.getAttribute<String>("code")
                !TextUtils.isEmpty(code) && code.startsWith("1")
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

    private val mAdapter: BaseQuickAdapter<TreeNode, BaseViewHolder> =
        object : BaseQuickAdapter<TreeNode, BaseViewHolder>(R.layout.item_address_filter_recycler) {
            override fun convert(helper: BaseViewHolder, item: TreeNode) {
                helper.setText(R.id.tv_name, item.getAttribute<String>("name"))
                helper.setText(R.id.tv_code, item.getAttribute<String>("code"))
            }
        }
}