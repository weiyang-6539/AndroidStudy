package com.wyang.study.adapter

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import com.w6539android.base.ui.recycler.BaseAdapter
import com.w6539android.base.ui.recycler.BaseViewHolder
import com.wyang.study.R
import com.wyang.study.bean.SectionItem
import com.wyang.study.ui.helper.IDragDelegate

class DragSortAdapter(
    itemTouchHelper: ItemTouchHelper
) : BaseAdapter<SectionItem>(), IDragDelegate {
    private var itemTouchHelper: ItemTouchHelper? = null
    private var minActivatedPos = 0
    private var maxActivatedPos = 0
    private var currentPos = 3 //当前选中项

    private var isEdit = false

    init {
        this.itemTouchHelper = itemTouchHelper

        addItemType(SectionItem.TYPE_HEADER_MINE, R.layout.item_drag_header_mine_recycler)
        addItemType(SectionItem.TYPE_HEADER_OTHER, R.layout.item_drag_header_other_recycler)
        addItemType(SectionItem.TYPE_ITEM, R.layout.item_drag_channel_recycler)
    }

    override fun getItemSpanCount(position: Int):Int {
        val item = get(position)
        if (item.getItemType() == SectionItem.TYPE_HEADER_MINE || item.getItemType() == SectionItem.TYPE_HEADER_OTHER)
            return 100
        return 1
    }

    fun isEdit() = isEdit

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

    override fun convert(holder: BaseViewHolder, item: SectionItem) {
        when (item.getItemType()) {
            SectionItem.TYPE_HEADER_MINE -> {
                holder.setText(R.id.tv_title, item.title)
                    .setText(R.id.tv_edit, if (isEdit) "完成" else "编辑")
                    .setText(R.id.tv_prompt, if (isEdit) "拖拽可以排序" else "点击进入频道")
            }

            SectionItem.TYPE_HEADER_OTHER -> {
                holder.setText(R.id.tv_title, item.title)
                    .setText(R.id.tv_prompt, "点击添加频道")
            }

            else -> {
                item.channel?.apply {
                    if (isMine) {
                        holder.setGone(R.id.iv_delete, isEdit && isActivated)
                    } else {
                        holder.setGone(R.id.iv_delete, false)
                    }
                    holder
                        .setText(R.id.tv_title, name)
                        .setGone(R.id.iv_add, isMine)
                        .setGone(R.id.iv_delete, !isMine || !isEdit || !isActivated)

                    //非编辑状态才显示选中状态
                    if (!isEdit && holder.layoutPosition == currentPos) {
                        holder.setTextColor(R.id.tv_title, -0x999a)
                    } else {
                        holder.getView<View>(R.id.tv_title).isActivated = isActivated
                    }
                    holder.getView<View>(R.id.ll_container).isSelected = isMine

                    //我的频道拖拽事件的监听,逻辑判断条件如下
                    holder.itemView.setOnLongClickListener {
                        //仅在我的频道下响应长按事件
                        if (isMine) {
                            if (isActivated) itemTouchHelper!!.startDrag(holder)

                            //编辑状态下,可编辑响应拖拽
                            if (!isEdit) enableEdit()
                        }
                        true
                    }
                }
            }
        }
    }

    override fun onItemMove(fromPos: Int, toPos: Int) {
        if (toPos < minActivatedPos || toPos > maxActivatedPos) return
        swap(fromPos, toPos)
    }

    /**
     * 添加频道
     */
    fun addMine(position: Int) {
        move(position, maxActivatedPos++ + 1)
    }

    /**
     * 移除频道
     */
    fun removeMine(position: Int) {
        move(position, maxActivatedPos-- + 1)
        if (currentPos > maxActivatedPos) {
            currentPos = maxActivatedPos
        }
    }
}