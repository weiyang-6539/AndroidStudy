package com.moonisland.texasholdempoker.net

import retrofit2.http.GET

/**
 * @author {USER}
 * @date {DATE}
 * @desc
 */
interface DataService {

    @GET("texasholdempoker/blob/master/test.json")
    suspend fun test(): Any
}