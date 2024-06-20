package com.moonisland.texasholdempoker.ui.fragment

import android.util.Log
import com.moonisland.texasholdempoker.databinding.FragmentRankBinding
import com.moonisland.texasholdempoker.db.AppDatabase
import com.moonisland.texasholdempoker.utils.DataProvider
import com.w6539.base_jetpack.base.fragment.BaseVBFragment
import com.w6539.base_jetpack.ext.bean2Json
import kotlin.concurrent.thread

/**
 * @author WeiYang
 * @date 2024/6/19
 * @desc
 */
class RankFragment : BaseVBFragment<FragmentRankBinding>() {

    override fun initialize() {
        init()
//        query()
    }

    private fun init() {
        thread {
            AppDatabase.getInstance(requireContext())
                .playerDao()
                .insertPlayers(*DataProvider.buildRankList().toTypedArray())
        }
    }

    private fun query() {
        thread {
            val bean2Json = AppDatabase.getInstance(requireContext())
                .playerDao()
                .queryAll()
                .bean2Json()
            Log.e(mTag, bean2Json)
        }
    }
}