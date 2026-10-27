package com.aether.launcher.data.model

import android.graphics.drawable.Drawable

data class AppItem(
    val packageName: String,
    val className: String,
    val label: String,
    val icon: Drawable? = null,
    val category: AppCategory = AppCategory.ALL
)

enum class AppCategory(val displayName: String) {
    ALL("All"),
    SOCIAL("Social"),
    PRODUCTIVITY("Productivity"),
    MEDIA("Media"),
    UTILITIES("Utilities")
}
