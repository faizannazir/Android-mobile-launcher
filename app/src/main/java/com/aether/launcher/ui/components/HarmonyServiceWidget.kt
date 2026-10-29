package com.aether.launcher.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CalendarToday
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.VolumeMute
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aether.launcher.data.model.HomeLayoutItem
import com.aether.launcher.glass.GlassLevel
import com.aether.launcher.glass.glassSurface

@Composable
fun HarmonyServiceWidget(
    widgetItem: HomeLayoutItem.ServiceWidget,
    isDarkTheme: Boolean,
    isBlurEnabled: Boolean,
    onToggleFocus: () -> Unit,
    modifier: Modifier = Modifier
) {
    val textColor = if (isDarkTheme) Color.White else Color(0xFF191C1E)

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(4.dp)
            .glassSurface(
                level = GlassLevel.BALANCED,
                isDarkTheme = isDarkTheme,
                blurEnabled = isBlurEnabled,
                shape = RoundedCornerShape(26.dp)
            )
            .padding(14.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Rounded.CalendarToday,
                        contentDescription = "Schedule",
                        tint = Color(0xFF2563EB),
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = "  SCHEDULE",
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                        color = textColor.copy(alpha = 0.8f)
                    )
                }

                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(Color(0xFF00FBFF), RoundedCornerShape(4.dp))
                )
            }

            Column {
                Text(
                    text = widgetItem.title,
                    style = MaterialTheme.typography.titleSmall.copy(fontSize = 15.sp),
                    color = textColor,
                    maxLines = 1
                )
                Text(
                    text = widgetItem.subtitle,
                    style = MaterialTheme.typography.labelSmall,
                    color = textColor.copy(alpha = 0.7f),
                    maxLines = 1
                )
            }

            // Quick Toggle Row
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .weight(1f)
                        .clickable(onClick = onToggleFocus)
                        .glassSurface(
                            level = GlassLevel.SUBTLE,
                            isDarkTheme = isDarkTheme,
                            blurEnabled = isBlurEnabled,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.CheckCircle,
                        contentDescription = "Focus",
                        tint = if (widgetItem.isFocusActive) Color(0xFF00FBFF) else textColor.copy(alpha = 0.4f),
                        modifier = Modifier.size(14.dp)
                    )
                    Text(
                        text = " Focus",
                        style = MaterialTheme.typography.labelSmall,
                        color = textColor
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .weight(1f)
                        .glassSurface(
                            level = GlassLevel.SUBTLE,
                            isDarkTheme = isDarkTheme,
                            blurEnabled = isBlurEnabled,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.VolumeMute,
                        contentDescription = "Mute",
                        tint = textColor.copy(alpha = 0.6f),
                        modifier = Modifier.size(14.dp)
                    )
                    Text(
                        text = " Mute",
                        style = MaterialTheme.typography.labelSmall,
                        color = textColor
                    )
                }
            }
        }
    }
}
