package com.common.composesample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.common.composesample.ui.theme.color_backGround

/**
 * @Author: Sun
 * @CreateDate: 2022/12/9
 * @Description: java类作用描述
 */
@Composable
fun FollowUi(
    modifier: Modifier = Modifier
        .fillMaxSize()
        .background(color_backGround)
        .padding(16.dp),
){
    LazyColumn(
        modifier = modifier
    ){
        items(images){
            ItemFollow(url = it)
        }
    }
}

@Composable
fun ItemFollow(
    url: String
){
    Surface(
      modifier = Modifier.background(MaterialTheme.colors.background, RoundedCornerShape(8.dp)).padding(10.dp)
    ) {
        Column {
            Text(text = "图片地址：${url}", style = MaterialTheme.typography.body1)
            Spacer(modifier = Modifier.height(8.dp))
            ExploreImageContainer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ){
                CoilImage(url)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "发布时间：${timeFormat(System.currentTimeMillis())}", style = MaterialTheme.typography.body2)
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
}