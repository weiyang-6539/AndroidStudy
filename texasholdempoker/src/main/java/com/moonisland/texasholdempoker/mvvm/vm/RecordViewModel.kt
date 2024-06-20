package com.moonisland.texasholdempoker.mvvm.vm

import androidx.lifecycle.MutableLiveData
import com.moonisland.texasholdempoker.db.entity.GameRecord
import com.moonisland.texasholdempoker.db.entity.Player
import com.moonisland.texasholdempoker.mvvm.DataRepository
import com.w6539.base_jetpack.base.vm.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
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
    val createGameRecordResult = MutableLiveData<Boolean>()
    val gameRecordListResult = MutableLiveData<List<GameRecord>>()
    val gameRecordDetailResult = MutableLiveData<GameRecord>()

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

    fun createRecord(gameRecord: GameRecord) {
        launchOnUI {
            launchOnIO {
                mRepository.createGameRecord(gameRecord)
            }.checkSuccess {
                createGameRecordResult.postValue(true)
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

    fun queryGameRecordById (id:Long){
        launchOnUI {
            launchOnIO {
                mRepository.queryGameRecordById(id)
            }.checkSuccess {
                gameRecordDetailResult.postValue(it)
            }.checkFailed {
                loadError(it)
            }
        }
    }
}