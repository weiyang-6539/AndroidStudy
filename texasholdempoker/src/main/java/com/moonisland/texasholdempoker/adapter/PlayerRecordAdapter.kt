package com.moonisland.texasholdempoker.adapter

import com.chad.library.adapter4.viewholder.QuickViewHolder
import com.moonisland.texasholdempoker.R
import com.moonisland.texasholdempoker.db.entity.PlayerRecord
import com.w6539.base_jetpack.base.recycleview.BaseAdapter

/**
 * @author Yang
 * @date 2024/6/21
 * @desc
 */
class PlayerRecordAdapter : BaseAdapter<PlayerRecord>(R.layout.item_player_record_recycler) {
    override fun convert(holder: QuickViewHolder, item: PlayerRecord) {
        holder.setText(R.id.tv_name, "姓名：${item.player?.name}")
            .setText(R.id.tv_load, "欠分：${item.loan}")
            .setText(R.id.tv_score, "得分：${item.score}")
            .setText(R.id.tv_rate, "倍率：${item.rate}")
            .setText(R.id.tv_payoff, "结现：${item.money}")
    }
}