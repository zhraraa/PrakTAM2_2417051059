package com.example.praktam2_2417051059.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val AppColorScheme = lightColorScheme(
    background = GreyBackground,
    primary = BrownBackground,
    secondary = SageBackground,
    surface = WhiteBackground,
    onPrimary = BrownText,
    onSecondary = GreyText,
    onSurface = BlackText
    )


@Composable
fun PrakTAM2_2417051059Theme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = AppColorScheme,
        typography = Typography,
        content = content
    )
}