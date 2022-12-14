package com.common.composesample.ui.page

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

/**
 * @Author: Sun
 * @CreateDate: 2022/12/9
 * @Description: java类作用描述
 */
@Composable
fun OtherUi(
    modifier: Modifier = Modifier.fillMaxSize(),
    text: String
){
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, color = Color.Black, style = MaterialTheme.typography.h3)
    }
}