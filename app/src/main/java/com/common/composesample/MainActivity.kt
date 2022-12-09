package com.common.composesample

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ScrollableTabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.common.composesample.ui.theme.ComposeSampleTheme
import com.common.composesample.ui.theme.CustomTheme
import com.common.composesample.ui.theme.SwitchTheme
import com.common.composesample.ui.theme.ThemeKinds
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.tencent.mmkv.MMKV
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

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
    fun TestTheme(){
        val choosedThemeid = remember {
            mutableStateOf(MMKV.defaultMMKV()?.getString("themeId",ThemeKinds.DEFAULT.name) ?: ThemeKinds.DEFAULT.name)
        }
        CustomTheme(
            chooseThemeid = choosedThemeid
        ) {
            // A surface container using the 'background' color from the theme
            SwitchTheme(choosedThemeid){
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

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Test(){
    val choosedThemeid = remember {
        mutableStateOf(MMKV.defaultMMKV()?.getString("themeId",ThemeKinds.DEFAULT.name) ?: ThemeKinds.DEFAULT.name)
    }
    CustomTheme(chooseThemeid = choosedThemeid) {
        val state = rememberPagerState(initialPage = 0)
        Column() {
            HorizontalPager(state = state,count = items.size, modifier = Modifier.weight(1f)) { page->
                val content = items[state.currentPage].name
                Log.d("sun","内容=${content}")
                when(page){
                    0-> HomeUi()
                    1-> ChooseCourseUi(text = content)
                    2-> ChooseCourseUi(text = content)
                    3-> ChooseCourseUi(text = content)
                    4-> ChooseCourseUi(text = content)
                }
            }
            BottomTab(items = items,state = state)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
   Test()
}

