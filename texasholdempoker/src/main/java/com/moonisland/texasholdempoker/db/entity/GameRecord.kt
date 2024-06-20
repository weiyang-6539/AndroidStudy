package com.moonisland.texasholdempoker.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

/**
 * @author WeiYang
 * @date 2024/6/19
 * @desc 对局记录
 */
@Entity
@TypeConverters()
data class GameRecord(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    val name: String,
    val playerIds: MutableList<Long>, // 对局玩家id
    val startTime: String,
    val endTime: String,
)