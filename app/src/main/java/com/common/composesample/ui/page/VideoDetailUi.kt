package com.common.composesample.ui.page

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.common.composesample.widget.ComposePlayer
import com.common.composesample.widget.CustomTopbar
import com.common.composesample.widget.VideoConfigState
import com.common.composesample.widget.rememberVideoConfigState
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

/**
 * @Author: Sun
 * @CreateDate: 2022/12/14
 * @Description: java类作用描述
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun VideoDetailUi(
    navController: NavHostController
){
    val scaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()
    val videoState = rememberVideoConfigState()
    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        topBar = { CustomTopbar("视频详情",{
            scope.launch {
                if(scaffoldState.bottomSheetState.isCollapsed){
                    scaffoldState.bottomSheetState.expand()
                }else{
                    scaffoldState.bottomSheetState.collapse()
                }
            }
        }){ navController.popBackStack() } },
        sheetContent = { VideoConfigSheet(videoState) },
        sheetPeekHeight = 0.dp
    ) {
        ComposePlayer(videoState = videoState)
    }
}

@Composable
fun VideoConfigSheet(
    videoState: VideoConfigState
){
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(16.dp)) {
        Text(text = "画面比例", modifier = Modifier.padding(bottom = 10.dp))
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween) {
            TextButton(onClick = { videoState.ratio = 0 }) {
                Text(text = "默认")
            }
            TextButton(onClick = { videoState.ratio = 1 }) {
                Text(text = "16:9")
            }
            TextButton(onClick = { videoState.ratio =2 }) {
                Text(text = "4:3")
            }
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween) {
            TextButton(onClick = { videoState.ratio = 3 }) {
                Text(text = "原始")
            }
            TextButton(onClick = { videoState.ratio = 4 }) {
                Text(text = "填充")
            }
            TextButton(onClick = { videoState.ratio = 5 }) {
                Text(text = "居中裁剪")
            }
        }
        Text(text = "播放速度", modifier = Modifier.padding(bottom = 10.dp))
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween) {
            TextButton(onClick = { videoState.speed = 0.5f }) {
                Text(text = "0.5")
            }
            TextButton(onClick = { videoState.speed = 0.75f }) {
                Text(text = "0.75")
            }
            TextButton(onClick = { videoState.speed = 1.0f }) {
                Text(text = "1.0")
            }
            TextButton(onClick = { videoState.speed = 1.5f }) {
                Text(text = "1.5")
            }
            TextButton(onClick = { videoState.speed = 2.0f }) {
                Text(text = "2.0")
            }
        }
        Text(text = "其他", modifier = Modifier.padding(bottom = 10.dp))
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween) {
            TextButton(onClick = { /*TODO*/ }) {
                Text(text = "镜像旋转")
            }
            TextButton(onClick = { /*TODO*/ }) {
                Text(text = "截图")
            }
            TextButton(onClick = { /*TODO*/ }) {
                Text(text = "静音")
            }
        }
    }
}