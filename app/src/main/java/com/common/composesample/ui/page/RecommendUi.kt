package com.common.composesample.ui.page

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.common.composesample.widget.AutoBanner
import com.common.composesample.entity.News
import com.common.composesample.entity.news
import com.google.accompanist.pager.ExperimentalPagerApi

/**
 * @Author: Sun
 * @CreateDate: 2022/12/9
 * @Description: java类作用描述
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
fun RecommendUi(
    modifier: Modifier = Modifier.fillMaxSize(),
    onArticleClick: (News)->Unit
){
    LazyColumn(modifier = modifier){
        item {
             AutoBanner()
        }
        items(news){
            ItemNew(item = it){
                onArticleClick(it)
            }
        }
    }
}

@Composable
fun ItemNew(
    item: News,
    onArticleClick: (News)->Unit
) {
    Column(modifier = Modifier
        .clickable { onArticleClick(item) }
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 10.dp)) {
        Text(text = item.title, style = MaterialTheme.typography.body1, maxLines = 2, overflow = TextOverflow.Ellipsis)
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = item.source, style = MaterialTheme.typography.body2, fontSize = 12.sp)
            Text(text = item.time, style = MaterialTheme.typography.body2, fontSize = 12.sp)
        }
    }
}