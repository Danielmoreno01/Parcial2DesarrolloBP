package com.example.parcial2.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val HomecenterRed    = Color(0xFFDF2A2A)
private val HomecenterYellow = Color(0xFFF9B900)
private val HomecenterGray   = Color(0xFF4A4A4A)
private val HomecenterWhite  = Color(0xFFFFFFFF)

private val LightColorScheme = lightColorScheme(
    primary      = HomecenterRed,
    secondary    = HomecenterYellow,
    background   = HomecenterWhite,
    surface      = HomecenterWhite,
    onPrimary    = HomecenterWhite,
    onSecondary  = HomecenterGray,
    onBackground = HomecenterGray,
    onSurface    = HomecenterGray,
)

private val DarkColorScheme = darkColorScheme(
    primary      = HomecenterRed,
    secondary    = HomecenterYellow,
    background   = HomecenterGray,
    surface      = HomecenterGray,
    onPrimary    = HomecenterWhite,
    onSecondary  = HomecenterWhite,
    onBackground = HomecenterWhite,
    onSurface    = HomecenterWhite,
)

@Composable
fun Parcial2Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val colors = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S ->
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        darkTheme -> DarkColorScheme
        else      -> LightColorScheme
    }
    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}
