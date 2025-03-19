package com.fxbandroid.basejetpack.ext

/**
 * @author yang
 * @date 2024/12/5
 * @desc
 */
fun Boolean.toInt() = if (this) 1 else 0

fun Int?.toBoolean() = this == 1

/** 检查某个位是否为1 */
fun Int.isBitSet(position: Int): Boolean = (this and (1 shl position)) != 0
fun Long.isBitSet(position: Int): Boolean = (this and (1L shl position)) != 0L

/** 设置某个位为1 */
fun Int.setBit(position: Int): Int = this or (1 shl position)
fun Long.setBit(position: Int): Long = this or (1L shl position)

/** 清除某个位（置0） */
fun Int.clearBit(position: Int): Int = this and (1 shl position).inv()
fun Long.clearBit(position: Int): Long = this and (1L shl position).inv()

/** 反转某个位 */
fun Int.toggleBit(position: Int): Int = this xor (1 shl position)
fun Long.toggleBit(position: Int): Long = this xor (1L shl position)