package com.common.composesample.entity

/**
 * @Author: Sun
 * @CreateDate: 2022/12/19
 * @Description: java类作用描述
 */
data class VideoBean(
    val list: List<VideoItem>,
    val pageNo: Int,
    val pageSize: Int
)

data class VideoItem(
    val image: Int,
    val time: String,
    val title: String,
    val source: String
)