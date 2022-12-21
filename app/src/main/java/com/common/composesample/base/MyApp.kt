package com.common.composesample.base

import android.app.Application
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.common.composesample.Constants
import com.common.composesample.utils.searchMatchStr
import com.common.libnet.NetWorkManager
import com.common.libnet.inter.NetWorkInterface
import com.tencent.mmkv.MMKV
import okhttp3.Request
import xyz.doikki.videoplayer.ijk.IjkPlayerFactory
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
        initNetWork()
    }

    private fun initPlayer() {
        VideoViewManager.setConfig(
            VideoViewConfig.newBuilder()
            //使用使用IjkPlayer解码
            .setPlayerFactory(IjkPlayerFactory.create())
            //使用ExoPlayer解码
//            .setPlayerFactory(ExoMediaPlayerFactory.create())
            //使用MediaPlayer解码
//            .setPlayerFactory(AndroidMediaPlayerFactory.create())
            .build());
    }

    private fun initNetWork() {
        NetWorkManager.init(object : NetWorkInterface {

            override fun encryptionBody(request: Request): Request {
                return request
            }

            override fun hostUrl(): String {
                return Constants.HOST_PRE
            }

            override fun httpLog(log: String?) {
                log?.let {
                    val maxSize = 666
                    if (it.length < maxSize) {
                        LogUtils.d(it)
                    } else {
                        val header: String = searchMatchStr(it, Constants.REGEX)
                        var index = 0
                        while (index + maxSize < it.length) {
                            val content: String = it.substring(index, index + maxSize)
                            if (index != 0) {
                                LogUtils.d(header+"\n"+content)
                            } else {
                                LogUtils.d(content)
                            }
                            index += maxSize
                        }
                        LogUtils.d(header+"\n"+it.substring(index))
                    }
                }
            }

            override fun toast(message: String?) {
                message?.let { ToastUtils.showShort(it) }
            }

            override fun userToken(): String {
                return "821ffb977e6a2df86b0bd895e3ebb574"
            }
        })
    }

}