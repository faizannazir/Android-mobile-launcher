package com.aether.launcher.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Folder
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aether.launcher.data.model.AppItem
import com.aether.launcher.data.model.FolderItem
import com.aether.launcher.glass.GlassLevel
import com.aether.launcher.glass.glassSurface
import com.aether.launcher.ui.components.FloatingDock
import com.aether.launcher.ui.components.SquircleIcon

@Composable
fun HomeScreen(
    state: com.aether.launcher.viewmodel.LauncherState,
    onAppClick: (AppItem) -> Unit,
    onAppDrawerClick: () -> Unit,
    onSearchClick: () -> Unit,
    onFolderClick: (FolderItem) -> Unit,
    onLongPressEdit: () -> Unit
) {
    // 3D Crystal Sculpture Ambient Light Background Gradient
    val backgroundBrush = if (state.isDarkTheme) {
        Brush.verticalGradient(
            colors = listOf(
                Color(0xFF101319),
                Color(0xFF181C28),
                Color(0xFF101319)
            )
        )
    } else {
        Brush.verticalGradient(
            colors = listOf(
                Color(0xFFF7F9FB),
                Color(0xFFE9D5FF),
                Color(0xFFDBEAFE),
                Color(0xFFF7F9FB)
            )
        )
    }

    val textColor = if (state.isDarkTheme) Color.White else Color(0xFF191C1E)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundBrush)
            .statusBarsPadding()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            // Upper Section: Floating Clock & Date (NO Bounding Box Container)
            Text(
                text = "Tuesday, October 24",
                style = MaterialTheme.typography.titleSmall,
                color = textColor.copy(alpha = 0.8f)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "10:08",
                style = MaterialTheme.typography.displayLarge.copy(fontSize = 80.sp, fontWeight = FontWeight.Light),
                color = textColor
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Weather Pill
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .glassSurface(
                        level = GlassLevel.SUBTLE,
                        isDarkTheme = state.isDarkTheme,
                        blurEnabled = state.isBlurEnabled,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(horizontal = 14.dp, vertical = 6.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.WbSunny,
                    contentDescription = "Sunny",
                    tint = Color(0xFFF59E0B),
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = "  72° • Sunny · AQI 22",
                    style = MaterialTheme.typography.labelSmall,
                    color = textColor
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Google Search Field Pill
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
                    tint = textColor.copy(alpha = 0.6f)
                )
                Text(
                    text = "  Search apps & web...",
                    style = MaterialTheme.typography.bodyMedium,
                    color = textColor.copy(alpha = 0.6f),
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Main App Grid (4x2 Squircle Icons)
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .weight(1f),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Folder items
                items(state.folders) { folder ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .clickable { onFolderClick(folder) }
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
                                contentDescription = folder.title,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(28.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = folder.title,
                            style = MaterialTheme.typography.labelMedium,
                            color = textColor,
                            maxLines = 1
                        )
                    }
                }

                // Grid apps
                items(state.homeGridApps) { app ->
                    SquircleIcon(
                        label = app.label,
                        iconDrawable = app.icon,
                        onClick = { onAppClick(app) },
                        isDarkTheme = state.isDarkTheme,
                        isBlurEnabled = state.isBlurEnabled
                    )
                }
            }

            // Page Indicator Dots
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(textColor, RoundedCornerShape(4.dp))
                )
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .background(textColor.copy(alpha = 0.3f), RoundedCornerShape(3.dp))
                )
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .background(textColor.copy(alpha = 0.3f), RoundedCornerShape(3.dp))
                )
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
