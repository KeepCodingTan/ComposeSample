package com.common.composesample.widget

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import xyz.doikki.videocontroller.StandardVideoController
import xyz.doikki.videocontroller.component.*
import xyz.doikki.videoplayer.player.VideoView

/**
 * @Author: Sun
 * @CreateDate: 2022/12/14
 * @Description: java类作用描述
 */
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ComposePlayer(
    videoState: VideoConfigState = rememberVideoConfigState(),
    path: String = "http://vfx.mtime.cn/Video/2019/03/14/mp4/190314223540373995.mp4"
){
    var playerView: VideoView? = null
    Box(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()) {
        DisposableEffect(Unit){
            onDispose {
                Log.d("sun","onDispose")
                playerView?.let {
                    it.release()
                }
            }
        }
        AndroidView(
            factory = { context ->
                val controller = StandardVideoController(context).apply {
                    setEnableOrientation(true) //根据屏幕方向自动进入/退出全屏
                    addControlComponent(PrepareView(context)) //准备播放界面
                    addControlComponent(CompleteView(context)) //自动完成播放界面
                    addControlComponent(ErrorView(context)) //错误界面
                    addControlComponent(TitleView(context)) //标题栏
                    addControlComponent(VodControlView(context)) //点播控制条
                    addControlComponent(GestureView(context)) //滑动控制视图
                }
                playerView = VideoView(context).apply {
                    setVideoController(controller)
                    setUrl(path)
                    start()
                }
                playerView!!
            },
            modifier = Modifier.wrapContentHeight(),
            update = {
                it.speed = videoState.speed
                when(videoState.ratio){
                    0-> it.setScreenScaleType(VideoView.SCREEN_SCALE_DEFAULT)
                    1-> it.setScreenScaleType(VideoView.SCREEN_SCALE_16_9)
                    2-> it.setScreenScaleType(VideoView.SCREEN_SCALE_4_3)
                    3-> it.setScreenScaleType(VideoView.SCREEN_SCALE_ORIGINAL)
                    4-> it.setScreenScaleType(VideoView.SCREEN_SCALE_MATCH_PARENT)
                    5-> it.setScreenScaleType(VideoView.SCREEN_SCALE_CENTER_CROP)
                }
            }
        )
    }
}

@Composable
fun rememberVideoConfigState(
    ratio: Int = 0,
    speed: Float = 1f,
):VideoConfigState{
    return remember(ratio,speed) {
        VideoConfigState(
            ratio = ratio,
            speed = speed
        )
    }
}

@Stable
class VideoConfigState(
    var ratio: Int,
    var speed: Float,
)