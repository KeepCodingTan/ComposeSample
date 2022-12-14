package com.common.composesample.ui.page

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.common.composesample.widget.ComposeWebview
import com.common.composesample.widget.CustomTopbar

/**
 * @Author: Sun
 * @CreateDate: 2022/12/13
 * @Description: java类作用描述
 */
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ArticleDetailUi(
    navController: NavHostController
){
    Scaffold(
        topBar = { CustomTopbar("文章详情"){ navController.popBackStack() } }
    ) {
        ComposeWebview(url = "https://www.toutiao.com/article/7176283550818566693/?log_from=4dcaf1131c721_1670919208904")
    }
}

