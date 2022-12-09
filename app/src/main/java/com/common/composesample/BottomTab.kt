package com.common.composesample

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.common.composesample.ui.theme.color_1973F4
import com.common.composesample.ui.theme.color_999999
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.launch

/**
 * @Author: Sun
 * @CreateDate: 2022/12/8
 * @Description: java类作用描述
 */

@OptIn(ExperimentalPagerApi::class)
@Composable
fun BottomTab(
    items: List<BottomData>,
    modifier: Modifier = Modifier.background(MaterialTheme.colors.background),
    state: PagerState
){
    val scope = rememberCoroutineScope()
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        items.forEachIndexed{ index, bottomData ->
            TabItem(Modifier.weight(1f),state.currentPage == index,bottomData){
                scope.launch { state.animateScrollToPage(index) }
            }
        }
    }
}

@Composable
fun TabItem(
    modifier: Modifier = Modifier,
    isSelect: Boolean,
    data: BottomData,
    onTabChoose: ()->Unit
){
    Column(
        modifier = modifier.padding(vertical = 8.dp).clickable { onTabChoose() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val tint = if(isSelect)data.selectColor else data.defaultColor
        val icon = if(isSelect)data.selectIcon else data.defaultIcon
        Icon(painterResource(id = icon), modifier = Modifier.size(24.dp),tint = tint, contentDescription = data.name)
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = data.name, color = tint, fontSize = 14.sp)
    }
}

data class BottomData(
    val name: String,
    val selectIcon: Int,
    val defaultIcon: Int,
    val selectColor: Color = color_1973F4,
    val defaultColor: Color = color_999999
)
