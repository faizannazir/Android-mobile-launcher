package com.aether.launcher.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = PixelFrostBlue,
    secondary = RadiantIris,
    tertiary = LuminousOrchid,
    background = DeepObsidian,
    surface = DeepObsidian,
    onPrimary = ObsidianSlate,
    onSecondary = Color.White,
    onBackground = PearlWhite,
    onSurface = PearlWhite
)

private val LightColorScheme = lightColorScheme(
    primary = ObsidianSlate,
    secondary = ElectricSky,
    tertiary = LuminousOrchid,
    background = PearlWhite,
    surface = PearlWhite,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = ObsidianSlate,
    onSurface = ObsidianSlate
)

@Composable
fun AetherLauncherTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AetherTypography,
        content = content
    )
}
