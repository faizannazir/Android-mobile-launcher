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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.aether.launcher.data.model.AppCategory
import com.aether.launcher.data.model.AppItem
import com.aether.launcher.glass.GlassLevel
import com.aether.launcher.glass.glassSurface
import com.aether.launcher.ui.components.SquircleIcon

@Composable
fun AppDrawerScreen(
    state: com.aether.launcher.viewmodel.LauncherState,
    onAppClick: (AppItem) -> Unit,
    onCategorySelect: (AppCategory) -> Unit,
    onCloseDrawer: () -> Unit
) {
    val textColor = if (state.isDarkTheme) Color.White else Color(0xFF191C1E)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .glassSurface(
                level = GlassLevel.STRONG,
                isDarkTheme = state.isDarkTheme,
                blurEnabled = state.isBlurEnabled,
                shape = RoundedCornerShape(0.dp)
            )
            .statusBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Header Row
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "App Drawer",
                    style = MaterialTheme.typography.headlineMedium,
                    color = textColor,
                    modifier = Modifier.weight(1f)
                )

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .clickable(onClick = onCloseDrawer)
                        .padding(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Close,
                        contentDescription = "Close",
                        tint = textColor
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Category Filter Chips
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(AppCategory.entries.toTypedArray()) { category ->
                    val isSelected = state.selectedCategory == category
                    Box(
                        modifier = Modifier
                            .clickable { onCategorySelect(category) }
                            .glassSurface(
                                level = if (isSelected) GlassLevel.BALANCED else GlassLevel.SUBTLE,
                                isDarkTheme = state.isDarkTheme,
                                blurEnabled = state.isBlurEnabled,
                                shape = RoundedCornerShape(20.dp)
                            )
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = category.displayName,
                            style = MaterialTheme.typography.labelMedium,
                            color = if (isSelected) MaterialTheme.colorScheme.primary else textColor.copy(alpha = 0.7f)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Filtered App List
            val displayedApps = if (state.selectedCategory == AppCategory.ALL) {
                state.installedApps
            } else {
                state.installedApps.filter { it.category == state.selectedCategory }
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(displayedApps) { app ->
                    SquircleIcon(
                        label = app.label,
                        iconDrawable = app.icon,
                        onClick = { onAppClick(app) },
                        isDarkTheme = state.isDarkTheme,
                        isBlurEnabled = state.isBlurEnabled
                    )
                }
            }
        }
    }
}
