package com.common.composesample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEmotions
import androidx.compose.material.icons.filled.PeopleAlt
import androidx.compose.material.icons.filled.Poll
import androidx.compose.material.icons.filled.Public
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.common.composesample.ui.page.DrawerContent
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
    val (chooseThemeId,setThemeId) = remember {
        mutableStateOf(
            MMKV.defaultMMKV()?.getString("themeId", ThemeKinds.DEFAULT.name)
                ?: ThemeKinds.DEFAULT.name
        )
    }
    CustomTheme(chooseThemeid = chooseThemeId) {
        CustomNavHost(chooseThemeId){
            setThemeId(it.name)
            MMKV.defaultMMKV()?.encode("themeId",it.name)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}

