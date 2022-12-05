package com.common.composesample

import TodoViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.common.composesample.ui.theme.ComposeSampleTheme

class MainActivity : ComponentActivity() {

    val viewModel by viewModels<TodoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
//                    StaggeredGridContent()
                    TodoScreenUi()
                }
            }
        }
    }


    @Composable
    private fun TodoScreenUi() {
        /*val items = listOf(
            TodoItem("测试手动阀手动阀", ToDoIcon.Square),
            TodoItem("gfgdgdfgsdf", ToDoIcon.Done),
            TodoItem("的发射点发射点", ToDoIcon.Event),
            TodoItem("手动阀手动阀", ToDoIcon.Privacy),
            TodoItem("饿温热微软", ToDoIcon.Trash),
            TodoItem("和人谈话人体", ToDoIcon.Square)
        )*/
//        val items: List<TodoItem> by viewModel.items.observeAsState(listOf())
        TodoScreen(
            items = viewModel._items,
            onItemAdd = { viewModel.addItem(it) },
            onItemRemove = { viewModel.removeItem(it) },
            onEdit = { viewModel.onEdit(it) },
            currentEditing = viewModel.currentEdit,
            startEdit = { viewModel.startEdit(it) },
            onEditDown = { viewModel.onEditDown() }
        )
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeSampleTheme {
        StaggeredGridContent()
    }
}