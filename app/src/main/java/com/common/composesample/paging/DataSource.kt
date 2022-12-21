package com.common.composesample.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.common.composesample.Constants
import com.common.composesample.entity.VideoItem
import com.common.composesample.repository.ComposeRepository
import com.common.libnet.data.PackageResponse

/**
 * @Author: Sun
 * @CreateDate: 2022/12/21
 * @Description: java类作用描述
 */
class DataSource(
    val repository: ComposeRepository
):PagingSource<Int,VideoItem>() {


    override fun getRefreshKey(state: PagingState<Int, VideoItem>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, VideoItem> {
        val currentPage = params.key ?: 1
        val pageSize = params.loadSize
        val data = when(val response = repository.getVideoList(pageSize,currentPage)){
            is PackageResponse.Success -> {
                response.data?.let {
                    it.list
                } ?:  listOf()
            }
            is PackageResponse.Failure -> {
                listOf()
            }
        }
        var prevKey: Int?
        var nextKey: Int?
        val realPageSize = Constants.PAGE_SIZE
        val initialPageSize = Constants.INITIAL_SIZE
        if(currentPage == 1){
            prevKey = null
            nextKey = initialPageSize/realPageSize + 1
        }else{
            prevKey = currentPage - 1
            nextKey = currentPage + 1
        }
        return LoadResult.Page(
            data = data,
            prevKey = prevKey,
            nextKey = nextKey
        )
    }
}