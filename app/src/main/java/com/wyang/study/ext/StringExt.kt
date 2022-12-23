package com.wyang.study.ext

import android.text.TextUtils
import net.sourceforge.pinyin4j.PinyinHelper

/**
 * @author Yang
 * @since 2022/12/13 9:57
 * @desc
 */
fun String.chineseChar2EN(): String {
    return if (TextUtils.isEmpty(this)) "" else {
        val convert = StringBuilder()
        for (i in 0 until length) {
            val word = get(i)
            val array = PinyinHelper.toHanyuPinyinStringArray(word)
            if (array != null && array.isNotEmpty()) {
                convert.append(array[0][0])
            } else {
                convert.append(word)
            }
        }
        return convert.toString().uppercase()
    }
}