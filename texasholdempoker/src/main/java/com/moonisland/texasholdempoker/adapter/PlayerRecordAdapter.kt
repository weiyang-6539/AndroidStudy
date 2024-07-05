package com.moonisland.texasholdempoker.adapter

import androidx.compose.ui.graphics.Color
import com.chad.library.adapter4.viewholder.QuickViewHolder
import com.moonisland.texasholdempoker.R
import com.moonisland.texasholdempoker.db.entity.PlayerRecord
import com.moonisland.texasholdempoker.ext.formatFloat
import com.w6539.base_jetpack.base.recycleview.BaseAdapter

/**
 * @author Yang
 * @date 2024/6/21
 * @desc
 */
class PlayerRecordAdapter(
    var status: Int = 0
) : BaseAdapter<PlayerRecord>(R.layout.item_player_record_recycler) {
    override fun convert(holder: QuickViewHolder, item: PlayerRecord) {
        holder.setText(R.id.tv_name, "姓名：${item.player?.name}")
            .setText(R.id.tv_load, "欠分：${item.loan}（贷款筹码数）")
            .setText(R.id.tv_score, "结余：${item.score}（当前积分，手中筹码数）")
            .setText(R.id.tv_rate, "倍率：${item.rate.formatFloat()}")
            .setText(R.id.tv_payoff, "结现：${item.money.formatFloat()}")
            .setTextColorRes(
                R.id.tv_payoff,
                if (item.money == 0f) R.color.gray_a else if (item.money < 0) android.R.color.holo_red_light else android.R.color.holo_green_light
            )
            .setGone(R.id.btn_loan_, status == 2)
            .setGone(R.id.btn_loan, status == 2)
            .setGone(R.id.btn_rate, item.isLockRate || status == 2)
            .setGone(R.id.btn_payoff, status == 2)
    }

    fun getUidList() = ArrayList<Long>().apply {
        items.forEach {
            add(it.pid)
        }
    }
}