package com.common.composesample.ui.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.common.composesample.ui.theme.color_backGround

/**
 * @Author: Sun
 * @CreateDate: 2022/12/13
 * @Description: java类作用描述
 */
@Composable
fun SearchUi(
    modifier: Modifier = Modifier.fillMaxSize().background(color_backGround),
    text: String
){
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text(text = text, color = Color.Black, style = MaterialTheme.typography.h1)
            Text(text = text, color = Color.Black, style = MaterialTheme.typography.h2)
            Text(text = text, color = Color.Black, style = MaterialTheme.typography.h3)
            Text(text = text, color = Color.Black, style = MaterialTheme.typography.h4)
            Text(text = text, color = Color.Black, style = MaterialTheme.typography.h5)
            Text(text = text, color = Color.Black, style = MaterialTheme.typography.h6)
            Text(text = text, color = Color.Black, style = MaterialTheme.typography.subtitle1)
            Text(text = text, color = Color.Black, style = MaterialTheme.typography.subtitle2)
            Text(text = text, color = Color.Black, style = MaterialTheme.typography.body1)
            Text(text = text, color = Color.Black, style = MaterialTheme.typography.body2)
            Text(text = text, color = Color.Black, style = MaterialTheme.typography.button)
            Text(text = text, color = Color.Black, style = MaterialTheme.typography.caption)
            Text(text = text, color = Color.Black, style = MaterialTheme.typography.overline)
        }
    }
}