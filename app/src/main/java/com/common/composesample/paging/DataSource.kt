package com.common.composesample.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.common.composesample.Constants

/**
 * @Author: Sun
 * @CreateDate: 2022/12/21
 * @Description: java类作用描述
 */
abstract class DataSource<T : Any>:PagingSource<Int,T>() {

    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        val currentPage = params.key ?: 1
        val pageSize = params.loadSize
        val data = loadData(currentPage,pageSize)
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

    abstract suspend fun loadData(currentPage: Int,pageSize: Int): List<T>
}