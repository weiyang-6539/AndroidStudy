package com.moonisland.texasholdempoker.db.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

/**
 * @author WeiYang
 * @date 2024/6/19
 * @desc 玩家对局信息
 */
@Entity
data class PlayerRecord(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
) {
    @Ignore
    constructor() : this(0)
}