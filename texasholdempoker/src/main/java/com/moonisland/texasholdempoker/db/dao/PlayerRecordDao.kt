package com.moonisland.texasholdempoker.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.moonisland.texasholdempoker.db.entity.PlayerRecord

/**
 * @author WeiYang
 * @date 2024/6/19
 * @desc
 */
@Dao
interface PlayerRecordDao {
    @Insert
    fun insert(playerRecord: PlayerRecord): Long

    @Update
    fun update(playerRecord: PlayerRecord)

    @Query(" SELECT * FROM playerrecord WHERE gid = :gid")
    fun queryPlayerRecordsByGid(gid: Long): List<PlayerRecord>

    @Query("select * from playerrecord")
    fun queryPlayerRecords(): List<PlayerRecord>
}