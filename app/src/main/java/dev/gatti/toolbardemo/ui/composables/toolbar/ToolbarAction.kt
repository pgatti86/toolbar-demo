package dev.gatti.toolbardemo.ui.composables.toolbar

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import dev.gatti.toolbardemo.R

sealed class ToolbarAction(
    val imageVector: ImageVector,
    @StringRes val description: Int,
    val onAction: () -> Unit
) {

    data class Notifications(val action: () -> Unit) : ToolbarAction(
        imageVector = Icons.Filled.Notifications,
        description = R.string.toolbarActions_notifications_label,
        onAction = action
    )

    data class RefreshData(val action: () -> Unit) : ToolbarAction(
        imageVector = Icons.Filled.Refresh,
        description = R.string.toolbarActions_refresh_label,
        onAction = action
    )

    data class OpenSettings(val action: () -> Unit) : ToolbarAction(
        imageVector = Icons.Filled.Settings,
        description = R.string.toolbarActions_settings_label,
        onAction = action
    )
}