package com.common.composesample.ui.page

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.common.composesample.entity.items
import com.common.composesample.ui.theme.ThemeKinds
import com.common.composesample.widget.Destination
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
    navController: NavHostController,
    chooseThemeId: String,
    themeChoose: (ThemeKinds)->Unit
){
    val state = rememberPagerState(initialPage = 0)
    val scope = rememberCoroutineScope()
    Scaffold(bottomBar = {
        BottomNavigation(
            backgroundColor = MaterialTheme.colors.primary
        ) {
            items.forEachIndexed { index, bottomData ->
                BottomNavigationItem(
                    selected = state.currentPage == index,
                    onClick = { scope.launch { state.animateScrollToPage(index) } },
                    icon = {
                        Icon(painter = painterResource(id = bottomData.defaultIcon), contentDescription = bottomData.name)
                    },
                    label = { Text(text = bottomData.name) }
                )
            }
        }
    }) { it ->
        HorizontalPager(state = state,count = items.size, modifier = Modifier.padding(it)) { page->
            val content = items[state.currentPage].name
            when(page){
                0-> HomeUi(
                    onArticleClick = { navController.navigate(Destination.ArticleDetail.route) },
                    onSearchClick = { navController.navigate(Destination.SearchContent.route) },
                    onVideoClick = { navController.navigate(Destination.VideoDetail.route) },
                    chooseThemeId = chooseThemeId,
                    themeChoose = { theme-> themeChoose(theme) }
                )
                1-> ChooseCourseUi(text = content)
//                2-> ChooseCourseUi(text = content)
//                3-> ChooseCourseUi(text = content)
                2-> MineUi(
                    toPersonMainPage = { navController.navigate(Destination.PersonMainPage.route) }
                )
            }
        }
    }
}