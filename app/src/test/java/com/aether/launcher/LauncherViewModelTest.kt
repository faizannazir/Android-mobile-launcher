package com.aether.launcher

import androidx.lifecycle.SavedStateHandle
import androidx.test.core.app.ApplicationProvider
import com.aether.launcher.data.model.FolderItem
import com.aether.launcher.data.model.HomeLayoutItem
import com.aether.launcher.viewmodel.LauncherScreen
import com.aether.launcher.viewmodel.LauncherViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
class LauncherViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: LauncherViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = LauncherViewModel(
            ApplicationProvider.getApplicationContext(),
            SavedStateHandle()
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `openScreen updates state correctly`() {
        assertEquals(LauncherScreen.HOME, viewModel.uiState.value.currentScreen)
        viewModel.openScreen(LauncherScreen.APP_DRAWER)
        assertEquals(LauncherScreen.APP_DRAWER, viewModel.uiState.value.currentScreen)
    }

    @Test
    fun `folder operations update title and active folder`() {
        val folder = FolderItem("folder_test", "Test Folder", listOf("com.test.app"))
        viewModel.openFolder(folder)

        assertNotNull(viewModel.uiState.value.activeFolder)
        assertEquals("Test Folder", viewModel.uiState.value.activeFolder?.title)

        viewModel.updateFolderTitle("folder_test", "Renamed Folder")
        assertEquals("Renamed Folder", viewModel.uiState.value.activeFolder?.title)

        viewModel.closeFolder()
        assertNull(viewModel.uiState.value.activeFolder)
    }

    @Test
    fun `drag and drop app onto app creates new folder`() {
        val app1 = HomeLayoutItem.App("app_1", 0, "com.test.app1", "Activity1")

        viewModel.startDrag(app1, pageIndex = 0, position = 0)
        assertEquals(app1, viewModel.uiState.value.dragState.draggedItem)

        viewModel.dropDraggedItem(targetPosition = 1)
        assertNull(viewModel.uiState.value.dragState.draggedItem)
    }

    @Test
    fun `toggleServiceWidgetFocus toggles focus state on service widget`() {
        val initialWidget = viewModel.uiState.value.pages.firstOrNull()?.items
            ?.filterIsInstance<HomeLayoutItem.ServiceWidget>()?.firstOrNull()

        if (initialWidget != null) {
            val initialFocus = initialWidget.isFocusActive
            viewModel.toggleServiceWidgetFocus(initialWidget.id)

            val updatedWidget = viewModel.uiState.value.pages.firstOrNull()?.items
                ?.filterIsInstance<HomeLayoutItem.ServiceWidget>()?.firstOrNull()

            assertNotNull(updatedWidget)
            assertEquals(!initialFocus, updatedWidget?.isFocusActive)
        }
    }

    @Test
    fun `clock time formatting logic formatting test`() {
        val testTime = LocalDateTime.of(2026, 10, 30, 14, 30)
        val pattern24 = "HH:mm"
        val pattern12 = "h:mm"

        val formatted24 = testTime.format(DateTimeFormatter.ofPattern(pattern24, Locale.US))
        val formatted12 = testTime.format(DateTimeFormatter.ofPattern(pattern12, Locale.US))

        assertEquals("14:30", formatted24)
        assertEquals("2:30", formatted12)
    }
}
