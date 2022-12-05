package com.common.composesample

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlin.random.Random

/**
 * @Author: Sun
 * @CreateDate: 2022/12/5
 * @Description: java类作用描述
 */

@Composable
fun TodoScreen(
    items: List<TodoItem>,
    modifier: Modifier = Modifier,
    currentEditing: TodoItem?,
    startEdit: (TodoItem)->Unit,
    onEdit: (TodoItem)->Unit,
    onEditDown: ()->Unit,
    onItemAdd: (TodoItem)->Unit,
    onItemRemove: (TodoItem)->Unit,
) {
    Column {
        val editing = currentEditing == null
        TodoInputBackground(elevate = true) {
            if(editing){
                ToDoInputLayoutStatus(onItemAdd)
            }else{
                Text(
                    text = "Edit Item",
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.CenterVertically)
                        .padding(16.dp)
                        .fillMaxWidth()
                )
            }
        }
        LazyColumn(
            modifier = modifier.weight(1f)
        ) {
            items(items) {
                if(currentEditing?.id == it.id){
                    TodoInputEdit(currentEditing,{e->onEdit(e)},{onEditDown()},{r->onItemRemove(r)})
                }else{
                    TodoRow(item = it, modifier.fillParentMaxWidth(),startEdit)
                }
            }
        }
        Button(
            onClick = {onItemAdd(generateTodoItem())},
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Add Item")
        }
    }
}

@Composable
fun TodoRow(
    item: TodoItem,
    modifier: Modifier = Modifier,
    startEdit: (TodoItem)->Unit
) {
    val tintAlpha = remember(item.id) {
        randomFloat()
    }
    Log.d("sun","id=${item.id}   tintAlpha=${tintAlpha}")
    Row(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { startEdit(item) },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = item.task)
        Icon(
            imageVector = item.icon.imageVector,
            tint = LocalContentColor.current.copy(alpha = tintAlpha),
            contentDescription = stringResource(id = item.icon.contentDescription)
        )
    }
}

fun randomFloat(): Float{
    return Random.nextFloat().coerceIn(0.3f,0.9f)
}