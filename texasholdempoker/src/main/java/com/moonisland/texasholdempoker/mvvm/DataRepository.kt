package com.moonisland.texasholdempoker.mvvm

import com.moonisland.texasholdempoker.db.AppDatabase
import com.moonisland.texasholdempoker.db.entity.GameRecord
import com.moonisland.texasholdempoker.global.App
import com.w6539.base_jetpack.base.vm.BaseRepository
import com.w6539.base_jetpack.net.ApiResult
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Yang
 * @date 2024/6/20
 * @desc
 */
@Singleton
class DataRepository @Inject constructor() : BaseRepository() {
    private val mAppDatabase = AppDatabase.getInstance(App.instance)

    suspend fun queryAllPlayer() = handleApiCall {
        runCatching {
            ApiResult.Success(mAppDatabase.playerDao().queryAll())
        }.getOrElse {
            ApiResult.Failed(Exception(it))
        }
    }

    suspend fun createGameRecord(gameRecord: GameRecord) = handleApiCall {
        runCatching {
            ApiResult.Success(mAppDatabase.gameRecordDao().insertGameRecord(gameRecord))
        }.getOrElse {
            ApiResult.Failed(Exception(it))
        }
    }

    suspend fun queryAllGameRecord() = handleApiCall {
        runCatching {
            ApiResult.Success(mAppDatabase.gameRecordDao().queryAll())
        }.getOrElse {
            ApiResult.Failed(Exception(it))
        }
    }

    suspend fun queryGameRecordById(id: Long) = handleApiCall {
        runCatching {
            ApiResult.Success(mAppDatabase.gameRecordDao().queryGameRecordById(id))
        }.getOrElse {
            ApiResult.Failed(Exception(it))
        }
    }
}