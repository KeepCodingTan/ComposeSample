package com.common.composesample

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout

/**
 * @Author: Sun
 * @CreateDate: 2022/12/3
 * @Description: java类作用描述
 */

@Composable
fun FlowLayout(
    modifier: Modifier = Modifier,
    content : @Composable ()->Unit
){
    Layout(
        modifier = modifier,
        content = content
    ){ measureables,constraints ->

        val placeables = measureables.mapIndexed { index, measurable ->
            val placeable = measurable.measure(constraints)

        }
        layout(0,0){

        }
    }
}