package com.common.composesample.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.regex.Pattern

/**
 * @Author: Sun
 * @CreateDate: 2022/12/9
 * @Description: java类作用描述
 */

@SuppressLint("SimpleDateFormat")
fun timeFormat(time: Long): String{
    return SimpleDateFormat("yy-MM-dd hh-mm-ss").format(time)
}

fun searchMatchStr(content: String?, regex: String?): String {
    val pattern = Pattern.compile(regex)
    val matcher = pattern.matcher(content)
    return if (matcher.find()) {
        matcher.group()
    } else ""
}