package com.aether.launcher.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.aether.launcher.data.AppSearchEngine
import com.aether.launcher.data.HomePreferences
import com.aether.launcher.data.InstalledAppsRepository
import com.aether.launcher.data.model.AppCategory
import com.aether.launcher.data.model.AppItem
import com.aether.launcher.data.model.FolderItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LauncherViewModel(
    application: Application,
    private val savedStateHandle: SavedStateHandle
) : AndroidViewModel(application) {

    private val repository = InstalledAppsRepository(application)
    private val preferences = HomePreferences(application)
    private val searchEngine = AppSearchEngine()

    private val _uiState = MutableStateFlow(LauncherState())
    val uiState: StateFlow<LauncherState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.loadInstalledApps()
        }

        viewModelScope.launch {
            repository.installedApps.collectLatest { apps ->
                val defaultDock = apps.take(5)
                val defaultHomeGrid = apps.drop(5).take(8)

                _uiState.update { current ->
                    current.copy(
                        installedApps = apps,
                        dockApps = if (current.dockApps.isEmpty()) defaultDock else current.dockApps,
                        homeGridApps = if (current.homeGridApps.isEmpty()) defaultHomeGrid else current.homeGridApps,
                        folders = if (current.folders.isEmpty()) {
                            listOf(
                                FolderItem(
                                    id = "folder_1",
                                    title = "Social & Media",
                                    appPackages = apps.take(6).map { it.packageName }
                                )
                            )
                        } else current.folders
                    )
                }
            }
        }

        viewModelScope.launch {
            preferences.darkTheme.collectLatest { dark ->
                _uiState.update { it.copy(isDarkTheme = dark) }
            }
        }

        viewModelScope.launch {
            preferences.blurEnabled.collectLatest { blur ->
                _uiState.update { it.copy(isBlurEnabled = blur) }
            }
        }

        viewModelScope.launch {
            preferences.blurIntensity.collectLatest { intensity ->
                _uiState.update { it.copy(blurIntensity = intensity) }
            }
        }

        viewModelScope.launch {
            preferences.gridColumns.collectLatest { grid ->
                _uiState.update { it.copy(gridColumns = grid) }
            }
        }
    }

    fun openScreen(screen: LauncherScreen) {
        _uiState.update { it.copy(currentScreen = screen) }
    }

    fun onSearchQueryChanged(query: String) {
        val results = searchEngine.search(query, _uiState.value.installedApps)
        _uiState.update { it.copy(searchQuery = query, searchResults = results) }
    }

    fun onCategorySelected(category: AppCategory) {
        _uiState.update { it.copy(selectedCategory = category) }
    }

    fun openFolder(folder: FolderItem) {
        _uiState.update { it.copy(activeFolder = folder) }
    }

    fun closeFolder() {
        _uiState.update { it.copy(activeFolder = null) }
    }

    fun updateFolderTitle(folderId: String, newTitle: String) {
        _uiState.update { current ->
            val updated = current.folders.map {
                if (it.id == folderId) it.copy(title = newTitle) else it
            }
            current.copy(
                folders = updated,
                activeFolder = current.activeFolder?.takeIf { it.id == folderId }?.copy(title = newTitle) ?: current.activeFolder
            )
        }
    }

    fun toggleCustomizationSheet(open: Boolean) {
        _uiState.update { it.copy(isCustomizationSheetOpen = open) }
    }

    fun toggleWidgetSheet(open: Boolean) {
        _uiState.update { it.copy(isWidgetSheetOpen = open) }
    }

    fun setDarkTheme(enabled: Boolean) {
        viewModelScope.launch { preferences.setDarkTheme(enabled) }
    }

    fun setBlurEnabled(enabled: Boolean) {
        viewModelScope.launch { preferences.setBlurEnabled(enabled) }
    }

    fun setBlurIntensity(intensity: Float) {
        viewModelScope.launch { preferences.setBlurIntensity(intensity) }
    }

    fun setGridColumns(grid: String) {
        viewModelScope.launch { preferences.setGridColumns(grid) }
    }

    fun launchApp(app: AppItem) {
        repository.launchApp(app)
    }
}
