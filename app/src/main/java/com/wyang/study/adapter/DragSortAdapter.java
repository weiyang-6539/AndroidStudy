package com.wyang.study.adapter;

import android.support.v7.widget.helper.ItemTouchHelper;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wyang.study.R;
import com.wyang.study.bean.Channel;
import com.wyang.study.bean.SectionItem;
import com.wyang.study.ui.helper.IDragDelegate;

/**
 * Created by weiyang on 2019/6/20.
 */
public class DragSortAdapter extends BaseSectionQuickAdapter<SectionItem, BaseViewHolder> implements IDragDelegate {
    private ItemTouchHelper itemTouchHelper;
    private int minActivatedPos;
    private int maxActivatedPos;
    private int currentPos = 3;//当前选中项
    private boolean isEdit;

    public boolean isEdit() {
        return isEdit;
    }

    public void enableEdit() {
        isEdit = !isEdit;
        notifyDataSetChanged();
    }

    public void setMinActivatedPos(int minActivatedPos) {
        this.minActivatedPos = minActivatedPos;
    }

    public void setMaxActivatedPos(int maxActivatedPos) {
        this.maxActivatedPos = maxActivatedPos;
    }

    public void setCurrentPos(int currentPos) {
        this.currentPos = currentPos;
        notifyDataSetChanged();
    }

    public DragSortAdapter(ItemTouchHelper itemTouchHelper) {
        super(R.layout.item_drag_channel_recycler, R.layout.item_drag_header_recycler, null);
        this.itemTouchHelper = itemTouchHelper;
    }

    @Override
    protected void convertHead(BaseViewHolder helper, SectionItem item) {
        helper.addOnClickListener(R.id.tv_edit);

        helper.setText(R.id.tv_edit, isEdit ? "完成" : "编辑");
        helper.setText(R.id.tv_title, item.header);
        if (helper.getAdapterPosition() == 0) {
            helper.setGone(R.id.tv_edit, true);
            helper.setText(R.id.tv_prompt, isEdit ? "拖拽可以排序" : "点击进入频道");
        } else {
            helper.setGone(R.id.tv_edit, false);
            helper.setText(R.id.tv_prompt, "点击添加频道");
        }
    }

    @Override
    protected void convert(BaseViewHolder helper, SectionItem item) {
        Channel channel = item.t;
        if (channel.isMine()) {
            helper.setGone(R.id.iv_delete, isEdit && channel.isActivated());
        } else {
            helper.setGone(R.id.iv_delete, false);
        }
        helper.setGone(R.id.iv_add, !channel.isMine());
        helper.setText(R.id.tv_title, channel.getName());

        //非编辑状态才显示选中状态
        if (!isEdit && helper.getLayoutPosition() == currentPos) {
            helper.setTextColor(R.id.tv_title, 0xffff6666);
        } else {
            helper.getView(R.id.tv_title).setActivated(channel.isActivated());
        }

        helper.getView(R.id.ll_container).setSelected(channel.isMine());

        //我的频道拖拽事件的监听,逻辑判断条件如下
        helper.itemView.setOnLongClickListener(v -> {
            //仅在我的频道下响应长按事件
            if (channel.isMine()) {
                //编辑状态下,可编辑响应拖拽
                if (!isEdit)
                    enableEdit();
                if (channel.isActivated())
                    itemTouchHelper.startDrag(helper);
            }
            return true;
        });
    }


    @Override
    public void onItemMove(int fromPos, int toPos) {
        if (toPos < minActivatedPos || toPos > maxActivatedPos)
            return;
        SectionItem item = mData.get(fromPos);
        mData.remove(fromPos);
        mData.add(toPos, item);

        notifyItemMoved(fromPos, toPos);
    }

    /**
     * 添加频道
     */
    public void addMine(int position) {
        SectionItem item = mData.get(position);

        mData.remove(position);
        mData.add(maxActivatedPos + 1, item);

        notifyItemChanged(position);
        notifyItemChanged(maxActivatedPos+1);
        notifyItemMoved(position, maxActivatedPos + 1);

        maxActivatedPos++;
    }

    /**
     * 移除频道
     */
    public void removeMine(int position) {
        SectionItem item = mData.get(position);

        mData.remove(position);
        mData.add(maxActivatedPos + 1, item);

        notifyItemChanged(position);
        notifyItemChanged(maxActivatedPos+1);
        notifyItemMoved(position, maxActivatedPos + 1);

        maxActivatedPos--;
    }
}
