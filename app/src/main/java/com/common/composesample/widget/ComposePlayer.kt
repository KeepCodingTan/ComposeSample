package com.common.composesample.widget

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
    videoState: VideoConfigState
){
    var playerView by remember {
        mutableStateOf<VideoView?>(null)
    }
    Box(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
    ) {
        LaunchedEffect(videoState){
            with(videoState){
                playerView?.handleEvents()
            }
        }
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
                VideoView(context).apply {
                    setVideoController(controller)
                }.also {
                    playerView = it
                }
                playerView!!
            },
            modifier = Modifier.wrapContentHeight(),
        ){ videoView ->
            videoState.config.let {
                videoView.setUrl(it)
                videoView.start()
            }
        }
    }
}

@Composable
fun rememberVideoConfigState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    path: String,
    onShot: (Bitmap)->Unit = {},
    isMute: (Boolean) -> Unit = {}
) = remember(key1 = path) {
    VideoConfigState(coroutineScope,path,onShot,isMute)
}

class VideoConfigState(private val coroutineScope: CoroutineScope,path: String,val onShot: (Bitmap)->Unit,val isMute:(Boolean)->Unit){
    var config by mutableStateOf(path)
    var curRatio by mutableStateOf(0)
    var curSpeed by mutableStateOf(2)

    enum class EventType{
        CHANGE_RATIO,
        CHANGE_SPEED,
        CHANGE_MUTE,
        MIRROR_ROTATE,
        SHOT_SCREEN
    }

    class Event(val type: EventType,val ratio: Int = 0,val speed: Float = 0f)

    private val events: MutableSharedFlow<Event> = MutableSharedFlow()

    private var i: Int = 0

    suspend fun VideoView.handleEvents(): Bitmap = withContext(Dispatchers.Main){
        events.collect{
            when(it.type){
                EventType.CHANGE_RATIO -> setScreenScaleType(it.ratio)
                EventType.CHANGE_SPEED -> speed = it.speed
                EventType.CHANGE_MUTE -> {
                    isMute = !isMute
                    isMute(isMute)
                }
                EventType.MIRROR_ROTATE -> {
                    setMirrorRotation(i % 2 == 0)
                    i++
                }
                EventType.SHOT_SCREEN -> { onShot(doScreenShot()) }
            }
        }
    }

    fun setScreenScaleType(ratio: Int){
        val event = Event(EventType.CHANGE_RATIO,ratio = ratio)
        curRatio = ratio
        coroutineScope.launch { events.emit(event) }
    }

    fun setSpeed(speed: Float){
        val event = Event(EventType.CHANGE_SPEED, speed = speed)
        curSpeed = when (speed) {
            0.5f -> 0
            0.75f -> 1
            1f -> 2
            1.5f -> 3
            2f -> 4
            else -> 2
        }
        coroutineScope.launch { events.emit(event) }
    }

    fun changeMute(){
        val event = Event(EventType.CHANGE_MUTE)
        coroutineScope.launch { events.emit(event) }
    }

    fun mirrorRotate(){
        val event = Event(EventType.MIRROR_ROTATE)
        coroutineScope.launch { events.emit(event) }
    }

    fun onShotScreen(){
        val event = Event(EventType.SHOT_SCREEN)
        coroutineScope.launch { events.emit(event) }
    }
}
