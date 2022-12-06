package com.common.composesample.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import androidx.core.text.toSpannable

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

//红色主题色值
private val RedThemeColors = lightColors(
    primary = Color(0xFFFF4040),
    background = Color(0x66FF4040)
)

//黄色主题色值
private val YellowThemeColors = lightColors(
    primary = Color(0xFFDAA520),
    background = Color(0x66FFD700)
)

//蓝色主题色值
private val BlueThemeColors = lightColors(
    primary = Color(0xFF436EEE),
    background = Color(0x6600FFFF)
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
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

@Composable
fun CustomTheme(
    chooseThemeid: MutableState<String>,
    content: @Composable ()->Unit
){
    val colors = when(chooseThemeid.value){
        ThemeKinds.DEFAULT.name -> LightColorPalette
        ThemeKinds.BLUE.name -> BlueThemeColors
        ThemeKinds.RED.name -> RedThemeColors
        ThemeKinds.YELLOW.name -> YellowThemeColors
        else -> LightColorPalette
    }

    val shape = when(chooseThemeid.value){
        ThemeKinds.DEFAULT.name -> Shapes
        ThemeKinds.BLUE.name -> Shapes
        ThemeKinds.RED.name -> Shapes1
        ThemeKinds.YELLOW.name -> Shapes1
        else -> Shapes1
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
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