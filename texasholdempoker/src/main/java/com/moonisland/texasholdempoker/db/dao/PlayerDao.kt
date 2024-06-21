package com.moonisland.texasholdempoker.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.moonisland.texasholdempoker.db.entity.Player

/**
 * @author WeiYang
 * @date 2024/6/19
 * @desc
 */
@Dao
interface PlayerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlayer(player: Player)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlayers(vararg player: Player)

    @Query("SELECT * FROM player WHERE id = :id")
    fun queryUser(id: Long): Player?

    @Query("SELECT * FROM player WHERE id in (:ids)")
    fun queryUser(ids: MutableList<Long>): List<Player>

    @Query("select * from player")
    fun queryAll(): List<Player>
}