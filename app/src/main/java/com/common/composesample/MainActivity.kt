package com.common.composesample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.common.composesample.ui.page.TodoScreen
import com.common.composesample.ui.theme.*
import com.common.composesample.viewmodel.TodoViewModel
import com.common.composesample.widget.CustomNavHost
import com.common.composesample.widget.LoadingMore
import com.tencent.mmkv.MMKV

class MainActivity : ComponentActivity() {
    val viewModel by viewModels<TodoViewModel>()

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Test()
        }
    }


    @Composable
    fun TestTheme() {
        val choosedThemeid = remember {
            mutableStateOf(
                MMKV.defaultMMKV()?.getString("themeId", ThemeKinds.DEFAULT.name)
                    ?: ThemeKinds.DEFAULT.name
            )
        }
        CustomTheme(
            chooseThemeid = choosedThemeid
        ) {
            // A surface container using the 'background' color from the theme
            SwitchTheme(choosedThemeid) {
                TodoScreenUi()
            }
        }
    }

    @Composable
    private fun TodoScreenUi(modifier: Modifier = Modifier) {
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
fun Test() {
    val choosedThemeid = remember {
        mutableStateOf(
            MMKV.defaultMMKV()?.getString("themeId", ThemeKinds.DEFAULT.name)
                ?: ThemeKinds.DEFAULT.name
        )
    }
    CustomTheme(chooseThemeid = choosedThemeid) {
        CustomNavHost()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LoadingMore()
}

