package com.wyang.study.bean

import java.io.Serializable

/**
 * @author Yang
 * @since 2022/11/3 14:32
 * @desc
 */
data class Simple(
    val title: String,
    val description: String,
    val className: String,
) : Serializable