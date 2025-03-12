package com.wyang.study.ui.fragment

import android.annotation.SuppressLint
import android.util.Log
import android.view.MotionEvent
import androidx.constraintlayout.widget.ConstraintSet
import androidx.transition.TransitionManager
import com.w6539android.base.base.fragment.BaseFragment
import com.wyang.study.databinding.FragmentTestConstraintBinding

/**
 * @author Yang
 * @date 2025/2/14
 * @desc
 */
class TestConstraintFragment : BaseFragment<FragmentTestConstraintBinding>() {

    @SuppressLint("ClickableViewAccessibility")
    override fun initialize() {
        with(mBinding) {
//            ConstraintSet().apply {
//                clone(root)
//                connect(
//                    draggableView.id,
//                    ConstraintSet.START,
//                    ConstraintSet.PARENT_ID,
//                    ConstraintSet.START,
//                    100
//                )
//                connect(
//                    draggableView.id,
//                    ConstraintSet.TOP,
//                    ConstraintSet.PARENT_ID,
//                    ConstraintSet.TOP,
//                    100
//                )
//                applyTo(root)
//            }
//
            Log.e("TestConstraint", "init x:${draggableView.x} y:${draggableView.y}")

            var initialX = 0f
            var initialY = 0f
            var lastX = initialX
            var lastY = initialY
            draggableView.setOnTouchListener { v, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        initialX = draggableView.x
                        initialY = draggableView.y
                        // 记录按下的位置
                        lastX = event.rawX
                        lastY = event.rawY
                    }

                    MotionEvent.ACTION_MOVE -> {
                        // 移动过程中更新视图的位置
                        val deltaX = event.rawX - lastX
                        val deltaY = event.rawY - lastY
                        draggableView.x += deltaX
                        draggableView.y += deltaY
                        lastX = event.rawX
                        lastY = event.rawY
                    }

                    MotionEvent.ACTION_UP -> {
                        // 松手时判断贴边
                        val screenWidth = root.width
                        val threshold = screenWidth / 2
                        val finalX = draggableView.x

                        draggableView.x = initialX
                        draggableView.y = initialY

                        val constraintSet = ConstraintSet()
                        constraintSet.clone(root)

                        if (finalX < threshold) {
                            // 贴左边
                            constraintSet.setHorizontalBias(draggableView.id, .02f)
                            constraintSet.setVerticalBias(draggableView.id, .02f)
                        } else {
                            // 贴右边
                            constraintSet.setHorizontalBias(draggableView.id, .98f)
                            constraintSet.setVerticalBias(draggableView.id, .02f)
                        }

                        Log.e("TestConstraint", "up x:${draggableView.x} y:${draggableView.y}")

                        TransitionManager.beginDelayedTransition(root)
                        constraintSet.applyTo(root)
                    }
                }
                true
            }
        }
    }
}