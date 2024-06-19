package com.moonisland.texasholdempoker.bean

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

/**
 * @author WeiYang
 * @date 2024/6/19
 * @desc
 */
@Entity
data class PlayerRecord(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
) {
    @Ignore
    constructor() : this(0)
}