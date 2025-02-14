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

            val initialX = draggableView.x
            var lastX = initialX
            draggableView.setOnTouchListener { v, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        // 记录按下的位置
                        lastX = event.rawX
                    }

                    MotionEvent.ACTION_MOVE -> {
                        // 移动过程中更新视图的位置
                        val deltaX = event.rawX - lastX
                        draggableView.x += deltaX
                        lastX = event.rawX
                    }

                    MotionEvent.ACTION_UP -> {
                        // 松手时判断贴边
                        val screenWidth = root.width
                        val threshold = screenWidth / 2
                        val finalX = draggableView.x

                        Log.e(
                            "TestConstraint",
                            "screenWidth:$screenWidth threshold:$threshold finalX:$finalX"
                        )

                        val constraintSet = ConstraintSet()
                        constraintSet.clone(root)

                        if (finalX < threshold) {

                            // 贴左边
                            constraintSet.connect(
                                draggableView.id,
                                ConstraintSet.START,
                                ConstraintSet.PARENT_ID,
                                ConstraintSet.START,
                                50
                            )
                            constraintSet.connect(
                                draggableView.id,
                                ConstraintSet.TOP,
                                ConstraintSet.PARENT_ID,
                                ConstraintSet.TOP,
                                50
                            )
                        } else {
                            // 贴右边
                            constraintSet.connect(
                                draggableView.id,
                                ConstraintSet.END,
                                ConstraintSet.PARENT_ID,
                                ConstraintSet.END,
                                0
                            )
                            constraintSet.connect(
                                draggableView.id,
                                ConstraintSet.TOP,
                                ConstraintSet.PARENT_ID,
                                ConstraintSet.TOP,
                                0
                            )
                        }

                        TransitionManager.beginDelayedTransition(root)
                        constraintSet.applyTo(root)
                    }
                }
                true
            }
        }
    }
}