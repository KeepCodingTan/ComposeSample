package com.common.composesample.ui.page

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * @Author: Sun
 * @CreateDate: 2022/12/16
 * @Description: java类作用描述
 */
@Composable
fun MineUi(){
    DisposableEffect(Unit){
        onDispose {
            Log.d("sun","MineUi_onDispose")
        }
    }
    Column (modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
        /*val data = remember {
            mutableStateOf(6)
        }*/
        val dataSaver = listSaver<Int,Int>(
            save = { listOf(it) },
            restore = { it[0] }
        )
        val data = rememberSaveable(stateSaver = dataSaver) {
            mutableStateOf(6)
        }
        Text(text = "当前数据${data.value}")
        TextButton(onClick = { data.value = 8 }) {
            Text(text = "修改值为8")
        }
    }
}