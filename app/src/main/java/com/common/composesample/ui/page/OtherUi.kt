package com.common.composesample.ui.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.common.composesample.ui.theme.color_backGround

/**
 * @Author: Sun
 * @CreateDate: 2022/12/9
 * @Description: java类作用描述
 */
@Composable
fun OtherUi(
    modifier: Modifier = Modifier.fillMaxSize().background(color_backGround),
    text: String
){
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, color = Color.Black, style = MaterialTheme.typography.h3)
    }

    /*Column (modifier = Modifier.fillMaxSize().background(color_backGround), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
        *//*val data = remember {
            mutableStateOf(6)
        }*//*
        val dataSaver = listSaver<Int,Int>(
            save = { listOf(it) },
            restore = { it[0] }
        )
        val data = rememberSaveable(stateSaver = dataSaver) {
            mutableStateOf(6)
        }
        Text(text = "当前数据${data.value}", color = Color.Black)
        TextButton(onClick = { data.value = 8 }) {
            Text(text = "修改值为8",color = Color.Black)
        }
    }*/
}