package com.common.composesample.repository

import com.common.composesample.api.ApiService
import com.common.libnet.ktx.httCall

/**
 * @Author: Sun
 * @CreateDate: 2022/12/19
 * @Description: java类作用描述
 */
class ComposeRepository(private val service: ApiService) {

    suspend fun getVideoList(pageSize: Int,pageNo: Int) = httCall {
        service.getVideoList(pageSize,pageNo)
    }

    suspend fun getNewList() = httCall {
        service.getNewList()
    }

}