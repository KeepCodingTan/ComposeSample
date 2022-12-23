package com.common.composesample.ui.page

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.common.composesample.ui.theme.color_backGround
import com.common.composesample.widget.CoilImage
import com.common.composesample.widget.ExploreImageContainer
import com.google.accompanist.permissions.ExperimentalPermissionsApi

/**
 * @Author: Sun
 * @CreateDate: 2022/12/16
 * @Description: java类作用描述
 */
@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterialApi::class)
@Composable
fun MineUi(
    toPersonMainPage: ()->Unit
){

    val imageUrl = mutableStateOf<Uri?>(null)
    val icons = mapOf(
        "服务" to Icons.Default.LocalMall,
        "收藏" to Icons.Default.FolderSpecial,
        "朋友圈" to Icons.Default.AccountBox,
        "卡包" to Icons.Default.Poll,
        "表情" to Icons.Default.EmojiEmotions,
        "设置" to Icons.Default.Psychology
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color_backGround)
    ) {
        Row(
            modifier = Modifier
                .background(MaterialTheme.colors.background)
                .fillMaxWidth()
                .padding(vertical = 50.dp, horizontal = 16.dp)
                .clickable {
                    toPersonMainPage()
                }
        ) {
            ExploreImageContainer(
                modifier = Modifier.size(40.dp)
            ) {
                CoilImage(imageUrl.value ?: "https://img2.baidu.com/it/u=1876128925,1748328021&fm=253&fmt=auto&app=138&f=JPEG?w=462&h=403")
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(text = "Sun", color = Color.Black)
                Text(text = "账号：Sun_666", color = Color.Black)
            }
        }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(10.dp))
        icons.forEach{
            MineItem(it.key,it.value,it.key=="设置"||it.key=="收藏")
        }
    }
}

@Composable
fun MineItem(
    title: String,
    icon: ImageVector,
    showSpacer: Boolean
){
    Column {
        if(showSpacer){
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(10.dp))
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
            .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = icon, tint = MaterialTheme.colors.primary, contentDescription = "", modifier = Modifier.size(22.dp))
            Text(text = title, color = Color.Black, modifier = Modifier
                .weight(1f)
                .padding(start = 10.dp))
            Icon(imageVector = Icons.Default.ArrowForwardIos,tint = MaterialTheme.colors.primary, contentDescription = "",modifier = Modifier.size(22.dp))
        }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(color_backGround))
    }
}