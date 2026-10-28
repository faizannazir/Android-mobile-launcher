package com.aether.launcher.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aether.launcher.data.model.AppItem
import com.aether.launcher.glass.GlassLevel
import com.aether.launcher.glass.glassSurface

@Composable
fun FloatingDock(
    dockApps: List<AppItem>,
    onAppClick: (AppItem) -> Unit,
    onAppDrawerClick: () -> Unit,
    isDarkTheme: Boolean = false,
    isBlurEnabled: Boolean = true,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 8.dp)
            .glassSurface(
                level = GlassLevel.BALANCED,
                isDarkTheme = isDarkTheme,
                blurEnabled = isBlurEnabled,
                shape = RoundedCornerShape(36.dp)
            )
            .padding(vertical = 10.dp, horizontal = 12.dp)
    ) {
        dockApps.take(4).forEach { app ->
            SquircleIcon(
                label = app.label,
                iconDrawable = app.icon,
                onClick = { onAppClick(app) },
                showLabel = false,
                isDarkTheme = isDarkTheme,
                isBlurEnabled = isBlurEnabled,
                size = 52.dp
            )
        }

        // Dedicated App Drawer Button
        SquircleIcon(
            label = "Apps",
            iconDrawable = null,
            onClick = onAppDrawerClick,
            showLabel = false,
            isDarkTheme = isDarkTheme,
            isBlurEnabled = isBlurEnabled,
            size = 52.dp
        )
    }
}
