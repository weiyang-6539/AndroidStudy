package com.moonisland.texasholdempoker.bean

/**
 * @author WeiYang
 * @date 2024/6/18
 * @desc
 */
data class Player(
    val id: String,
    val name: String,
    val magnification: Float = .4f, // 倍率
    val startScore: Int = -1000, // 初始比分
    val endScore: Int, // 结束比分
)
