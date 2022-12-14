package com.common.composesample.ui.page

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.common.composesample.entity.ToDoIcon
import com.common.composesample.entity.TodoItem

/**
 * @Author: Sun
 * @CreateDate: 2022/12/3
 * @Description: java类作用描述
 */

@Composable
fun TodoInputBackground(
    elevate: Boolean,
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
){
    val animatedElevation by animateDpAsState(if(elevate)1.dp else 0.dp,TweenSpec(500))
    Surface(
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.05f),
        elevation = animatedElevation,
        shape = RectangleShape
    ) {
        Row(
            modifier = modifier.animateContentSize(animationSpec = TweenSpec(300)),
            content = content
        )
    }
}

@Composable
fun TodoInputEdit(
    item: TodoItem,
    onEditChange: (TodoItem)->Unit,
    onEditDown: ()->Unit,
    onRemoved: (TodoItem)->Unit
){
    ToDoInputLayout(
        text = item.task,
        setText = {onEditChange(item.copy(task = it))},
        submit = { onEditDown() },
        isIconVisiable = true,
        icon = item.icon,
        setIcon = {onEditChange(item.copy(icon = it))}
    ){
        Row {
           val shirk = Modifier.widthIn(20.dp)
           TextButton(onClick = onEditDown, modifier = shirk) {
               Text(
                   text = "\uD83D\uDCBE",
                   textAlign = TextAlign.End
               )
           }
            TextButton(onClick = {onRemoved(item)}, modifier = shirk) {
                Text(
                    text = "X",
                    textAlign = TextAlign.End
                )
            }
        }
    }
}

@Composable
fun ToDoInputLayoutStatus( onItemComplete: (TodoItem) -> Unit ){
    val (text,setText) = remember {
        mutableStateOf("")
    }
    val (icon,setIcon) = remember {
        mutableStateOf(ToDoIcon.Default)
    }
    val isIconVisiable = text.isNotBlank()

    val submit = {
        onItemComplete(TodoItem(task = text,icon = icon))
        setIcon(ToDoIcon.Default)
        setText("")
    }
    ToDoInputLayout(text, setText, submit, isIconVisiable, icon, setIcon){
        ToDoButton(
            text = "Add",
            enabled = text.isNotBlank()
        ) {
            submit()
        }
    }
}

@Composable
fun ToDoInputLayout(
    text: String,
    setText: (String)->Unit,
    submit: ()->Unit,
    isIconVisiable: Boolean,
    icon: ToDoIcon,
    setIcon: (ToDoIcon)->Unit,
    buttonSlot: @Composable ()->Unit
){

    Column {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
        ) {
            TodoInput(
                text =text,
                onValueChange =setText,
                modifier = Modifier
                    .weight(1f)
                    .padding(10.dp),
                onItemComplete = {submit()}
            )
            Spacer(modifier = Modifier.width(8.dp))
            Box(modifier = Modifier.align(Alignment.CenterVertically)) {
                buttonSlot()
            }
        }
        if(isIconVisiable){
            AnimatedIconRow(
                icon = icon,
                onIconChanged = setIcon,
                modifier = Modifier.padding(top = 8.dp)
            )
        }else{
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TodoInput(
    text: String,
    onValueChange: (String) ->Unit,
    modifier: Modifier,
    onItemComplete: (String) -> Unit
){
    val control = LocalSoftwareKeyboardController.current
    TextField(
        modifier = modifier,
        value = text,
        onValueChange = onValueChange,
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {
            onItemComplete(text)
            control?.hide()
        })
    )
}

@Composable
fun ToDoButton(
    text: String,
    enabled: Boolean,
    modifier: Modifier = Modifier,
    onclick: () -> Unit,
){
    TextButton(
        onClick = onclick,
        shape= CircleShape,
        colors = ButtonDefaults.buttonColors(),
        enabled = enabled,
        modifier = modifier
    ) {
        Text(text = text)
    }
}

@Composable
fun AnimatedIconRow(
    icon: ToDoIcon,
    onIconChanged: (ToDoIcon)->Unit,
    modifier: Modifier = Modifier,
    visiable: Boolean = true
){
    val enter = remember {
        fadeIn(animationSpec = TweenSpec(durationMillis = 300, easing = FastOutLinearInEasing))
    }
    val outer = remember {
        fadeOut(animationSpec = TweenSpec(durationMillis = 300, easing = FastOutSlowInEasing))
    }
    Box(modifier = modifier.defaultMinSize(minHeight = 16.dp)){
        AnimatedVisibility(
            visible = visiable,
            enter = enter,
            exit = outer
        ) {
            IconRow(icon = icon, onIconChanged = onIconChanged)
        }
    }
}

@Composable
fun IconRow(
    icon: ToDoIcon,
    onIconChanged: (ToDoIcon)->Unit,
    modifier: Modifier = Modifier
){
    Row(modifier) {
        for(toDo in ToDoIcon.values()){
            SelectAbleIconButton(
                icon = toDo.imageVector,
                contentDescription = toDo.contentDescription,
                onIconSelect = { onIconChanged(toDo) },
                isSelect = icon == toDo
            )
        }
    }
}

@Composable
fun SelectAbleIconButton(
    icon: ImageVector,
    contentDescription: Int,
    onIconSelect: ()->Unit,
    isSelect: Boolean,
    modifier: Modifier = Modifier
){
    val tint = if(isSelect){
        MaterialTheme.colors.primary
    }else{
        MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
    }
    TextButton(
        onClick = {onIconSelect()},
        shape = CircleShape,
        modifier = modifier
    ) {
        Column {
            Icon(
                imageVector = icon,
                tint = tint,
                contentDescription = stringResource(id = contentDescription)
            )
            if(isSelect){
                Box(
                    modifier = Modifier
                        .padding(top = 3.dp)
                        .width(icon.defaultWidth)
                        .height(1.dp)
                        .background(tint)
                )
            }else{
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}