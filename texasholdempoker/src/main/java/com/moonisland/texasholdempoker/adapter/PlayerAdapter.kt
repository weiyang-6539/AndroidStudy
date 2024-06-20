package com.moonisland.texasholdempoker.adapter

import android.widget.CheckBox
import com.chad.library.adapter4.viewholder.QuickViewHolder
import com.moonisland.texasholdempoker.R
import com.moonisland.texasholdempoker.db.entity.Player
import com.w6539.base_jetpack.base.recycleview.BaseAdapter

/**
 * @author yang
 * @date 2024/6/20
 * @desc
 */
class PlayerAdapter : BaseAdapter<Player>(R.layout.item_player_recycler) {
    private val checkedIds = ArrayList<Long>()
    override fun convert(holder: QuickViewHolder, item: Player) {
        holder.setText(R.id.tv_name, "${item.id}${item.name}${item.md5}")
        holder.getView<CheckBox>(R.id.mCheckBox).isChecked = checkedIds.contains(item.id)
    }

    fun resetChecked(position: Int) {
        getItem(position)?.apply {
            if (checkedIds.contains(id)) {
                checkedIds.remove(id)
            } else {
                checkedIds.add(id)
            }
        }
        notifyItemChanged(position)
    }

    fun getCheckIds() = checkedIds
}