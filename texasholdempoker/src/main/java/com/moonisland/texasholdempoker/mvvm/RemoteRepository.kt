package com.moonisland.texasholdempoker.mvvm

import com.moonisland.texasholdempoker.net.DataClient
import com.w6539.base_jetpack.base.vm.BaseRepository
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author {USER}
 * @date {DATE}
 * @desc
 */
@Singleton
class RemoteRepository @Inject constructor() : BaseRepository() {

    suspend fun test() = coroutineScope {
        DataClient.dataService.test()
    }
}