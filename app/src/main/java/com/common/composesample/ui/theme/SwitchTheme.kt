package com.common.composesample.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tencent.mmkv.MMKV

/**
 * @Author: Sun
 * @CreateDate: 2022/12/8
 * @Description: java类作用描述
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwitchTheme(
    choosedThemeid: MutableState<String>,
    content: @Composable ()->Unit
){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
//                    StaggeredGridContent()
        Column {
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .fillMaxWidth(),
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
            content()
        }
    }
}