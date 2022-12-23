package com.common.composesample.widget

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * @Author: Sun
 * @CreateDate: 2022/12/21
 * @Description: java类作用描述
 */
@Composable
fun LoadingMore(){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
       CircularProgressIndicator(
           modifier = Modifier.size(24.dp),
           color = MaterialTheme.colors.primary,
           strokeWidth = 3.dp
       )
       Spacer(modifier = Modifier.width(30.dp))
       Text(text = "正在加载...", fontSize = 16.sp, color = Color.Black)
    }
}