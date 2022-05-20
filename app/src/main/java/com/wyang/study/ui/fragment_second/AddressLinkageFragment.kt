package com.wyang.study.ui.fragment_second

import android.util.Log
import android.util.SparseArray
import android.util.SparseIntArray
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.trello.rxlifecycle4.android.FragmentEvent
import com.wyang.study.R
import com.wyang.study.databinding.FragmentAddressLinkageBinding
import com.wyang.study.ui.base.BaseFragment
import com.wyang.study.ui.util.TreeHelper
import com.wyang.study.utils.AssetUtil
import com.wyang.study.utils.NodeSeeker
import com.wyang.study.utils.TreeNode
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class AddressLinkageFragment : BaseFragment<FragmentAddressLinkageBinding>() {
    private var mTreeHelper: TreeHelper? = null
    private val data = SparseArray<List<TreeNode>>()
    private val selectPos = SparseIntArray()

    override fun getViewBinding(): FragmentAddressLinkageBinding {
        return FragmentAddressLinkageBinding.inflate(layoutInflater)
    }

    override fun initialize() {
        mBinding!!.mTabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val position = tab.position
                mAdapter.setNewData(data[position])
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        mBinding!!.mRecyclerView.layoutManager = LinearLayoutManager(context)

        mAdapter.bindToRecyclerView(mBinding!!.mRecyclerView)
        mAdapter.setOnItemClickListener { _: BaseQuickAdapter<*, *>?, _: View?, position: Int ->
            //记录每页选中的position
            setSelectPosition(position)
            val node = mAdapter.data[position]
            val results =
                NodeSeeker.newInstance(node).children().results()
            if (results.isNotEmpty()) {
                val next: Int = (mBinding?.mTabLayout?.selectedTabPosition ?: 0) + 1
                selectPos.put(next, -1)
                data.put(next, results)
                while (next + 1 < selectPos.size()) {
                    selectPos.removeAt(selectPos.size() - 1)
                    data.remove(data.size() - 1)
                }
            }
            setData()
        }

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
                    setData()
                }

                override fun onError(e: Throwable) {
                    Log.e("Idea", "数据解析失败,$e")
                }

                override fun onComplete() {}
            })
    }

    private fun getSelectPosition(): Int {
        return selectPos[mBinding?.mTabLayout?.selectedTabPosition!!, -1]
    }

    private fun setSelectPosition(position: Int) {
        mBinding?.mTabLayout?.selectedTabPosition?.let { selectPos.put(it, position) }
    }

    private fun setData() {
        val tabCount: Int? = mBinding?.mTabLayout?.tabCount
        if (tabCount == 0) {
            selectPos.put(0, -1)
            data.put(0, mTreeHelper!!.rootSeeker().children().results())
            mAdapter.setNewData(data[0])
        }
        mBinding?.mTabLayout?.removeAllTabs()
        for (i in 0 until selectPos.size()) {
            val key = selectPos.keyAt(i)
            val position = selectPos.valueAt(i)
            if (position == -1) {
                mBinding?.mTabLayout?.newTab()?.setText("请选择")
                    ?.let { mBinding?.mTabLayout?.addTab(it) }
            } else {
                val list = data[key]
                mBinding?.mTabLayout?.newTab()?.setText(list[position].getAttribute<String>("name"))
                    ?.let {
                        mBinding?.mTabLayout?.addTab(
                            it
                        )
                    }
            }
        }
        val tab: TabLayout.Tab? = mBinding?.mTabLayout?.getTabAt(selectPos.size() - 1)
        tab?.select()
    }

    private val mAdapter: BaseQuickAdapter<TreeNode, BaseViewHolder> =
        object : BaseQuickAdapter<TreeNode, BaseViewHolder>(R.layout.item_address_recycler) {
            override fun convert(helper: BaseViewHolder, item: TreeNode) {
                helper.setText(R.id.tv_name, item.getAttribute<String>("name"))
                when {
                    getSelectPosition() == -1 -> helper.setVisible(
                        R.id.iv_icon,
                        false
                    )
                    getSelectPosition() == helper.adapterPosition -> helper.setVisible(
                        R.id.iv_icon,
                        true
                    )
                    else -> helper.setVisible(R.id.iv_icon, false)
                }
            }
        }
}