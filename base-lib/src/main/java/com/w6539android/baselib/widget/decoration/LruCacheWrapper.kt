package com.w6539android.baselib.widget.decoration

import androidx.collection.LruCache

/**
 * @author Yang
 * @since 2022/11/2 17:26
 * @desc
 */
class LruCacheWrapper<Int, T> : LruCache<Int, T>(2 * 1024 * 1024) {

}