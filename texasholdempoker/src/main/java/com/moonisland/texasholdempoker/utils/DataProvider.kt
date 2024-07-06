package com.moonisland.texasholdempoker.utils

import com.moonisland.texasholdempoker.db.entity.Player
import com.w6539.base_jetpack.utils.MessageDigestUtils

/**
 * @author WeiYang
 * @date 2024/6/19
 * @desc
 */
object DataProvider {
    private val playerNameList = arrayListOf(
        "袁梦",
        "袁鹏",
        "王晓波",
        "魏洋",
        "饶煦庭",
        "胡逸超",
        "李哲",
        "严雷",
        "高登",
        "郭纳谏",
        "段海鹏",
        "石闯勋",
        "李望",
        "王璞",
        "邱云亮",
        "覃枫",
    )

    fun buildRankList(): List<Player> {
        var id = 1L
        return playerNameList.map {
            Player(
                id++,
                it,
                it,
                MessageDigestUtils.md5(it),
            )
        }
    }

    fun getPlayerById() {}
}