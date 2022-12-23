package com.common.composesample.paging

import com.common.composesample.api.ApiService
import com.common.composesample.entity.VideoItem
import com.common.composesample.repository.ComposeRepository
import com.common.libnet.RetrofitManager
import com.common.libnet.data.PackageResponse

/**
 * @Author: Sun
 * @CreateDate: 2022/12/22
 * @Description: java类作用描述
 */
class NewsDataSource(
    private val repository: ComposeRepository = ComposeRepository(
        RetrofitManager.createService(
            ApiService::class.java
        )
    ),
    val loadSuccess: ()->Unit
): DataSource<VideoItem>() {

    override suspend fun loadData(currentPage: Int, pageSize: Int): List<VideoItem> {
        return when (val response = repository.getNewList(currentPage, pageSize)) {
            is PackageResponse.Success -> {
                loadSuccess()
                response.data?.list ?: listOf()
            }
            is PackageResponse.Failure -> {
                listOf()
            }
        }
    }

}