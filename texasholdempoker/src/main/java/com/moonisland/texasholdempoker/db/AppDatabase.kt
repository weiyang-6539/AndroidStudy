package com.moonisland.texasholdempoker.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.moonisland.texasholdempoker.bean.Player
import com.moonisland.texasholdempoker.bean.PlayerRecord
import com.moonisland.texasholdempoker.db.dao.PlayerDao
import com.moonisland.texasholdempoker.db.dao.PlayerRecordDao

/**
 * @author WeiYang
 * @date 2024/6/19
 * @desc
 */
@Database(version = 1, entities = [Player::class, PlayerRecord::class], exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private var instance: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            return instance ?: Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "app_database"
            ).build().apply { instance = this }
        }
    }

    abstract fun playerDao(): PlayerDao

    abstract fun playerRecordDao(): PlayerRecordDao
}