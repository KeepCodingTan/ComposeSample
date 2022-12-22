package com.common.composesample.ui.page

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.common.composesample.ui.theme.ThemeKinds

/**
 * @Author: Sun
 * @CreateDate: 2022/12/22
 * @Description: java类作用描述
 */
@Composable
fun DrawerContent(
    chooseThemeId: String,
    themeChoose: (ThemeKinds)->Unit
){
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.primary)
                    .padding(vertical = 50.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(imageVector = Icons.Default.Person, contentDescription = "",tint = Color.White, modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(Color.Cyan)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "作者：Sun", style = MaterialTheme.typography.h5, color = Color.White)
            }
        }
        items(ThemeKinds.values()){
            DrawerItem(it.name == chooseThemeId,it){
                themeChoose(it)
            }
        }
    }
}

@Composable
fun DrawerItem(
    selected: Boolean,
    theme: ThemeKinds,
    themeChoose: ()->Unit
){
    var iamgeVector: ImageVector = Icons.Default.Mood
    var themeText = ""
    when(theme){
        ThemeKinds.DEFAULT ->{
            iamgeVector = Icons.Default.EmojiEmotions
            themeText = "默认主题"
        }
        ThemeKinds.YELLOW ->{
            iamgeVector = Icons.Default.PeopleAlt
            themeText = "黄色主题"
        }
        ThemeKinds.RED ->{
            iamgeVector = Icons.Default.Public
            themeText = "红色主题"
        }
        ThemeKinds.BLUE ->{
            iamgeVector = Icons.Default.Poll
            themeText = "蓝色主题"
        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .clickable { themeChoose() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = iamgeVector, tint = MaterialTheme.colors.primary, contentDescription = "")
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = themeText)
        Spacer(modifier = Modifier.weight(1f))
        if(selected){
            Icon(imageVector = Icons.Default.Check, tint = MaterialTheme.colors.primary, contentDescription = "")
        }
    }
}