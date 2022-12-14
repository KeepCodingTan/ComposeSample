package com.common.composesample.ui.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.common.composesample.entity.searchHistory

/**
 * @Author: Sun
 * @CreateDate: 2022/12/13
 * @Description: java类作用描述
 */
@Composable
fun SearchContent(
    navController: NavHostController
){
    Column {
        TopSearchBar(navController)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "搜索历史",color = MaterialTheme.colors.primaryVariant)
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Default.Delete, tint = MaterialTheme.colors.primaryVariant, contentDescription = "")
            }
        }
        LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)){
            items(searchHistory){
                ItemHistory(it)
            }
        }
    }
}

@Composable
fun ItemHistory(content: String){
    TextButton(onClick = {  }) {
        Text(
            text = content,
            style = MaterialTheme.typography.body2,
            overflow = TextOverflow.Ellipsis, maxLines = 1,
            color = MaterialTheme.colors.onSecondary,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun TopSearchBar(
    navController: NavHostController
){
    val (text, updateText) = remember {
        mutableStateOf("新冠感染者初期症状")
    }
    Box(modifier = Modifier.fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(MaterialTheme.colors.primary)
                .padding(start = 8.dp, top = 16.dp, end = 16.dp)
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Default.ArrowBackIos, tint = MaterialTheme.colors.background, contentDescription = "")
            }
            BasicTextField(
                value = text,
                onValueChange = updateText,
                singleLine = true,
                cursorBrush = SolidColor(MaterialTheme.colors.primary),
                modifier = Modifier
                    .weight(1f)
                    .background(MaterialTheme.colors.primary),
                decorationBox = { innerTextField ->
                    Row(
                        modifier = Modifier
                            .background(
                                MaterialTheme.colors.background,
                                RoundedCornerShape(8.dp)
                            )
                            .padding(6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            modifier = Modifier.size(24.dp),
                            contentDescription = ""
                        )
                        innerTextField()
                    }
                }
            )
            Spacer(modifier = Modifier.width(4.dp))
            TextButton(onClick = {  }) {
                Text(text = "搜索", fontSize = 18.sp, color = Color.Red)
            }
        }
    }
}