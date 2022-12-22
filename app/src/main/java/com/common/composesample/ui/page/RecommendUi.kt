package com.common.composesample.ui.page

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.common.composesample.entity.VideoItem
import com.common.composesample.ui.theme.color_backGround
import com.common.composesample.viewmodel.NewViewModel
import com.common.composesample.widget.AutoBanner
import com.common.composesample.widget.LoadingMore
import com.google.accompanist.pager.ExperimentalPagerApi

/**
 * @Author: Sun
 * @CreateDate: 2022/12/9
 * @Description: java类作用描述
 */
@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class)
@Composable
fun RecommendUi(
    modifier: Modifier = Modifier.fillMaxSize(),
    newsModel: NewViewModel = viewModel(),
    onArticleClick: (VideoItem)->Unit
){
    val pagingItems = newsModel.newsList.collectAsLazyPagingItems()
    val isRefreshing = pagingItems.loadState.refresh == LoadState.Loading
    val refreshState = rememberPullRefreshState(refreshing = isRefreshing, onRefresh = { pagingItems.refresh() })
    Surface(
        modifier = modifier.pullRefresh(refreshState)
    ) {
        LazyColumn(modifier = modifier){
            item {
                AutoBanner()
            }
            items(pagingItems){
                it?.let {
                    ItemNew(item = it){
                        onArticleClick(it)
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
fun ItemNew(
    item: VideoItem,
    onArticleClick: (VideoItem)->Unit
) {
    Column(modifier = Modifier
        .clickable { onArticleClick(item) }
        .background(MaterialTheme.colors.background, RoundedCornerShape(8.dp))
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 10.dp)) {
        Text(text = item.title, style = MaterialTheme.typography.body1, maxLines = 2, overflow = TextOverflow.Ellipsis,color = Color.Black)
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = item.source, style = MaterialTheme.typography.body2, fontSize = 12.sp,color = Color.Black)
            Text(text = item.time, style = MaterialTheme.typography.body2, fontSize = 12.sp,color = Color.Black)
        }
    }
}