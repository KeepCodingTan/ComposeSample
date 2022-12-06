package com.common.composesample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.common.composesample.ui.theme.ComposeSampleTheme
import com.common.composesample.ui.theme.CustomTheme
import com.common.composesample.ui.theme.ThemeKinds
import com.tencent.mmkv.MMKV

class MainActivity : ComponentActivity() {

    val viewModel by viewModels<TodoViewModel>()

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val choosedThemeid = remember {
                mutableStateOf(MMKV.defaultMMKV()?.getString("themeId",ThemeKinds.DEFAULT.name) ?: ThemeKinds.DEFAULT.name)
            }
            CustomTheme(
                chooseThemeid = choosedThemeid
            ) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
//                    StaggeredGridContent()
                    Column {
                        Row(
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp).fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            for (theme in ThemeKinds.values()){
                                if(choosedThemeid.equals(theme.name)){
                                    Surface(
                                        color = Color.Gray.copy(alpha = 0.5f),
                                        onClick = {
                                            MMKV.defaultMMKV()?.encode("themeId",theme.name)
                                            choosedThemeid.value = theme.name
                                        }
                                    ) {
                                        Text(
                                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                            text = theme.name,
                                            textAlign = TextAlign.Center,
                                            style = TextStyle(color = MaterialTheme.colors.primary)
                                        )
                                    }
                                }else{
                                    Surface(
                                        color = Color.Gray,
                                        onClick = {
                                            MMKV.defaultMMKV()?.encode("themeId",theme.name)
                                            choosedThemeid.value = theme.name
                                        }
                                    ) {
                                        Text(
                                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                            text = theme.name,
                                            textAlign = TextAlign.Center,
                                            style = TextStyle(color = MaterialTheme.colors.primary)
                                        )
                                    }
                                }
                            }
                        }
                        TodoScreenUi(
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
    }


    @Composable
    private fun TodoScreenUi(modifier: Modifier = Modifier) {
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
            modifier = modifier,
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