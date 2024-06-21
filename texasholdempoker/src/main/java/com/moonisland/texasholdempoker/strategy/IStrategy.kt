package com.moonisland.texasholdempoker.strategy

import com.moonisland.texasholdempoker.db.entity.GameRecord
import com.moonisland.texasholdempoker.db.entity.PlayerRecord

/**
 * @author Yang
 * @date 2024/6/21
 * @desc
 */
interface IStrategy {
    fun calculate(gr: GameRecord, prs: MutableList<PlayerRecord>)
}