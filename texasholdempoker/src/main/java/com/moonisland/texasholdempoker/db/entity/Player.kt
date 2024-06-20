package com.moonisland.texasholdempoker.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * @author WeiYang
 * @date 2024/6/18
 * @desc
 */
@Entity(
    tableName = "player",
    indices = [
        Index(value = ["id"], unique = true),
        Index(value = ["md5"], unique = true)
    ]
)
data class Player(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    @ColumnInfo(name = "name", typeAffinity = ColumnInfo.TEXT) val name: String = "",
    @ColumnInfo(name = "nickname", typeAffinity = ColumnInfo.TEXT) val nickname: String = "",
    @ColumnInfo(name = "md5", typeAffinity = ColumnInfo.TEXT) val md5: String = "",
) {
    @Ignore
    constructor() : this(0)
}
