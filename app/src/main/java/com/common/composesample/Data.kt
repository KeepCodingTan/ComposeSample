package com.common.composesample

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import java.util.*

/**
 * @Author: Sun
 * @CreateDate: 2022/12/3
 * @Description: java类作用描述
 */

data class TodoItem(
    val task: String,
    val icon: ToDoIcon = ToDoIcon.Default,
    val id: UUID = UUID.randomUUID()
)

enum class ToDoIcon(
   val imageVector: ImageVector,
   @StringRes val contentDescription: Int
){
    Square(Icons.Default.CropSquare,R.string.cd_expand),
    Done(Icons.Default.Done,R.string.cd_expand),
    Event(Icons.Default.Event,R.string.cd_event),
    Privacy(Icons.Default.PrivacyTip,R.string.cd_privacy),
    Trash(Icons.Default.RestoreFromTrash,R.string.cd_restore);

    companion object{
        val Default = Square
    }
}