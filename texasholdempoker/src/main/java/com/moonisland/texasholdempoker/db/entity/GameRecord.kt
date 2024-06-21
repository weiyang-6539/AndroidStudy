package com.moonisland.texasholdempoker.db.entity

import androidx.room.ColumnInfo
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
    @ColumnInfo(name = "name", typeAffinity = ColumnInfo.TEXT)
    var name: String,
    @ColumnInfo(name = "playerIds", typeAffinity = ColumnInfo.TEXT)
    val playerIds: MutableList<Long>, // 对局玩家id
    @ColumnInfo(name = "score", typeAffinity = ColumnInfo.INTEGER)
    var score: Int,// 对局总分数
    @ColumnInfo(name = "money", typeAffinity = ColumnInfo.REAL)
    var money: Float = 0f,// 输赢的人民币
    @ColumnInfo(name = "deductMoney", typeAffinity = ColumnInfo.REAL)
    var deductMoney: Float = 0f, // 抽水
    @ColumnInfo(name = "bonusMoney", typeAffinity = ColumnInfo.REAL)
    var bonusMoney: Float = 0f, // 奖金
    @ColumnInfo(name = "status", typeAffinity = ColumnInfo.INTEGER)
    var status: Int = 0, // 0未开始1进行中2已结算(结束)
    val startTime: Long = System.currentTimeMillis(),
    var endTime: Long = 0,
)