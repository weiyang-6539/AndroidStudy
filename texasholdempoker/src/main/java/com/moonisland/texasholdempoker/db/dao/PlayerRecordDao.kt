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

    @Query("select * from playerrecord where id in (:ids)")
    fun queryByIds(ids: Array<Long>): List<PlayerRecord>

    @Query("delete from playerrecord where id in (:ids)")
    fun deleteByIds(ids: Array<Long>)

    @Query("select * from playerrecord where gid = :gid")
    fun queryPlayerRecordsByGid(gid: Long): List<PlayerRecord>

    @Query("select * from playerrecord")
    fun queryPlayerRecords(): List<PlayerRecord>

    @Query("delete from playerrecord where gid = :gid")
    fun deletePlayerRecordsByGid(gid: Long)
}