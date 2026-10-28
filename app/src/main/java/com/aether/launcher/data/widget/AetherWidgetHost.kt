package com.aether.launcher.data.widget

import android.appwidget.AppWidgetHost
import android.appwidget.AppWidgetHostView
import android.appwidget.AppWidgetProviderInfo
import android.content.Context

class AetherWidgetHost(
    context: Context,
    hostId: Int
) : AppWidgetHost(context, hostId) {

    override fun onCreateView(
        context: Context,
        appWidgetId: Int,
        appWidget: AppWidgetProviderInfo?
    ): AppWidgetHostView {
        return AetherWidgetHostView(context)
    }

    companion object {
        const val HOST_ID = 1024
    }
}

class AetherWidgetHostView(context: Context) : AppWidgetHostView(context)
