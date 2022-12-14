package com.common.composesample.utils

import com.common.composesample.entity.ToDoIcon
import com.common.composesample.entity.TodoItem

/**
 * @Author: Sun
 * @CreateDate: 2022/12/5
 * @Description: java类作用描述
 */

fun generateTodoItem(): TodoItem {
    val tasks = listOf(
        "随机添加一条数据",
        "你是真的很牛皮",
        "给你一颗棒棒糖",
        "我是夫了",
        "给你一个大棒槌",
        "哈哈哈哈哈哈哈哈哈",
        "你不是真正的快乐喔",
        "想学，我教你啊",
    ).random()
    val icon = ToDoIcon.values().random()
    return TodoItem(tasks,icon)
}