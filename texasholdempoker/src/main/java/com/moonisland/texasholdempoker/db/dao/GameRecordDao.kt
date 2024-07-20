package com.moonisland.texasholdempoker.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.moonisland.texasholdempoker.db.entity.GameRecord

/**
 * @author yang
 * @date 2024/6/20
 * @desc
 */
@Dao
interface GameRecordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGameRecord(gameRecord: GameRecord): Long

    @Delete
    fun deleteGameRecord(gameRecord: GameRecord)

    @Update
    fun updateGameRecord(gameRecord: GameRecord)

    @Query("select * from gamerecord where id = :id")
    fun queryGameRecordById(id: Long): GameRecord

    @Query("select * from gamerecord order by startTime desc")
    fun queryAll(): List<GameRecord>
}