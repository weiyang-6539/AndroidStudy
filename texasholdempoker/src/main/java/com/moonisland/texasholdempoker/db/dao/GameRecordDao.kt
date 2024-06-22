package com.moonisland.texasholdempoker.db.dao

import androidx.room.Dao
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

    @Update
    fun updateGameRecord(gameRecord: GameRecord)

    @Query("SELECT * FROM gamerecord WHERE id = :id")
    fun queryGameRecordById(id: Long): GameRecord

    @Query("select * from gamerecord  ORDER BY startTime DESC")
    fun queryAll(): List<GameRecord>
}