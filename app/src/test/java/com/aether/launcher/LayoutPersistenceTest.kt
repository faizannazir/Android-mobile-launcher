package com.aether.launcher

import com.aether.launcher.data.LayoutPersistence
import com.aether.launcher.data.model.HomeLayoutItem
import com.aether.launcher.data.model.PageData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import androidx.test.core.app.ApplicationProvider

@RunWith(RobolectricTestRunner::class)
class LayoutPersistenceTest {

    private lateinit var persistence: LayoutPersistence

    @Before
    fun setUp() {
        persistence = LayoutPersistence(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun `serialize and deserialize pages with App, Folder, and Widget items`() {
        val originalPages = listOf(
            PageData(
                pageIndex = 0,
                items = listOf(
                    HomeLayoutItem.App("app_1", 0, "com.spotify.music", "MainActivity"),
                    HomeLayoutItem.Folder("folder_1", 1, "Social Apps", listOf("com.instagram.android", "com.twitter.android")),
                    HomeLayoutItem.Widget("widget_1", 2, 101, "com.google.android.deskclock", "ClockWidgetProvider", 2, 2)
                )
            )
        )

        val jsonStr = persistence.serializePages(originalPages)
        assertTrue(jsonStr.isNotEmpty())

        val deserialized = persistence.deserializePages(jsonStr)
        assertEquals(1, deserialized.size)
        assertEquals(0, deserialized[0].pageIndex)

        val items = deserialized[0].items
        assertEquals(3, items.size)

        val appItem = items[0] as HomeLayoutItem.App
        assertEquals("app_1", appItem.id)
        assertEquals("com.spotify.music", appItem.packageName)

        val folderItem = items[1] as HomeLayoutItem.Folder
        assertEquals("folder_1", folderItem.id)
        assertEquals("Social Apps", folderItem.title)
        assertEquals(2, folderItem.appPackages.size)

        val widgetItem = items[2] as HomeLayoutItem.Widget
        assertEquals("widget_1", widgetItem.id)
        assertEquals(101, widgetItem.appWidgetId)
    }
}
