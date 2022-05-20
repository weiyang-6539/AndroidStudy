package com.wyang.study.adapter

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import com.chad.library.adapter.base.BaseSectionQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.wyang.study.R
import com.wyang.study.bean.SectionItem
import com.wyang.study.ui.helper.IDragDelegate

class DragSortAdapter (itemTouchHelper: ItemTouchHelper): BaseSectionQuickAdapter<SectionItem, BaseViewHolder>(
    R.layout.item_drag_channel_recycler,
    R.layout.item_drag_header_recycler,
    null
), IDragDelegate {
    private var itemTouchHelper: ItemTouchHelper? = null
    private var minActivatedPos = 0
    private var maxActivatedPos = 0
    private var currentPos = 3 //当前选中项

    private var isEdit = false

    init {
        this.itemTouchHelper = itemTouchHelper
    }

    fun isEdit(): Boolean {
        return isEdit
    }

    @SuppressLint("NotifyDataSetChanged")
    fun enableEdit() {
        isEdit = !isEdit
        notifyDataSetChanged()
    }

    fun setMinActivatedPos(minActivatedPos: Int) {
        this.minActivatedPos = minActivatedPos
    }

    fun setMaxActivatedPos(maxActivatedPos: Int) {
        this.maxActivatedPos = maxActivatedPos
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setCurrentPos(currentPos: Int) {
        this.currentPos = currentPos
        notifyDataSetChanged()
    }

    override fun convertHead(helper: BaseViewHolder, item: SectionItem) {
        helper.addOnClickListener(R.id.tv_edit)
        helper.setText(R.id.tv_edit, if (isEdit) "完成" else "编辑")
        helper.setText(R.id.tv_title, item.header)
        if (helper.adapterPosition == 0) {
            helper.setGone(R.id.tv_edit, true)
            helper.setText(R.id.tv_prompt, if (isEdit) "拖拽可以排序" else "点击进入频道")
        } else {
            helper.setGone(R.id.tv_edit, false)
            helper.setText(R.id.tv_prompt, "点击添加频道")
        }
    }

    override fun convert(helper: BaseViewHolder, item: SectionItem) {
        val channel = item.t
        if (channel.isMine) {
            helper.setGone(R.id.iv_delete, isEdit && channel.isActivated)
        } else {
            helper.setGone(R.id.iv_delete, false)
        }
        helper.setGone(R.id.iv_add, !channel.isMine)
        helper.setText(R.id.tv_title, channel.name)

        //非编辑状态才显示选中状态
        if (!isEdit && helper.layoutPosition == currentPos) {
            helper.setTextColor(R.id.tv_title, -0x999a)
        } else {
            helper.getView<View>(R.id.tv_title).isActivated = channel.isActivated
        }
        helper.getView<View>(R.id.ll_container).isSelected = channel.isMine

        //我的频道拖拽事件的监听,逻辑判断条件如下
        helper.itemView.setOnLongClickListener {
            //仅在我的频道下响应长按事件
            if (channel.isMine) {
                if (channel.isActivated) itemTouchHelper!!.startDrag(helper)

                //编辑状态下,可编辑响应拖拽
                if (!isEdit) enableEdit()
            }
            true
        }
    }


    override fun onItemMove(fromPos: Int, toPos: Int) {
        if (toPos < minActivatedPos || toPos > maxActivatedPos) return
        val item = mData[fromPos]
        mData.removeAt(fromPos)
        mData.add(toPos, item)
        notifyItemMoved(fromPos, toPos)
    }

    /**
     * 添加频道
     */
    fun addMine(position: Int) {
        val item = mData[position]
        mData.removeAt(position)
        mData.add(maxActivatedPos + 1, item)
        notifyItemChanged(position)
        notifyItemChanged(maxActivatedPos + 1)
        notifyItemMoved(position, maxActivatedPos + 1)
        maxActivatedPos++
    }

    /**
     * 移除频道
     */
    fun removeMine(position: Int) {
        val item = mData[position]
        mData.removeAt(position)
        mData.add(maxActivatedPos + 1, item)
        notifyItemChanged(position)
        notifyItemChanged(maxActivatedPos + 1)
        notifyItemMoved(position, maxActivatedPos + 1)
        maxActivatedPos--
        if (currentPos > maxActivatedPos) {
            currentPos = maxActivatedPos
        }
    }
}