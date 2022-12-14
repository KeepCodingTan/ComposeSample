package com.common.composesample.ui.page

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.common.composesample.widget.CoilImage
import com.common.composesample.widget.ExploreImageContainer
import com.common.composesample.entity.images
import com.common.composesample.utils.timeFormat
import com.common.composesample.ui.theme.color_backGround
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
    onVideoClick: ()->Unit = {}
){
    val scope = rememberCoroutineScope()
    val isRefresh = rememberSwipeRefreshState(isRefreshing = false)
    SwipeRefresh(
        state = isRefresh,
        onRefresh = {
            scope.launch {
                isRefresh.isRefreshing = true
                delay(1000)
                isRefresh.isRefreshing = false
            }
        }
    ) {
        LazyColumn(
            modifier = modifier
        ){
            items(images){
                ItemFollow(url = it){
                    onVideoClick()
                }
            }
        }
    }
}

@Composable
fun ItemFollow(
    url: String,
    onVideoClick:()->Unit
){
    Surface(
      modifier = Modifier
          .background(MaterialTheme.colors.background, RoundedCornerShape(8.dp))
          .clickable { onVideoClick() }
          .padding(10.dp)
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