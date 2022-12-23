package com.w6539android.baselib.ext

import com.google.gson.Gson
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import java.lang.Exception
import java.util.ArrayList

/**
 * @author Yang
 * @since 2022/12/9 15:24
 * @desc
 */
fun Any.bean2Json(): String {
    return Gson().toJson(this)
}

inline fun <reified T> String.json2Bean(): T {
    return Gson().fromJson(this, T::class.java)
}

inline fun <reified T> String.json2List(): MutableList<T> {
    val gson = Gson()
    val list = ArrayList<T>()
    try {
        val array = JsonParser.parseString(this).asJsonArray
        for (elem in array) {
            list.add(gson.fromJson(elem, T::class.java))
        }
    } catch (ignored: Exception) {
        ignored.printStackTrace()
    }
    return list
}

inline fun <reified T> String.json2ListMaps(): MutableList<MutableMap<String, T>> {
    return Gson().fromJson(this, object : TypeToken<MutableList<MutableMap<String, T>>>() {}.type)
}

inline fun <reified T> String.json2Maps(): MutableMap<String, T> {
    return Gson().fromJson(this, object : TypeToken<Map<String, T>>() {}.type)
}