package com.aether.launcher.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "aether_preferences")

class HomePreferences(private val context: Context) {

    companion object {
        val DARK_THEME = booleanPreferencesKey("dark_theme")
        val BLUR_ENABLED = booleanPreferencesKey("blur_enabled")
        val BLUR_INTENSITY = floatPreferencesKey("blur_intensity")
        val GRID_COLUMNS = stringPreferencesKey("grid_columns")
        val DOCK_PACKAGES = stringPreferencesKey("dock_packages")
    }

    val darkTheme: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[DARK_THEME] ?: false
    }

    val blurEnabled: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[BLUR_ENABLED] ?: true
    }

    val blurIntensity: Flow<Float> = context.dataStore.data.map { prefs ->
        prefs[BLUR_INTENSITY] ?: 0.75f
    }

    val gridColumns: Flow<String> = context.dataStore.data.map { prefs ->
        prefs[GRID_COLUMNS] ?: "4x5"
    }

    val dockPackages: Flow<List<String>> = context.dataStore.data.map { prefs ->
        val raw = prefs[DOCK_PACKAGES] ?: ""
        if (raw.isEmpty()) emptyList() else raw.split(",")
    }

    suspend fun setDarkTheme(enabled: Boolean) {
        context.dataStore.edit { prefs -> prefs[DARK_THEME] = enabled }
    }

    suspend fun setBlurEnabled(enabled: Boolean) {
        context.dataStore.edit { prefs -> prefs[BLUR_ENABLED] = enabled }
    }

    suspend fun setBlurIntensity(intensity: Float) {
        context.dataStore.edit { prefs -> prefs[BLUR_INTENSITY] = intensity }
    }

    suspend fun setGridColumns(grid: String) {
        context.dataStore.edit { prefs -> prefs[GRID_COLUMNS] = grid }
    }

    suspend fun setDockPackages(packages: List<String>) {
        context.dataStore.edit { prefs -> prefs[DOCK_PACKAGES] = packages.joinToString(",") }
    }
}
