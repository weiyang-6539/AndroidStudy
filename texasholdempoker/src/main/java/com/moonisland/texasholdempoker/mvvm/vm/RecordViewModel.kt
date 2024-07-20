package com.moonisland.texasholdempoker.mvvm.vm

import androidx.lifecycle.MutableLiveData
import com.moonisland.texasholdempoker.db.entity.GameRecord
import com.moonisland.texasholdempoker.db.entity.Player
import com.moonisland.texasholdempoker.db.entity.PlayerRecord
import com.moonisland.texasholdempoker.mvvm.DataRepository
import com.w6539.base_jetpack.base.vm.BaseViewModel
import com.w6539.base_jetpack.net.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import javax.inject.Inject

/**
 * @author Yang
 * @date 2024/6/20
 * @desc
 */
@HiltViewModel
class RecordViewModel @Inject constructor(
    private val mRepository: DataRepository
) : BaseViewModel() {
    val playerListResult = MutableLiveData<List<Player>>()
    val createGameRecordResult = MutableLiveData<GameRecord>()
    val gameRecordListResult = MutableLiveData<List<GameRecord>>()
    val gameRecordDetailResult = MutableLiveData<GameRecord>()
    val playerRecordsResult = MutableLiveData<List<PlayerRecord>>()
    val updateRecordResult = MutableLiveData<Any>()
    val deleteRecordResult = MutableLiveData<Any>()

    fun queryAllPlayer() {
        launchOnUI {
            launchOnIO {
                mRepository.queryAllPlayer()
            }.checkSuccess {
                playerListResult.postValue(it ?: emptyList())
            }.checkFailed {
                loadError(it)
            }
        }
    }

    fun queryUserById(id: Long) {
        launchOnUI {
            launchOnIO {
                mRepository.queryUserById(id)
            }.checkSuccess {

            }.checkFailed {
                loadError(it)
            }
        }
    }

    fun createRecord(gameRecord: GameRecord) {
        launchOnUI {
            launchOnIO {
                mRepository.createGameRecord(gameRecord)
            }.checkSuccess {
                createGameRecordResult.postValue(it)
            }.checkFailed {
                loadError(it)
            }
        }
    }

    fun queryAllGameRecord() {
        launchOnUI {
            launchOnIO {
                mRepository.queryAllGameRecord()
            }.checkSuccess {
                gameRecordListResult.postValue(it)
            }.checkFailed {
                loadError(it)
            }
        }
    }

    fun queryGameRecordById(id: Long) {
        launchOnUI {
            val async1 = async {
                launchOnIO {
                    mRepository.queryPlayerRecordsByGid(id)
                }
            }
            val async2 = async {
                launchOnIO {
                    mRepository.queryGameRecordById(id)
                }
            }
            val await1 = async1.await()
            val await2 = async2.await()
            if (await1 is ApiResult.Success && await2 is ApiResult.Success) {
                await1.checkSuccess {
                    playerRecordsResult.postValue(it)
                }
                await2.checkSuccess {
                    gameRecordDetailResult.postValue(it)
                }
            } else {
                await1.checkFailed {
                    loadError(it)
                }
                await2.checkFailed {
                    loadError(it)
                }
            }
        }
    }

    /**
     * 借贷还贷 flag为true为借， 反之为还
     */
    fun invokeLoan(playerRecord: PlayerRecord, flag: Boolean) {
        launchOnUI {
            val gameRecord = gameRecordDetailResult.value?.apply {
                if (flag)
                    score += 1000
                else
                    score -= 1000
            }

            val async1 = async {
                launchOnIO {
                    mRepository.updateGameRecord(gameRecord!!)
                }
            }

            val async2 = async {
                launchOnIO {
                    mRepository.updatePlayerRecord(playerRecord)
                }
            }

            val await1 = async1.await()
            val await2 = async2.await()

            if (await1 is ApiResult.Success && await2 is ApiResult.Success) {
                await1.checkSuccess {
                    gameRecordDetailResult.postValue(gameRecord!!)
                }
                await2.checkSuccess {
                    updateRecordResult.postValue(it)
                }
            } else {
                await1.checkFailed {
                    loadError(it)
                }
                await2.checkFailed {
                    loadError(it)
                }
            }
        }
    }

    fun insertPlayerRecord(playerRecord: PlayerRecord) {
        launchOnUI {
            launchOnIO {
                mRepository.inertPlayerRecord(playerRecord)
            }.checkSuccess {
                updateRecordResult.postValue(it)
            }.checkFailed {
                loadError(it)
            }
        }
    }

    /**
     * 更新玩家个人对局信息， 修改倍率， 修改得分
     */
    fun updatePlayerRecord(playerRecord: PlayerRecord) {
        launchOnUI {
            launchOnIO {
                mRepository.updatePlayerRecord(playerRecord)
            }.checkSuccess {
                updateRecordResult.postValue(it)
            }.checkFailed {
                loadError(it)
            }
        }
    }

    fun deletePlayerRecords(ids: ArrayList<Long>) {
        launchOnUI {
            launchOnIO {
                mRepository.queryPlayerRecordByIds(ids)
            }.checkSuccess { list ->
                gameRecordDetailResult.value?.apply {

                    list?.forEach {
                        this.playerIds.remove(it.id)
                        this.score -= it.loan
                    }

                    updateGameRecord(this)
                }
            }.checkFailed {
                loadError(it)
            }
        }
    }

    fun updateGameRecord(gameRecord: GameRecord) {
        launchOnUI {
            val async1 = async {
                launchOnIO {
                    mRepository.updateGameRecord(gameRecord)
                }
            }

            val await1 = async1.await()

            val async2 = async {
                launchOnIO {
                    mRepository.queryPlayerRecordsByGid(gameRecord.id)
                }
            }

            val await2 = async2.await()

            if (await1 is ApiResult.Success && await2 is ApiResult.Success) {
                await1.checkSuccess {
                    gameRecordDetailResult.postValue(gameRecord)
                }
                await2.checkSuccess {
                    playerRecordsResult.postValue(it)
                }
            } else {
                await1.checkFailed {
                    loadError(it)
                }
                await2.checkFailed {
                    loadError(it)
                }
            }
        }
    }

    fun deleteGameRecord(gameRecord: GameRecord) {
        launchOnUI {
            launchOnIO {
                mRepository.deleteGameRecord(gameRecord)
            }.checkSuccess {
                deleteRecordResult.postValue(gameRecord)
            }.checkFailed {
                loadError(it)
            }
        }
    }
}