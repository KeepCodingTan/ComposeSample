package com.common.composesample

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DocumentScanner
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Scanner
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.common.composesample.ui.theme.color_999999
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

/**
 * @Author: Sun
 * @CreateDate: 2022/12/8
 * @Description: java类作用描述
 */
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeUi(
    onArticleClick: (News)->Unit,
    onSearchClick: ()->Unit
){
    Scaffold(
        topBar = {SearchBar(onSearchClick)},
    ) {
        HomeContent{onArticleClick(it)}
    }
}

@Composable
fun SearchBar(
    onSearchClick: ()->Unit
) {
    /*val (text, updateText) = remember {
        mutableStateOf("")
    }*/
    Box(modifier = Modifier.background(MaterialTheme.colors.primary)
        .padding(start = 16.dp, top = 16.dp, end = 16.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                value = "新冠感染者初期症状 | 以岭药业公告",
                onValueChange = {},
                singleLine = true,
                cursorBrush = SolidColor(MaterialTheme.colors.primary),
                readOnly = true,
                modifier = Modifier
                    .weight(1f)
                    .background(MaterialTheme.colors.primary),
                decorationBox = { innerTextField ->
                    Row(
                        modifier = Modifier.background(MaterialTheme.colors.background,RoundedCornerShape(8.dp)).padding(6.dp).clickable { onSearchClick() },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            modifier = Modifier.size(24.dp),
                            contentDescription = ""
                        )
                        innerTextField()
                    }
                }
            )
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                imageVector = Icons.Default.Notifications,
                tint = MaterialTheme.colors.background,
                modifier = Modifier.size(30.dp),
                contentDescription = ""
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeContent(
    modifier: Modifier = Modifier.fillMaxSize(),
    onArticleClick: (News)->Unit
){
    val pageTitles = listOf("关注","推荐","探索","世界杯","发现","热榜","抗疫","每日必看")
    val pagerState = rememberPagerState(initialPage = 0)
    val scope = rememberCoroutineScope()
    Column (
        modifier = modifier
    ){
        Box(
            modifier = Modifier
                .background(MaterialTheme.colors.primary)
                .padding(vertical = 4.dp)
        ) {
            ScrollableTabRow(
                selectedTabIndex = pagerState.currentPage,
                edgePadding = 16.dp,
                divider = {},
                indicator = {
                    TabRowDefaults.Indicator(Modifier.pagerTabIndicatorOffset(pagerState,it))
                }
            ) {
                pageTitles.forEachIndexed{index, s ->
                    Tab(
                        selected = index == pagerState.currentPage,
                        onClick = { scope.launch { pagerState.animateScrollToPage(index) } }
                    ) {
                        Text(text = s,modifier = Modifier.padding(vertical = 8.dp), fontStyle = FontStyle.Italic, fontSize = 16.sp)
                    }
                }
            }
        }
        HorizontalPager(count = pageTitles.size, modifier = Modifier.weight(1f),state = pagerState) { page->
            val content = pageTitles[pagerState.currentPage]
            when(page){
                0 -> FollowUi()
                1 -> RecommendUi{onArticleClick(it)}
                2 -> SearchUi(text = content)
                else -> OtherUi(text = content)
            }
        }
    }
}