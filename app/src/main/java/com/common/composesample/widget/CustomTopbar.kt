package com.common.composesample.widget

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * @Author: Sun
 * @CreateDate: 2022/12/13
 * @Description: java类作用描述
 */
@Composable
fun CustomTopbar(
    title: String,
    actions: ()->Unit = {},
    onBack: ()->Unit
){
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = { Text(text = title, modifier = Modifier.fillMaxWidth().wrapContentSize(Alignment.Center)) },
        navigationIcon = { IconButton(onClick = {onBack()}) {
            Icon(imageVector = Icons.Default.ArrowBackIos, contentDescription = "")
        }},
        actions = { IconButton(onClick = {actions()}) {
            Icon(imageVector = Icons.Default.Share, contentDescription = "")
        } }
    )
}