package com.moonisland.texasholdempoker.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.moonisland.texasholdempoker.bean.Player

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

    @Query("select * from Player")
    fun queryAll(): List<Player>
}