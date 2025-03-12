package com.wyang.study.ui.widget

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

/**
 * @author Yang
 * @date 2024/7/22
 * @desc 九个符号译文自动换行不截断单词
 */
class AutoWrapTextView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    AppCompatTextView(context, attrs, defStyleAttr) {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    private var formatText: CharSequence = ""

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        if (!TextUtils.equals(formatText, text))
            resetText()
    }

    private fun resetText() {
        val tvWidth = (width - compoundPaddingLeft - compoundPaddingRight).toFloat()
        if (text.isNullOrEmpty()) {
            formatText = text
        } else {
            val sb = StringBuilder()

            var lineWidth = 0f // 记录当前行文本所占宽度
            var cursor = 0
            text.forEachIndexed { index, c ->
                // 当前索引字符
                when (c) {
                    ' ' -> {
                        // 空格
                        val word = text.substring(IntRange(cursor, index)) // 单词包含后空格
                        lineWidth += paint.measureText(word, 0, word.length)

                        if (lineWidth > tvWidth) {
                            sb.append("\n")
                            lineWidth = paint.measureText(word, 0, word.length)
                        }
                        sb.append(word)

                        cursor = index + 1
                    }

                    '\t' -> {
                        // 制表符
                    }

                    '\n' -> {
                        // 换行符重新计算
                        lineWidth = 0f
                    }
                }
            }

            if (cursor < text.length) {
                val word = text.substring(cursor)
                lineWidth += paint.measureText(word, 0, word.length)

                if (lineWidth > tvWidth) {
                    sb.append("\n")
                    lineWidth = paint.measureText(word, 0, word.length)
                }
                sb.append(word)
            }

            formatText = sb.toString()
            text = formatText

            requestLayout()
        }
    }
}