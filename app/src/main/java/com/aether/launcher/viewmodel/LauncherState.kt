package com.aether.launcher.viewmodel

import com.aether.launcher.data.model.AppCategory
import com.aether.launcher.data.model.AppItem
import com.aether.launcher.data.model.FolderItem

enum class LauncherScreen {
    HOME,
    APP_DRAWER,
    SEARCH_OVERLAY,
    EDIT_MODE
}

data class LauncherState(
    val currentScreen: LauncherScreen = LauncherScreen.HOME,
    val installedApps: List<AppItem> = emptyList(),
    val dockApps: List<AppItem> = emptyList(),
    val homeGridApps: List<AppItem> = emptyList(),
    val folders: List<FolderItem> = emptyList(),
    val activeFolder: FolderItem? = null,
    val isCustomizationSheetOpen: Boolean = false,
    val isWidgetSheetOpen: Boolean = false,
    val searchQuery: String = "",
    val searchResults: List<AppItem> = emptyList(),
    val selectedCategory: AppCategory = AppCategory.ALL,
    val isDarkTheme: Boolean = false,
    val isBlurEnabled: Boolean = true,
    val blurIntensity: Float = 0.75f,
    val gridColumns: String = "4x5"
)
