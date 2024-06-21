package com.moonisland.texasholdempoker.db.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.moonisland.texasholdempoker.global.Config

/**
 * @author WeiYang
 * @date 2024/6/19
 * @desc 玩家对局信息
 */
@Entity
data class PlayerRecord(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    val pid: Long = 0,
    val gid: Long = 0,
    var score: Int = 0, // 得分界面输入
    var loan: Int = Config.INIT_SCORE, // 贷款积分
    var rate: Float = Config.INIT_RATE, // 参赛倍率
    var money: Float = 0f, // 结算现金
) {
    @Ignore
    constructor() : this(0)

    @Ignore
    @Transient
    var player: Player? = null
}