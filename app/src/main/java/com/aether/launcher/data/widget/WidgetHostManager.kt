package com.aether.launcher.data.widget

import android.appwidget.AppWidgetHost
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProviderInfo
import android.content.Context
import android.os.Build
import com.aether.launcher.data.model.HomeLayoutItem

class WidgetHostManager(private val context: Context) {

    val appWidgetManager: AppWidgetManager = AppWidgetManager.getInstance(context)
    val appWidgetHost: AppWidgetHost = AetherWidgetHost(context, AetherWidgetHost.HOST_ID)

    fun startListening() {
        try {
            appWidgetHost.startListening()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun stopListening() {
        try {
            appWidgetHost.stopListening()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun allocateAppWidgetId(): Int {
        return appWidgetHost.allocateAppWidgetId()
    }

    fun deleteAppWidgetId(appWidgetId: Int) {
        appWidgetHost.deleteAppWidgetId(appWidgetId)
    }

    fun getInstalledProviders(): List<AppWidgetProviderInfo> {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            appWidgetManager.getInstalledProvidersForProfile(null)
        } else {
            appWidgetManager.installedProviders
        }
    }

    fun createWidgetLayoutItem(
        position: Int,
        appWidgetId: Int,
        info: AppWidgetProviderInfo
    ): HomeLayoutItem.Widget {
        val spanX = Math.max(1, Math.min(4, info.minWidth / 70))
        val spanY = Math.max(1, Math.min(4, info.minHeight / 70))

        return HomeLayoutItem.Widget(
            id = "widget_${appWidgetId}_${System.currentTimeMillis()}",
            position = position,
            appWidgetId = appWidgetId,
            providerPackage = info.provider.packageName,
            providerClass = info.provider.className,
            spanX = spanX,
            spanY = spanY
        )
    }
}
