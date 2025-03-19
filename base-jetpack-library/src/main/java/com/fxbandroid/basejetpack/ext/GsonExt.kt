package com.fxbandroid.basejetpack.ext

import com.fxbandroid.basejetpack.utils.MapTypeAdapter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken

/**
 * @author yang
 * @date 2024/5/17
 * @desc gson转换拓展
 */
private val gson = GsonBuilder()
    .registerTypeAdapter(object : TypeToken<Map<String, Any>>() {}.type, MapTypeAdapter())
    .create()

fun Any.bean2Json(): String {
    return gson.toJson(this)
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