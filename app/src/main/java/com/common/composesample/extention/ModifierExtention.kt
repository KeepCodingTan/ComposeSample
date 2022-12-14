package com.common.composesample.extention

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.calculateTargetValue
import androidx.compose.animation.splineBasedDecay
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.horizontalDrag
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.input.pointer.consumePositionChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.compose.ui.unit.IntOffset
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

/**
 * @Author: Sun
 * @CreateDate: 2022/12/6
 * @Description: java类作用描述
 */

fun Modifier.swipeToDismiss(
    onSwipeDismiss: ()->Unit,
    onSingleTap: ()->Unit
): Modifier{
    return composed {
        val offsetX = remember {
            Animatable(0f)
        }
        pointerInput(Unit){
            val decay = splineBasedDecay<Float>(this)
            coroutineScope {
                while (true){
                    val pointerId = awaitPointerEventScope { awaitFirstDown().id }

                    val velocityTracker = VelocityTracker()

                    awaitPointerEventScope {
                        horizontalDrag(pointerId){ change ->
                            val horizontalOffsetX = offsetX.value+change.positionChange().x
                            launch { offsetX.snapTo(horizontalOffsetX) }
                            velocityTracker.addPosition(change.uptimeMillis,change.position)
                            change.consumePositionChange()
                        }
                    }

                    offsetX.updateBounds(
                        lowerBound = -size.width.toFloat(),
                        upperBound = size.width.toFloat()
                    )

                    val velocityTrackerX = velocityTracker.calculateVelocity().x
                    val targetOffsetX = decay.calculateTargetValue(offsetX.value,velocityTrackerX)
                    launch {
                        if(targetOffsetX.absoluteValue < size.width){
                            offsetX.animateTo(targetValue = 0f, initialVelocity = velocityTrackerX)
                        }else{
                            offsetX.animateDecay(initialVelocity = velocityTrackerX, animationSpec = decay)
                            onSwipeDismiss()
                        }
                    }
                }
            }
        }.offset{
            IntOffset(offsetX.value.roundToInt(),0)
        }
    }
}