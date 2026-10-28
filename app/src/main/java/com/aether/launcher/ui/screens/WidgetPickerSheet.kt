package com.aether.launcher.ui.screens

import android.appwidget.AppWidgetProviderInfo
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Widgets
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.aether.launcher.glass.GlassLevel
import com.aether.launcher.glass.glassSurface

@Composable
fun WidgetPickerSheet(
    providers: List<AppWidgetProviderInfo>,
    isDarkTheme: Boolean,
    isBlurEnabled: Boolean,
    onProviderSelect: (AppWidgetProviderInfo) -> Unit,
    onClose: () -> Unit
) {
    val textColor = if (isDarkTheme) Color.White else Color(0xFF191C1E)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(520.dp)
            .glassSurface(
                level = GlassLevel.STRONG,
                isDarkTheme = isDarkTheme,
                blurEnabled = isBlurEnabled,
                shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
            )
            .padding(24.dp)
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Widgets & Glanceables",
                    style = MaterialTheme.typography.headlineMedium,
                    color = textColor,
                    modifier = Modifier.weight(1f)
                )

                Icon(
                    imageVector = Icons.Rounded.Close,
                    contentDescription = "Close",
                    tint = textColor,
                    modifier = Modifier.clickable(onClick = onClose)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(providers) { provider ->
                    val label = provider.loadLabel(androidx.compose.ui.platform.LocalContext.current.packageManager)
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp)
                            .glassSurface(
                                level = GlassLevel.SUBTLE,
                                isDarkTheme = isDarkTheme,
                                blurEnabled = isBlurEnabled,
                                shape = RoundedCornerShape(16.dp)
                            )
                            .clickable { onProviderSelect(provider) }
                            .padding(12.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Widgets,
                            contentDescription = "Widget",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(32.dp)
                        )
                        Spacer(modifier = Modifier.padding(start = 12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = label,
                                style = MaterialTheme.typography.bodyLarge,
                                color = textColor
                            )
                            Text(
                                text = provider.provider.packageName,
                                style = MaterialTheme.typography.labelSmall,
                                color = textColor.copy(alpha = 0.6f)
                            )
                        }
                        Button(
                            onClick = { onProviderSelect(provider) },
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Icon(Icons.Rounded.Add, contentDescription = "Add")
                            Text("Add")
                        }
                    }
                }
            }
        }
    }
}
