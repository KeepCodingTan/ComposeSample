package com.common.composesample.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.common.composesample.api.ApiService
import com.common.composesample.entity.VideoItem
import com.common.composesample.repository.ComposeRepository
import com.common.libnet.RetrofitManager
import com.common.libnet.data.PackageResponse

/**
 * @Author: Sun
 * @CreateDate: 2022/12/19
 * @Description: java类作用描述
 */
class VideoModel: ViewModel() {

    private val repository = ComposeRepository(RetrofitManager.createService(ApiService::class.java))

    var list by mutableStateOf(listOf<VideoItem>())
        private set

    var isRefresh by mutableStateOf(false)

    suspend fun getVideoList(){
        isRefresh = true
        when(val response = repository.getVideoList()){
            is PackageResponse.Success -> {
                isRefresh = false
                response.data?.list?.let { list = it }
            }
            is PackageResponse.Failure -> {
                isRefresh = false
            }
        }
    }

}