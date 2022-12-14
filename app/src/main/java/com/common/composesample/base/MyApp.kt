package com.common.composesample.base

import android.app.Application
import com.tencent.mmkv.MMKV
import xyz.doikki.videoplayer.exo.ExoMediaPlayerFactory
import xyz.doikki.videoplayer.ijk.IjkPlayerFactory
import xyz.doikki.videoplayer.player.AndroidMediaPlayerFactory
import xyz.doikki.videoplayer.player.VideoViewConfig
import xyz.doikki.videoplayer.player.VideoViewManager

/**
 * @Author: Sun
 * @CreateDate: 2022/12/6
 * @Description: java类作用描述
 */
class MyApp:Application() {

    override fun onCreate() {
        super.onCreate()
        //mmkv
        MMKV.initialize(this)
        initPlayer()
    }

    private fun initPlayer() {
        VideoViewManager.setConfig(
            VideoViewConfig.newBuilder()
            //使用使用IjkPlayer解码
            .setPlayerFactory(IjkPlayerFactory.create())
            /*//使用ExoPlayer解码
            .setPlayerFactory(ExoMediaPlayerFactory.create())
            //使用MediaPlayer解码
            .setPlayerFactory(AndroidMediaPlayerFactory.create())*/
            .build());
    }

}