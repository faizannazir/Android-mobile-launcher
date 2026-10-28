package com.aether.launcher.ui.components

import android.appwidget.AppWidgetHost
import android.appwidget.AppWidgetManager
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.aether.launcher.data.model.HomeLayoutItem
import com.aether.launcher.glass.GlassLevel
import com.aether.launcher.glass.glassSurface

@Composable
fun WidgetHostContainer(
    widgetItem: HomeLayoutItem.Widget,
    appWidgetManager: AppWidgetManager,
    appWidgetHost: AppWidgetHost,
    isDarkTheme: Boolean,
    isBlurEnabled: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(4.dp)
            .glassSurface(
                level = GlassLevel.BALANCED,
                isDarkTheme = isDarkTheme,
                blurEnabled = isBlurEnabled,
                shape = RoundedCornerShape(24.dp)
            )
            .padding(6.dp)
    ) {
        val info = appWidgetManager.getAppWidgetInfo(widgetItem.appWidgetId)
        if (info != null) {
            AndroidView(
                factory = { context ->
                    appWidgetHost.createView(context, widgetItem.appWidgetId, info)
                },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
