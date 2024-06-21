package com.moonisland.texasholdempoker.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
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

    @Query("SELECT * FROM gamerecord WHERE id = :id")
    fun queryGameRecordById(id: Long): GameRecord

    @Query("select * from gamerecord")
    fun queryAll(): List<GameRecord>
}