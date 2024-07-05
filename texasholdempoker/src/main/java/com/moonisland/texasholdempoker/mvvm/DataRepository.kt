package com.moonisland.texasholdempoker.mvvm

import android.util.Log
import com.moonisland.texasholdempoker.db.AppDatabase
import com.moonisland.texasholdempoker.db.entity.GameRecord
import com.moonisland.texasholdempoker.db.entity.Player
import com.moonisland.texasholdempoker.db.entity.PlayerRecord
import com.moonisland.texasholdempoker.global.App
import com.moonisland.texasholdempoker.utils.DataProvider
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
    private val playerMap = HashMap<Long, Player>()

    suspend fun queryAllPlayer() = handleApiCall {
        runCatching {
            val players = mAppDatabase.playerDao().queryAll()
            // 暂时写死
            if (players.isEmpty())
                ApiResult.Success(DataProvider.buildRankList().onEach {
                    playerMap[it.id] = it
                })
            else
                ApiResult.Success(players)
        }.getOrElse {
            ApiResult.Failed(Exception(it))
        }
    }

    suspend fun queryUserById(id: Long) = handleApiCall {
        runCatching {
            val player = mAppDatabase.playerDao().queryUser(id)
            if (player == null) {
                ApiResult.Success(playerMap[id])
            } else
                ApiResult.Success(player)
        }.getOrElse {
            ApiResult.Failed(Exception(it))
        }
    }

    suspend fun createGameRecord(gameRecord: GameRecord) = handleApiCall {
        runCatching {
            val gid = mAppDatabase.gameRecordDao().insertGameRecord(gameRecord)
            gameRecord.id = gid
            // 循环插入个人对局信息
            gameRecord.playerIds.forEach {
                mAppDatabase.playerRecordDao().insert(
                    PlayerRecord(
                        0,
                        it,
                        gid,
                    )
                )
            }
            ApiResult.Success(gameRecord)
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

    suspend fun queryPlayerRecordsByGid(gid: Long) = handleApiCall {
        runCatching {
            DataProvider.buildRankList().onEach {
                playerMap[it.id] = it
            }
            // 暂不会联查
            val list = mAppDatabase.playerRecordDao().queryPlayerRecordsByGid(gid).onEach {
                it.player = playerMap[it.pid]
            }
            ApiResult.Success(list)
        }.getOrElse {
            ApiResult.Failed(Exception(it))
        }
    }

    suspend fun updateGameRecord(gameRecord: GameRecord) = handleApiCall {
        runCatching {
            ApiResult.Success(mAppDatabase.gameRecordDao().updateGameRecord(gameRecord))
        }.getOrElse {
            ApiResult.Failed(Exception(it))
        }
    }

    suspend fun updatePlayerRecord(playerRecord: PlayerRecord) = handleApiCall {
        runCatching {
            ApiResult.Success(mAppDatabase.playerRecordDao().update(playerRecord))
        }.getOrElse {
            ApiResult.Failed(Exception(it))
        }
    }

    suspend fun inertPlayerRecord(playerRecord: PlayerRecord) = handleApiCall {
        runCatching {
            ApiResult.Success(mAppDatabase.playerRecordDao().insert(playerRecord))
        }.getOrElse {
            ApiResult.Failed(Exception(it))
        }
    }
}