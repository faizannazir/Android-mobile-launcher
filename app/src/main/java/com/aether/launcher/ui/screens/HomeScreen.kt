package com.aether.launcher.ui.screens

import android.appwidget.AppWidgetHost
import android.appwidget.AppWidgetManager
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Folder
import androidx.compose.material.icons.rounded.GraphicEq
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.WbSunny
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aether.launcher.data.model.AppItem
import com.aether.launcher.data.model.FolderItem
import com.aether.launcher.data.model.HomeLayoutItem
import com.aether.launcher.glass.GlassLevel
import com.aether.launcher.glass.glassSurface
import com.aether.launcher.ui.components.FloatingDock
import com.aether.launcher.ui.components.SquircleIcon
import com.aether.launcher.ui.components.WidgetHostContainer
import com.aether.launcher.viewmodel.LauncherState

@Composable
fun HomeScreen(
    state: LauncherState,
    appWidgetManager: AppWidgetManager?,
    appWidgetHost: AppWidgetHost?,
    onAppClick: (AppItem) -> Unit,
    onAppDrawerClick: () -> Unit,
    onSearchClick: () -> Unit,
    onFolderClick: (FolderItem) -> Unit,
    onOpenEditMode: () -> Unit,
    onStartDrag: (HomeLayoutItem, Int, Int) -> Unit,
    onDropItem: (Int) -> Unit
) {
    val backgroundBrush = if (state.isDarkTheme) {
        Brush.verticalGradient(
            colors = listOf(
                Color(0xFF0F141B),
                Color(0xFF1B2027),
                Color(0xFF0F141B)
            )
        )
    } else {
        Brush.verticalGradient(
            colors = listOf(
                Color(0xFF384A5C),
                Color(0xFF5B6E80),
                Color(0xFFC2B29A),
                Color(0xFF384A5C)
            )
        )
    }

    val textColor = Color.White
    val activePage = state.pages.getOrNull(state.activePageIndex) ?: state.pages.firstOrNull()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundBrush)
            .statusBarsPadding()
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = { onOpenEditMode() }
                )
            }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Architectural Header: Badge & Status Telemetry
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .background(Color(0xFF00FBFF), RoundedCornerShape(3.dp))
                    )
                    Text(
                        text = "  PIXEL // AETHER SPATIAL CORE",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White.copy(alpha = 0.8f)
                    )
                }

                Text(
                    text = "TUE / OCT 24",
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.White.copy(alpha = 0.9f)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Architectural Dot-Matrix Clock & Weather Row
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {
                Text(
                    text = "10:08",
                    style = MaterialTheme.typography.displayLarge.copy(fontSize = 76.sp, fontWeight = FontWeight.Bold),
                    color = Color.White
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .glassSurface(
                            level = GlassLevel.SUBTLE,
                            isDarkTheme = state.isDarkTheme,
                            blurEnabled = state.isBlurEnabled,
                            shape = RoundedCornerShape(20.dp)
                        )
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.WbSunny,
                        contentDescription = "Sunny",
                        tint = Color(0xFFF59E0B),
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = " 72° • AQI 18",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Spatial Media / Telemetry Capsule
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .glassSurface(
                        level = GlassLevel.SUBTLE,
                        isDarkTheme = state.isDarkTheme,
                        blurEnabled = state.isBlurEnabled,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(horizontal = 14.dp, vertical = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.GraphicEq,
                    contentDescription = "Media",
                    tint = Color(0xFF00FBFF),
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = "  Chromatics · Liquid Mirror  |  Pixel Buds 92%",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.White.copy(alpha = 0.9f),
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Google Search Capsule Bar
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .glassSurface(
                        level = GlassLevel.BALANCED,
                        isDarkTheme = state.isDarkTheme,
                        blurEnabled = state.isBlurEnabled,
                        shape = RoundedCornerShape(28.dp)
                    )
                    .clickable(onClick = onSearchClick)
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = "Search",
                    tint = Color.White.copy(alpha = 0.7f)
                )
                Text(
                    text = "  Search apps, contacts, web...",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.7f),
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Main Tactile Liquid-Glass App Grid (4x5 positions)
            val gridPositions = (0..19).toList()

            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .weight(1f),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                itemsIndexed(gridPositions) { _, position ->
                    val layoutItem = activePage?.items?.find { it.position == position }

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(72.dp)
                            .pointerInput(position) {
                                detectTapGestures(
                                    onLongPress = {
                                        if (layoutItem != null) {
                                            onStartDrag(layoutItem, state.activePageIndex, position)
                                        }
                                    }
                                )
                            }
                    ) {
                        when (layoutItem) {
                            is HomeLayoutItem.App -> {
                                val app = state.installedApps.find { it.packageName == layoutItem.packageName }
                                if (app != null) {
                                    SquircleIcon(
                                        label = app.label,
                                        iconDrawable = app.icon,
                                        onClick = { onAppClick(app) },
                                        isDarkTheme = state.isDarkTheme,
                                        isBlurEnabled = state.isBlurEnabled
                                    )
                                }
                            }
                            is HomeLayoutItem.Folder -> {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier
                                        .clickable {
                                            onFolderClick(
                                                FolderItem(
                                                    id = layoutItem.id,
                                                    title = layoutItem.title,
                                                    appPackages = layoutItem.appPackages
                                                )
                                            )
                                        }
                                        .padding(4.dp)
                                ) {
                                    Box(
                                        contentAlignment = Alignment.Center,
                                        modifier = Modifier
                                            .size(56.dp)
                                            .glassSurface(
                                                level = GlassLevel.BALANCED,
                                                isDarkTheme = state.isDarkTheme,
                                                blurEnabled = state.isBlurEnabled,
                                                shape = RoundedCornerShape(22.dp)
                                            )
                                    ) {
                                        Icon(
                                            imageVector = Icons.Rounded.Folder,
                                            contentDescription = layoutItem.title,
                                            tint = Color(0xFF00FBFF),
                                            modifier = Modifier.size(28.dp)
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(6.dp))
                                    Text(
                                        text = layoutItem.title,
                                        style = MaterialTheme.typography.labelMedium,
                                        color = textColor,
                                        maxLines = 1
                                    )
                                }
                            }
                            is HomeLayoutItem.Widget -> {
                                if (appWidgetManager != null && appWidgetHost != null) {
                                    WidgetHostContainer(
                                        widgetItem = layoutItem,
                                        appWidgetManager = appWidgetManager,
                                        appWidgetHost = appWidgetHost,
                                        isDarkTheme = state.isDarkTheme,
                                        isBlurEnabled = state.isBlurEnabled
                                    )
                                }
                            }
                            null -> {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clickable {
                                            if (state.dragState.draggedItem != null) {
                                                onDropItem(position)
                                            }
                                        }
                                )
                            }
                        }
                    }
                }
            }

            // Page Indicator Dots
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                state.pages.forEachIndexed { idx, _ ->
                    val isActive = idx == state.activePageIndex
                    Box(
                        modifier = Modifier
                            .size(if (isActive) 8.dp else 6.dp)
                            .background(
                                color = if (isActive) Color(0xFF00FBFF) else Color.White.copy(alpha = 0.3f),
                                shape = RoundedCornerShape(4.dp)
                            )
                    )
                }
            }

            // Floating Glass Dock
            FloatingDock(
                dockApps = state.dockApps,
                onAppClick = onAppClick,
                onAppDrawerClick = onAppDrawerClick,
                isDarkTheme = state.isDarkTheme,
                isBlurEnabled = state.isBlurEnabled,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
    }
}
