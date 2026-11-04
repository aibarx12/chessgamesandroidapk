package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = GoldLight,
    onPrimary = Color(0xFF261900),
    primaryContainer = GoldDark,
    onPrimaryContainer = Color(0xFFFFDEA3),
    secondary = BoardGreen,
    onSecondary = Color.White,
    tertiary = TealEngine,
    background = CharcoalDark,
    onBackground = Color(0xFFE2E2E6),
    surface = CharcoalSurface,
    onSurface = Color(0xFFE2E2E6),
    surfaceVariant = CharcoalVariant,
    onSurfaceVariant = Color(0xFFC7CBD5)
)

private val LightColorScheme = lightColorScheme(
    primary = GoldDark,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFFFDEA3),
    onPrimaryContainer = Color(0xFF2B1700),
    secondary = BoardGreen,
    onSecondary = Color.White,
    tertiary = TealEngine,
    background = ParchmentLight,
    onBackground = Color(0xFF1B1B1E),
    surface = ParchmentSurface,
    onSurface = Color(0xFF1B1B1E),
    surfaceVariant = ParchmentVariant,
    onSurfaceVariant = Color(0xFF48453D)
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Keep consistent chess aesthetic
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
