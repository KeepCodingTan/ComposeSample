package com.common.composesample.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.common.composesample.Constants
import com.common.composesample.entity.VideoItem
import com.common.composesample.paging.VideoDataSource
import kotlinx.coroutines.flow.Flow

/**
 * @Author: Sun
 * @CreateDate: 2022/12/19
 * @Description: java类作用描述
 */
class VideoModel: ViewModel() {

    /*private val repository = ComposeRepository(RetrofitManager.createService(ApiService::class.java))

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
    }*/

    val videoList: Flow<PagingData<VideoItem>> = Pager(
        config = PagingConfig(
            pageSize = Constants.PAGE_SIZE,
            prefetchDistance = 1,
            initialLoadSize = Constants.INITIAL_SIZE
        ),
        pagingSourceFactory = { VideoDataSource() }
    ).flow.cachedIn(viewModelScope)

}