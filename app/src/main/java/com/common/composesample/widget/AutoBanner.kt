package com.common.composesample.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.common.composesample.widget.CustomPagerIndicator
import com.common.composesample.entity.banners
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import java.util.*

/**
 * @Author: Sun
 * @CreateDate: 2022/12/13
 * @Description: java类作用描述
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
fun AutoBanner(){
    val virtual = Int.MAX_VALUE
    val initialPage = virtual/2
    val pageCount = banners.size
    val pagerState = rememberPagerState(initialPage = initialPage)
    val scope = rememberCoroutineScope()

    DisposableEffect(Unit){
        val timer = Timer()
        timer.schedule(object : TimerTask(){
            override fun run() {
                scope.launch { pagerState.animateScrollToPage(pagerState.currentPage+1) }
            }
        },3000,3000)
        onDispose {
            timer.cancel()
        }
    }

    Box{
        HorizontalPager(
            count = virtual,
            state = pagerState,
            modifier = Modifier
                .padding(8.dp)
                .clip(RoundedCornerShape(8.dp))
        ) {page ->
            val realPage = page.calculatePage(pageCount)
            AsyncImage(
                model = banners[realPage],
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(7 / 3f),
                contentScale = ContentScale.Crop
            )
        }
        CustomPagerIndicator(
            pageCount = pageCount,
            pagerState = pagerState,
            modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 20.dp)
        )
    }
}

fun Int.calculatePage(count: Int):Int = when(count){
    0 -> 0
    else -> this - this.floorDiv(count)*count
}