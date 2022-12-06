package com.common.composesample

import android.app.Application
import com.tencent.mmkv.MMKV

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
    }

}