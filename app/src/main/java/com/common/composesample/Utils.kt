package com.common.composesample

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

/**
 * @Author: Sun
 * @CreateDate: 2022/12/9
 * @Description: java类作用描述
 */

@SuppressLint("SimpleDateFormat")
fun timeFormat(time: Long): String{
    return SimpleDateFormat("yy-MM-dd hh-mm-ss").format(time)
}