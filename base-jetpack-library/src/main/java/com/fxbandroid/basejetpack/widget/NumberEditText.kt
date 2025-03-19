package com.fxbandroid.basejetpack.widget

import android.content.Context
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import kotlin.math.min

/**
 * @author Yang
 * @date 2024/11/19
 * @desc 自定义格式输入框 手机号,身份证 空格分隔
 */
class NumberEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = android.R.attr.editTextStyle
) : AppCompatEditText(context, attrs, defStyleAttr) {

    // 默认格式为 344
    private var formatArr = intArrayOf(3, 4, 4)
    private var maxLength = 11
    private var isFormatting = false
    private var lastFormattedText = ""

    init {
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(it: CharSequence?, start: Int, before: Int, count: Int) {
                if (isFormatting) return
                val input = (it?.toString() ?: "").replace(" ", "")

                isFormatting = true
                val formatted = formatPhoneNumber(input)
                var index = start + 1
                if (formatted != lastFormattedText) {
                    if (formatted.getOrNull(start) == ' ') {
                        if (before == 0) {
                            index++
                        } else {
                            index--
                        }
                    } else {
                        if (before == 1) {
                            index--
                        }
                    }
                    lastFormattedText = formatted
                    setText(formatted)
                    setSelection(min(lastFormattedText.length, index))
                } else {
                    if (before == 0) {
                        index++
                    } else {
                        index--
                    }
                    setText(formatted)
                    setSelection(min(formatted.length, index))
                }
                isFormatting = false
            }

            override fun afterTextChanged(it: Editable?) {
            }
        })
    }

    private fun formatPhoneNumber(raw: String): String {
        val builder = StringBuilder()
        var index = 0
        for (length in formatArr) {
            if (index >= raw.length) break
            val end = (index + length).coerceAtMost(raw.length)
            builder.append(raw.substring(index, end))
            if (end < raw.length)
                builder.append(" ")
            index += length
        }
        return builder.toString()
    }

    /**
     * 设置自定义格式
     * @param format 格式数组，如 intArrayOf(3, 3, 4)
     */
    fun setFormatArr(format: IntArray) {
        formatArr = format
        maxLength = 0
        format.forEach {
            maxLength += it
        }
        filters = arrayOf(InputFilter.LengthFilter(maxLength + format.size - 1))
    }
}