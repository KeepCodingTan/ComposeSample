package com.common.composesample.api

import com.common.composesample.entity.VideoBean
import com.common.libnet.data.BaseResponse
import retrofit2.http.GET

/**
 * @Author: Sun
 * @CreateDate: 2022/12/19
 * @Description: java类作用描述
 */
interface ApiService {

    @GET("user/videoList")
    suspend fun getVideoList(): BaseResponse<VideoBean>

    @GET("user/news")
    suspend fun getNewList(): BaseResponse<VideoBean>

}