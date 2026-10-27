package com.aether.launcher.glass

import android.graphics.RenderEffect as AndroidRenderEffect
import android.graphics.Shader
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.aether.launcher.ui.theme.*

enum class GlassLevel(
    val blurDp: Float,
    val lightColor: Color,
    val darkColor: Color,
    val lightFallback: Color,
    val darkFallback: Color,
    val specularBorderLight: Color,
    val specularBorderDark: Color,
    val elevation: Dp
) {
    SUBTLE(
        blurDp = 16f,
        lightColor = GlassLightLevel1,
        darkColor = GlassDarkLevel1,
        lightFallback = Color(0xF0F0F3F6),
        darkFallback = Color(0xF0181C24),
        specularBorderLight = Color(0x66FFFFFF),
        specularBorderDark = Color(0x1AFFFFFF),
        elevation = 2.dp
    ),
    BALANCED(
        blurDp = 28f,
        lightColor = GlassLightLevel2,
        darkColor = GlassDarkLevel2,
        lightFallback = Color(0xF5FFFFFF),
        darkFallback = Color(0xF5191C28),
        specularBorderLight = SpecularBorderLight,
        specularBorderDark = SpecularBorderDark,
        elevation = 8.dp
    ),
    STRONG(
        blurDp = 44f,
        lightColor = GlassLightLevel3,
        darkColor = GlassDarkLevel3,
        lightFallback = Color(0xFFFFFFFF),
        darkFallback = Color(0xFF101319),
        specularBorderLight = SpecularBorderLight,
        specularBorderDark = SpecularBorderDark,
        elevation = 16.dp
    )
}

@Composable
fun Modifier.glassSurface(
    level: GlassLevel = GlassLevel.BALANCED,
    isDarkTheme: Boolean = false,
    blurEnabled: Boolean = true,
    shape: Shape = RoundedCornerShape(28.dp),
    borderWidth: Dp = 1.dp
): Modifier {
    val backgroundColor = if (isDarkTheme) level.darkColor else level.lightColor
    val fallbackColor = if (isDarkTheme) level.darkFallback else level.lightFallback
    val borderColor = if (isDarkTheme) level.specularBorderDark else level.specularBorderLight

    return this
        .shadow(
            elevation = level.elevation,
            shape = shape,
            clip = false,
            ambientColor = Color.Black.copy(alpha = 0.15f),
            spotColor = Color.Black.copy(alpha = 0.25f)
        )
        .clip(shape)
        .then(
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && blurEnabled) {
                Modifier
                    .graphicsLayer {
                        renderEffect = AndroidRenderEffect.createBlurEffect(
                            level.blurDp,
                            level.blurDp,
                            Shader.TileMode.CLAMP
                        ).asComposeRenderEffect()
                    }
                    .background(backgroundColor, shape)
            } else {
                Modifier.background(fallbackColor, shape)
            }
        )
        .border(borderWidth, borderColor, shape)
}
