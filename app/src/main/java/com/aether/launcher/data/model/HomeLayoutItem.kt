package com.aether.launcher.data.model

sealed class HomeLayoutItem {
    abstract val id: String
    abstract val position: Int // Grid index 0..19 (4x5 grid)

    data class App(
        override val id: String,
        override val position: Int,
        val packageName: String,
        val className: String
    ) : HomeLayoutItem()

    data class Folder(
        override val id: String,
        override val position: Int,
        val title: String,
        val appPackages: List<String>
    ) : HomeLayoutItem()

    data class Widget(
        override val id: String,
        override val position: Int,
        val appWidgetId: Int,
        val providerPackage: String,
        val providerClass: String,
        val spanX: Int = 2,
        val spanY: Int = 2
    ) : HomeLayoutItem()
}

data class PageData(
    val pageIndex: Int,
    val items: List<HomeLayoutItem> = emptyList()
)
