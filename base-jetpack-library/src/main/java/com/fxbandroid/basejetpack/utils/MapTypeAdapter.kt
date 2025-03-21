package com.fxbandroid.basejetpack.utils

import com.google.gson.TypeAdapter
import com.google.gson.internal.LinkedTreeMap
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.lang.IllegalStateException
import java.util.ArrayList

/**
 * @author Yang
 * @since 2022/6/28 11:38
 * @desc Gson类型兼容
 */
class MapTypeAdapter : TypeAdapter<Any>() {

    override fun read(reader: JsonReader): Any? {
        when (reader.peek()) {
            JsonToken.BEGIN_ARRAY -> {
                val list = ArrayList<Any>()
                reader.beginArray()
                while (reader.hasNext()) {
                    read(reader)?.let { list.add(it) }
                }
                reader.endArray()
                return list
            }

            JsonToken.BEGIN_OBJECT -> {
                val map: MutableMap<String, Any> = LinkedTreeMap()
                reader.beginObject()
                while (reader.hasNext()) {
                    map[reader.nextName()] = read(reader) as Any
                }
                reader.endObject()
                return map
            }

            JsonToken.STRING -> return reader.nextString()
            JsonToken.NUMBER -> {
                //改写数字的处理逻辑，将数字值分为整型与浮点型。
                val numberStr: String = reader.nextString()
                if (numberStr.contains(".")
                    || numberStr.contains("e")
                    || numberStr.contains("E")
                ) return numberStr.toDouble()

                if (numberStr.toLong() <= Int.MAX_VALUE)
                    return numberStr.toInt()

                return numberStr.toLong()
            }

            JsonToken.BOOLEAN -> return reader.nextBoolean()
            JsonToken.NULL -> {
                reader.nextNull()
                return null
            }

            else -> throw IllegalStateException()
        }
    }

    override fun write(writer: JsonWriter, value: Any) {

    }
}