package com.moonisland.texasholdempoker.db.converters

import androidx.room.TypeConverter
import com.w6539.base_jetpack.ext.bean2Json
import com.w6539.base_jetpack.ext.json2List

/**
 * @author yang
 * @date 2024/6/20
 * @desc
 */
object ArrayConverters {

    @TypeConverter
    fun json2Array(json: String): MutableList<Long> {
        return json.json2List<Long>()
    }

    @TypeConverter
    fun array2Json(list: List<Long>): String {
        return list.bean2Json()
    }
}