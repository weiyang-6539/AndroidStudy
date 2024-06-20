package com.moonisland.texasholdempoker.adapter

import com.chad.library.adapter4.viewholder.QuickViewHolder
import com.moonisland.texasholdempoker.R
import com.moonisland.texasholdempoker.db.entity.GameRecord
import com.w6539.base_jetpack.base.recycleview.BaseAdapter

/**
 * @author yang
 * @date 2024/6/20
 * @desc
 */
class GameRecordAdapter : BaseAdapter<GameRecord>(R.layout.item_game_record_recycler) {
    override fun convert(holder: QuickViewHolder, item: GameRecord) {
        holder.setText(R.id.tv_name, "对局：${item.name}")
            .setText(R.id.tv_player, "人数：${item.playerIds.size}")
    }
}