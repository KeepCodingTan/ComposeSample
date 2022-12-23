package com.common.composesample.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.common.composesample.Constants
import com.common.composesample.entity.VideoItem
import com.common.composesample.paging.NewsDataSource
import kotlinx.coroutines.flow.Flow

/**
 * @Author: Sun
 * @CreateDate: 2022/12/20
 * @Description: java类作用描述
 */
class NewViewModel:ViewModel() {

    /*private val repository = ComposeRepository(RetrofitManager.createService(ApiService::class.java))

    var isRefresh by mutableStateOf(false)

    var news by mutableStateOf(listOf<VideoItem>())
        private set

    suspend fun getNewList(){
        isRefresh = true
        when(val response = repository.getNewList()){
            is PackageResponse.Success -> {
                isRefresh = false
                response.data?.list?.let { news = it }
            }
            is PackageResponse.Failure -> {
                isRefresh = false
            }
        }
    }*/

    var showHolder by mutableStateOf(true)

    val newsList: Flow<PagingData<VideoItem>> = Pager(
        config = PagingConfig(
            pageSize = Constants.PAGE_SIZE,
            prefetchDistance = 1,
            initialLoadSize = Constants.INITIAL_SIZE
        ),
        pagingSourceFactory = { NewsDataSource{ showHolder = false } }
    ).flow.cachedIn(viewModelScope)

}