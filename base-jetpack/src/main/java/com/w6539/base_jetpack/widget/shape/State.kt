package com.w6539.base_jetpack.widget.shape

import androidx.annotation.IntDef

/**
 * @author Yang
 * @since 2022/6/23 13:20
 * @desc View的状态
 */
@IntDef(
    State.NONE,
    State.ENABLED,
    State.PRESSED,
    State.DISABLE,
    State.SELECTED,
    State.FOCUSED,
    State.CHECKED
)
@Retention(AnnotationRetention.SOURCE)
annotation class State {
    companion object {
        const val NONE = 0
        const val ENABLED = android.R.attr.state_enabled
        const val PRESSED = android.R.attr.state_pressed
        const val DISABLE = -android.R.attr.state_enabled
        const val SELECTED = android.R.attr.state_selected
        const val FOCUSED = android.R.attr.state_focused
        const val CHECKED = android.R.attr.state_checked
    }
}
