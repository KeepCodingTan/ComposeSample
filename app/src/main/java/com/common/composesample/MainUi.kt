package com.common.composesample

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.common.composesample.ui.theme.color_1973F4
import com.common.composesample.ui.theme.color_999999
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

/**
 * @Author: Sun
 * @CreateDate: 2022/12/12
 * @Description: java类作用描述
 */
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainUi(
    navController: NavHostController
){
    val state = rememberPagerState(initialPage = 0)
    val scope = rememberCoroutineScope()
    Scaffold(bottomBar = {
        BottomNavigation(
            backgroundColor = MaterialTheme.colors.background
        ) {
            items.forEachIndexed { index, bottomData ->
                BottomNavigationItem(
                    selected = state.currentPage == index,
                    onClick = { scope.launch { state.animateScrollToPage(index) } },
                    icon = {
                        Icon(painter = painterResource(id = bottomData.defaultIcon), contentDescription = bottomData.name)
                    },
                    label = { Text(text = bottomData.name) },
                    selectedContentColor = color_1973F4,
                    unselectedContentColor = color_999999
                )
            }
        }
    }) {
        HorizontalPager(state = state,count = items.size, modifier = Modifier.padding(it)) { page->
            val content = items[state.currentPage].name
            when(page){
                0-> HomeUi(onArticleClick = {navController.navigate(Destination.ArticleDetail.route)}, onSearchClick = { navController.navigate(Destination.SearchContent.route) })
                1-> ChooseCourseUi(text = content)
                2-> ChooseCourseUi(text = content)
                3-> ChooseCourseUi(text = content)
                4-> ChooseCourseUi(text = content)
            }
        }
    }
}