package com.moonisland.texasholdempoker.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.moonisland.texasholdempoker.db.converters.ArrayConverters
import com.moonisland.texasholdempoker.db.dao.GameRecordDao
import com.moonisland.texasholdempoker.db.entity.Player
import com.moonisland.texasholdempoker.db.entity.PlayerRecord
import com.moonisland.texasholdempoker.db.dao.PlayerDao
import com.moonisland.texasholdempoker.db.dao.PlayerRecordDao
import com.moonisland.texasholdempoker.db.entity.GameRecord

/**
 * @author WeiYang
 * @date 2024/6/19
 * @desc
 */
@Database(
    version = 1,
    entities = [
        Player::class,
        PlayerRecord::class,
        GameRecord::class,
    ],
    exportSchema = false
)
@TypeConverters(ArrayConverters::class)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private var instance: AppDatabase? = null
        private const val DB_NAME = "app_database"

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            return instance ?: Room.databaseBuilder(
                context, AppDatabase::class.java, DB_NAME
            ).build().apply { instance = this }
        }
    }

    abstract fun playerDao(): PlayerDao

    abstract fun playerRecordDao(): PlayerRecordDao

    abstract fun gameRecordDao(): GameRecordDao
}