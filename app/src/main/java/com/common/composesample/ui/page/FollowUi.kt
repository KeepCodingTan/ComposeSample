package com.common.composesample.ui.page

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.common.composesample.viewmodel.VideoModel
import com.common.composesample.entity.VideoItem
import com.common.composesample.widget.CoilImage
import com.common.composesample.widget.ExploreImageContainer
import com.common.composesample.entity.images
import com.common.composesample.ui.theme.color_backGround
import com.common.composesample.ui.theme.color_red
import com.common.composesample.widget.LoadingMore

/**
 * @Author: Sun
 * @CreateDate: 2022/12/9
 * @Description: java类作用描述
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FollowUi(
    modifier: Modifier = Modifier
        .fillMaxSize()
        .background(color_backGround)
        .padding(10.dp),
    videoModel: VideoModel = viewModel(),
    onVideoClick: ()->Unit = {}
){
    val pagingItems = videoModel.videoList.collectAsLazyPagingItems()
    val isRefreshing = pagingItems.loadState.refresh == LoadState.Loading
    val refreshState = rememberPullRefreshState(refreshing = isRefreshing, onRefresh = { pagingItems.refresh() })
    Surface(
        modifier = modifier.pullRefresh(refreshState)
    ) {
        LazyColumn(
            modifier = modifier
        ){
            items(pagingItems){
                it?.let {
                    ItemFollow(item = it){
                        onVideoClick()
                    }
                }
            }
            when(pagingItems.loadState.append){
                is LoadState.Loading -> {
                    item {
                        LoadingMore()
                    }
                }
                else -> {

                }
            }
        }
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopCenter
        ) {
            PullRefreshIndicator(refreshing = isRefreshing, state = refreshState)
        }
    }
}

@Composable
fun ItemFollow(
    item: VideoItem,
    modifier: Modifier = Modifier
        .clickable { onVideoClick() }
        .background(MaterialTheme.colors.background, RoundedCornerShape(8.dp))
        .padding(6.dp),
    onVideoClick:()->Unit,
){
    Surface(
      modifier = modifier
    ) {
        Column(
            modifier = modifier
        ) {
            Text(text = item.title, style = MaterialTheme.typography.body1,color = Color.Black)
            Spacer(modifier = Modifier.height(8.dp))
            ExploreImageContainer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                CoilImage(images[item.image % 24])
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "发布时间：${item.time}", style = MaterialTheme.typography.body2, color = Color.Black)
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
}