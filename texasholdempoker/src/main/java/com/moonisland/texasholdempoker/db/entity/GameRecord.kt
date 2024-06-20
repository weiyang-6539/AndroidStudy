package com.moonisland.texasholdempoker.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author WeiYang
 * @date 2024/6/19
 * @desc 对局记录
 */
@Entity
data class GameRecord(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    val title: String,
    val playerIds: MutableList<String>, // 对局玩家id
    val startTime: String,
    val endTime: String,

)