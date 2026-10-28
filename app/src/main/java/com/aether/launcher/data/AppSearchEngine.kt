package com.aether.launcher.data

import com.aether.launcher.data.model.AppItem

class AppSearchEngine {

    fun search(query: String, apps: List<AppItem>): List<AppItem> {
        val trimmed = query.trim().lowercase()
        if (trimmed.isEmpty()) return emptyList()

        return apps.filter { app ->
            val label = app.label.lowercase()
            label.startsWith(trimmed) || label.contains(trimmed) || app.packageName.lowercase().contains(trimmed)
        }.sortedWith(
            compareBy<AppItem> { !it.label.lowercase().startsWith(trimmed) }
                .thenBy { it.label.lowercase() }
        )
    }
}
