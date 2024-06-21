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
            val playerRecordsRst = async {
                launchOnIO {
                    mRepository.queryPlayerRecordsByGid(id)
                }
            }
            val gameRecordRst = async {
                launchOnIO {
                    mRepository.queryGameRecordById(id)
                }
            }
            val playerRecords = playerRecordsRst.await()
            val gameRecord = gameRecordRst.await()
            if (playerRecords is ApiResult.Success && gameRecord is ApiResult.Success) {
                playerRecords.checkSuccess {
                    playerRecordsResult.postValue(it)
                }
                gameRecord.checkSuccess {
                    gameRecordDetailResult.postValue(it)
                }
            } else {
                playerRecords.checkFailed {
                    loadError(it)
                }
                gameRecord.checkFailed {
                    loadError(it)
                }
            }
        }
    }

}