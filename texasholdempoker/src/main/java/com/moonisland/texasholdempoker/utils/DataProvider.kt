package com.moonisland.texasholdempoker.utils

import com.moonisland.texasholdempoker.bean.Player
import com.w6539android.base.utils.MD5Util

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
        "胡毅超",
        "李哲",
        "严雷",
        "段海鹏",
        "石闯勋",
        "李望",
        "高登",
        "郭纳谏",
        "王璞",
        "邱云亮",
        "覃枫",
    )

    fun buildRankList(): List<Player> {
        return playerNameList.map {
            Player(
                0,
                it,
                it,
                MD5Util.encode(it),
            )
        }
    }
}