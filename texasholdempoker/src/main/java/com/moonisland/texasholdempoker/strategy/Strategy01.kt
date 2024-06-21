package com.moonisland.texasholdempoker.strategy

import com.moonisland.texasholdempoker.db.entity.GameRecord
import com.moonisland.texasholdempoker.db.entity.PlayerRecord
import kotlin.math.max

/**
 * @author Yang
 * @date 2024/6/21
 * @desc
 */
class Strategy01 : IStrategy {
    private val minDeductMoney = 200f // 最低扣除
    private val deductRate = .2f // 抽水比率, 不足取最低值

    override fun calculate(gr: GameRecord, prs: MutableList<PlayerRecord>) {
        var loseScore = 0 // 统计总输分
        var loserRateTotal = 0f
        var winnerRateTotal = 0f
        val losers = prs.filter {
            loserRateTotal += it.rate
            // 贷款 - 得分 = 输分
            loseScore += it.loan - it.score
            it.score < it.loan
        }
        val winners = prs.filter {
            winnerRateTotal += it.rate
            it.score >= it.loan
        }

        val loserRate = loserRateTotal / losers.size
        val winnerRate = winnerRateTotal / winners.size

        if (loserRate < winnerRate) {
            // 收钱
            losers.forEach {
                it.money = (it.loan - it.score) * it.rate
                // 统计总金额
                gr.money += it.money
            }
            // 抽水
            gr.deductMoney = max(minDeductMoney, gr.money * deductRate)
            gr.bonusMoney = gr.money - gr.deductMoney
            // 分钱
            winners.forEach {
                val scoreWeight = (it.score - it.loan) * 1f / loseScore // 比分权重
                val rateWeight = it.rate / winnerRate // 倍率权重
                it.money = scoreWeight * rateWeight * gr.bonusMoney
            }
        } else {
            // 预收现金
            gr.money = winnerRate * loseScore
            // 抽水
            gr.deductMoney = max(minDeductMoney, gr.money * deductRate)
            gr.bonusMoney = gr.money - gr.deductMoney
            // 分钱
            winners.forEach {
                val scoreWeight = (it.score - it.loan) * 1f / loseScore // 比分权重
                val rateWeight = it.rate / winnerRate // 倍率权重
                it.money = scoreWeight * rateWeight * gr.bonusMoney
            }
            // 收钱
            losers.forEach {
                val scoreWeight = (it.score - it.loan) * 1f / loseScore // 比分权重
                val rateWeight = it.rate / winnerRate // 倍率权重
                it.money = scoreWeight * rateWeight * gr.bonusMoney
            }
        }
    }
}