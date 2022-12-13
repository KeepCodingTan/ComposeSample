package com.common.composesample

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

/**
 * @Author: Sun
 * @CreateDate: 2022/12/13
 * @Description: java类作用描述
 */
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CustomNavHost(){
    val navController = rememberAnimatedNavController()
    AnimatedNavHost(
        navController = navController,
        startDestination = Destination.MainUi.route
    ){
        composable(
            route = Destination.MainUi.route,
        ){
            MainUi(navController = navController)
        }

        composable(
            route = Destination.ArticleDetail.route,
            enterTransition = {
                when(initialState.destination.route){
                    Destination.MainUi.route -> slideIntoContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
                    else -> null
                }
            },
            exitTransition = {
                when(initialState.destination.route){
                    Destination.ArticleDetail.route -> slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(700))
                    else -> null
                }
            },
        ){
            ArticleDetailUi(navController = navController)
        }

        composable(
            route = Destination.SearchContent.route,
            enterTransition = {
                when(initialState.destination.route){
                    Destination.MainUi.route -> slideIntoContainer(AnimatedContentScope.SlideDirection.Down, animationSpec = tween(700))
                    else -> null
                }
            },
            exitTransition = {
                when(initialState.destination.route){
                    Destination.SearchContent.route -> slideOutOfContainer(AnimatedContentScope.SlideDirection.Up, animationSpec = tween(700))
                    else -> null
                }
            },
        ){
            SearchContent(navController = navController)
        }
    }
}

sealed class Destination(val route: String){
    object MainUi: Destination("MainUi")
    object ArticleDetail: Destination("ArticleDetail")
    object SearchContent: Destination("SearchContent")
}