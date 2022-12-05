package com.common.composesample

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.max

/**
 * @Author: Sun
 * @CreateDate: 2022/12/2
 * @Description: java类作用描述
 */

@Composable
fun StaggeredGrid(
    modifier: Modifier = Modifier,
    rows: Int = 3,
    content: @Composable () -> Unit
){
    Layout(
        modifier = modifier,
        content = content
    ){ measureables,constraints ->
        val rowWidths = IntArray(rows){0}
        val rowHeights = IntArray(rows){0}
        val placeables = measureables.mapIndexed { index, measurable ->
            val placeable = measurable.measure(constraints)
            val row = index % rows
            rowWidths[row] += placeable.width
            rowHeights[row] = max(rowHeights[row],placeable.height)
            placeable
        }
        val width = rowWidths.maxOrNull() ?: constraints.minWidth
        val height = rowHeights.sumOf { it }
        val rowY = IntArray(rows){0}
        for(i in 1 until rows){
            rowY[i] = rowY[i-1]+rowHeights[i-1]
        }
        layout(width,height){
            val rowX = IntArray(rows){0}
            placeables.forEachIndexed { index, placeable ->
                val row = index % rows
                placeable.placeRelative(
                    x = rowX[row],
                    y = rowY[row]
                )
                rowX[row] += placeable.width
            }
        }
    }
}

@Composable
fun Chip(
    modifier: Modifier = Modifier,
    text: String
){
    Card(
        modifier = modifier,
        border = BorderStroke(width = Dp.Hairline, color = Color.Black),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(start = 8.dp, top = 4.dp, end = 8.dp, bottom = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(16.dp, 16.dp)
                    .background(color = MaterialTheme.colors.secondary)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = text)
        }
    }
}

@Composable
fun StaggeredGridContent(
    modifier: Modifier = Modifier
){
    Row(
        modifier = Modifier
            .background(color = Color.LightGray)
            .padding(16.dp)
            .horizontalScroll(rememberScrollState()),
        content = {
            StaggeredGrid(
                modifier = Modifier
            ) {
                for(topic in topics){
                    Chip(
                        modifier =Modifier.padding(8.dp),
                        text = topic
                    )
                }
            }
        }
    )
}

val topics = listOf(
    "体育",
    "娱乐",
    "新闻",
    "健身",
    "世界杯",
    "国家大事",
    "时事要闻，你懂得",
    "新冠疫情最新消息",
    "热榜",
    "推荐",
    "值得看",
    "牛批Plus"
)