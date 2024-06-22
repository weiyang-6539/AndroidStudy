package com.moonisland.texasholdempoker.strategy

import com.moonisland.texasholdempoker.db.entity.GameRecord
import com.moonisland.texasholdempoker.db.entity.PlayerRecord
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

/**
 * @author Yang
 * @date 2024/6/21
 * @desc
 */
class Strategy01 : IStrategy {
    private val minDeductMoney = 200f // 最低扣除
    private val deductRate = .2f // 抽水比率, 不足取最低值

    override fun calculate(gr: GameRecord, prs: List<PlayerRecord>) {
        var loseScore = 0 // 统计总输分
        var loserRateTotal = 0f
        var virtualLoseMoney = 0f
        var winnerRateTotal = 0f
        var virtualWinnerMoney = 0f
        val losers = prs.filter {
            val result = it.score < it.loan
            if (result) {
                loserRateTotal += it.rate
                // 贷款 - 得分 = 输分
                loseScore += (it.loan - it.score)
                virtualLoseMoney += it.rate * (it.loan - it.score) //计算正数
            }
            result
        }
        val winners = prs.filter {
            val result = it.score >= it.loan
            if (result) {
                winnerRateTotal += it.rate
                virtualWinnerMoney += it.rate * (it.score - it.loan)
            }
            result
        }

        val loserRate = loserRateTotal / losers.size
        val winnerRate = winnerRateTotal / winners.size

        if (loserRate < winnerRate) {
            // 收钱
            losers.forEach {
                // 输家计算负数
                it.money = (it.score - it.loan) * it.rate
                // 统计总金额
                gr.money += abs(it.money)
            }
            // 抽水
            gr.deductMoney = max(min(minDeductMoney, gr.money), gr.money * deductRate)
            gr.bonusMoney = max(gr.money - gr.deductMoney, 0f)
            // 分钱
            winners.forEach {
                it.money = gr.bonusMoney * (it.rate * (it.score - it.loan)) / virtualWinnerMoney
            }
        } else {
            // 预收现金
            gr.money = winnerRate * loseScore
            // 抽水
            gr.deductMoney = max(min(minDeductMoney, gr.money), gr.money * deductRate)
            gr.bonusMoney = max(gr.money - gr.deductMoney, 0f)
            // 分钱
            winners.forEach {
                it.money = gr.bonusMoney * (it.rate * (it.score - it.loan)) / virtualWinnerMoney
            }
            // 收钱
            losers.forEach {
                // 输家计算负数
                it.money = gr.money * (it.rate * (it.score - it.loan)) / virtualLoseMoney
            }
        }

        // 修改状态为已结算
        gr.status = 2
        gr.endTime = System.currentTimeMillis()
    }
}