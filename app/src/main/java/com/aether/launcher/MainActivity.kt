package com.aether.launcher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.aether.launcher.ui.screens.AppDrawerScreen
import com.aether.launcher.ui.screens.CustomizationSheet
import com.aether.launcher.ui.screens.FolderModal
import com.aether.launcher.ui.screens.HomeScreen
import com.aether.launcher.ui.screens.SearchOverlay
import com.aether.launcher.ui.theme.AetherLauncherTheme
import com.aether.launcher.viewmodel.LauncherScreen
import com.aether.launcher.viewmodel.LauncherViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: LauncherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val state by viewModel.uiState.collectAsState()

            AetherLauncherTheme(darkTheme = state.isDarkTheme) {
                Box(modifier = Modifier.fillMaxSize()) {
                    // Base Home Screen
                    HomeScreen(
                        state = state,
                        onAppClick = { viewModel.launchApp(it) },
                        onAppDrawerClick = { viewModel.openScreen(LauncherScreen.APP_DRAWER) },
                        onSearchClick = { viewModel.openScreen(LauncherScreen.SEARCH_OVERLAY) },
                        onFolderClick = { viewModel.openFolder(it) },
                        onLongPressEdit = { viewModel.openScreen(LauncherScreen.EDIT_MODE) }
                    )

                    // App Drawer Overlay
                    if (state.currentScreen == LauncherScreen.APP_DRAWER) {
                        AppDrawerScreen(
                            state = state,
                            onAppClick = {
                                viewModel.launchApp(it)
                                viewModel.openScreen(LauncherScreen.HOME)
                            },
                            onCategorySelect = { viewModel.onCategorySelected(it) },
                            onCloseDrawer = { viewModel.openScreen(LauncherScreen.HOME) }
                        )
                    }

                    // App Search Overlay
                    if (state.currentScreen == LauncherScreen.SEARCH_OVERLAY) {
                        SearchOverlay(
                            state = state,
                            onQueryChange = { viewModel.onSearchQueryChanged(it) },
                            onAppClick = {
                                viewModel.launchApp(it)
                                viewModel.openScreen(LauncherScreen.HOME)
                            },
                            onClose = { viewModel.openScreen(LauncherScreen.HOME) }
                        )
                    }

                    // Active Folder Modal
                    state.activeFolder?.let { folder ->
                        FolderModal(
                            folder = folder,
                            installedApps = state.installedApps,
                            isDarkTheme = state.isDarkTheme,
                            isBlurEnabled = state.isBlurEnabled,
                            onAppClick = { viewModel.launchApp(it) },
                            onTitleChange = { viewModel.updateFolderTitle(folder.id, it) },
                            onDismiss = { viewModel.closeFolder() }
                        )
                    }

                    // Customization Sheet Overlay
                    if (state.isCustomizationSheetOpen) {
                        Box(
                            contentAlignment = Alignment.BottomCenter,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            CustomizationSheet(
                                state = state,
                                onDarkThemeToggle = { viewModel.setDarkTheme(it) },
                                onBlurToggle = { viewModel.setBlurEnabled(it) },
                                onBlurIntensityChange = { viewModel.setBlurIntensity(it) },
                                onClose = { viewModel.toggleCustomizationSheet(false) }
                            )
                        }
                    }
                }
            }
        }
    }

    override fun onBackPressed() {
        if (viewModel.uiState.value.currentScreen != LauncherScreen.HOME) {
            viewModel.openScreen(LauncherScreen.HOME)
        } else if (viewModel.uiState.value.activeFolder != null) {
            viewModel.closeFolder()
        } else {
            super.onBackPressed()
        }
    }
}
