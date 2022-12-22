package com.common.composesample.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200,
)

private val LightColorPalette = lightColors(
    primary = color_default,
    onPrimary = color_white,
    background = color_white,
    onBackground = color_default
)

//红色主题色值
private val RedThemeColors = lightColors(
    primary = color_red,
    onPrimary = color_white,
    background = color_white,
    onBackground = color_red
)

//黄色主题色值
private val YellowThemeColors = lightColors(
    primary = color_yellow,
    onPrimary = color_white,
    background = color_white,
    onBackground = color_yellow
)

//蓝色主题色值
private val BlueThemeColors = lightColors(
    primary = color_blue,
    onPrimary = color_white,
    background = color_white,
    onBackground = color_blue
)

@Composable
fun ComposeSampleTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = craneTypography,
        shapes = Shapes,
        content = content
    )
}

@Composable
fun CustomTheme(
    chooseThemeid: String,
    content: @Composable ()->Unit
){
    val systemUiController = rememberSystemUiController()
    val colors = when(chooseThemeid){
        ThemeKinds.DEFAULT.name -> LightColorPalette
        ThemeKinds.BLUE.name -> BlueThemeColors
        ThemeKinds.RED.name -> RedThemeColors
        ThemeKinds.YELLOW.name -> YellowThemeColors
        else -> LightColorPalette
    }

    val shape = when(chooseThemeid){
        ThemeKinds.DEFAULT.name -> Shapes
        ThemeKinds.BLUE.name -> Shapes
        ThemeKinds.RED.name -> Shapes1
        ThemeKinds.YELLOW.name -> Shapes1
        else -> Shapes1
    }

    systemUiController.setSystemBarsColor(color = colors.primary)

    MaterialTheme(
        colors = colors,
        typography = craneTypography,
        shapes = shape,
        content = content
    )
}

enum class ThemeKinds{
    DEFAULT,
    RED,
    YELLOW,
    BLUE
}