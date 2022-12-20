package com.common.composesample.ui.page

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.common.composesample.widget.ComposePlayer
import com.common.composesample.widget.CustomTopbar
import com.common.composesample.widget.VideoConfigState
import com.common.composesample.widget.rememberVideoConfigState
import kotlinx.coroutines.launch

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
    var bitmapState = remember{
        mutableStateOf<Bitmap>(Bitmap.createBitmap(10,10,Bitmap.Config.ALPHA_8))
    }
    var isMuteState = remember {
        mutableStateOf(false)
    }
    var isFullScreen = remember {
        mutableStateOf(false)
    }
    val videoState = rememberVideoConfigState(
        path = "http://vfx.mtime.cn/Video/2019/03/14/mp4/190314223540373995.mp4",
        onShot = {
            bitmapState.value = it
        },
        isMute = {
            isMuteState.value = it
        }
    )
    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        topBar = {
            if (!isFullScreen.value) {
                CustomTopbar("视频详情", {
                    scope.launch {
                        if (scaffoldState.bottomSheetState.isCollapsed) {
                            scaffoldState.bottomSheetState.expand()
                        } else {
                            scaffoldState.bottomSheetState.collapse()
                        }
                    }
                }) { navController.popBackStack() }
            }
        },
        sheetContent = { VideoConfigSheet(videoState, isMuteState) },
        sheetPeekHeight = 0.dp
    ) {
        Column {
            ComposePlayer(videoState = videoState) {
                isFullScreen.value = it
            }
            Image(bitmap = bitmapState.value.asImageBitmap(), contentDescription = "")
        }
    }
}

@Composable
fun VideoConfigSheet(
    videoState: VideoConfigState,
    isMuteState: MutableState<Boolean>
){
    val buttonColors = mutableListOf(Color.LightGray,Color.LightGray,Color.LightGray,Color.LightGray,Color.LightGray,Color.LightGray)
    val buttonColors1 = mutableListOf(Color.LightGray,Color.LightGray,Color.LightGray,Color.LightGray,Color.LightGray)
    repeat(6){
        buttonColors[it] = if(videoState.curRatio == it) Color.Cyan else Color.LightGray
    }
    repeat(5){
        buttonColors1[it] = if(videoState.curSpeed == it) Color.Cyan else Color.LightGray
    }
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(16.dp)) {
        Text(text = "画面比例", modifier = Modifier.padding(bottom = 10.dp))
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween) {
            TextButton(onClick = { videoState.setScreenScaleType(0) }, colors = ButtonDefaults.buttonColors(buttonColors[0])) {
                Text(text = "默认")
            }
            TextButton(onClick = { videoState.setScreenScaleType(1) }, colors = ButtonDefaults.buttonColors(buttonColors[1])) {
                Text(text = "16:9")
            }
            TextButton(onClick = { videoState.setScreenScaleType(2) }, colors = ButtonDefaults.buttonColors(buttonColors[2])) {
                Text(text = "4:3")
            }
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween) {
            TextButton(onClick = { videoState.setScreenScaleType(3) }, colors = ButtonDefaults.buttonColors(buttonColors[3])) {
                Text(text = "原始")
            }
            TextButton(onClick = { videoState.setScreenScaleType(4) }, colors = ButtonDefaults.buttonColors(buttonColors[4])) {
                Text(text = "填充")
            }
            TextButton(onClick = { videoState.setScreenScaleType(5) }, colors = ButtonDefaults.buttonColors(buttonColors[5])) {
                Text(text = "居中裁剪")
            }
        }
        Text(text = "播放速度", modifier = Modifier.padding(bottom = 10.dp))
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween) {
            TextButton(onClick = { videoState.setSpeed(0.5f) },colors = ButtonDefaults.buttonColors(buttonColors1[0])) {
                Text(text = "0.5")
            }
            TextButton(onClick = { videoState.setSpeed(0.75f) },colors = ButtonDefaults.buttonColors(buttonColors1[1])) {
                Text(text = "0.75")
            }
            TextButton(onClick = { videoState.setSpeed(1.0f) },colors = ButtonDefaults.buttonColors(buttonColors1[2])) {
                Text(text = "1.0")
            }
            TextButton(onClick = { videoState.setSpeed(1.5f) },colors = ButtonDefaults.buttonColors(buttonColors1[3])) {
                Text(text = "1.5")
            }
            TextButton(onClick = { videoState.setSpeed(2.0f) },colors = ButtonDefaults.buttonColors(buttonColors1[4])) {
                Text(text = "2.0")
            }
        }
        Text(text = "其他", modifier = Modifier.padding(bottom = 10.dp))
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween) {
            TextButton(onClick = { videoState.mirrorRotate() },colors = ButtonDefaults.buttonColors(Color.LightGray)) {
                Text(text = "镜像旋转")
            }
            TextButton(onClick = { videoState.onShotScreen() },colors = ButtonDefaults.buttonColors(Color.LightGray)) {
                Text(text = "截图")
            }
            TextButton(onClick = { videoState.changeMute() },colors = ButtonDefaults.buttonColors(if(isMuteState.value)Color.Cyan else Color.LightGray)) {
                Text(text = "静音")
            }
        }
    }
}