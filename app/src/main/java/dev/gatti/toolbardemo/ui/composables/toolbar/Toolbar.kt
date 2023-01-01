package dev.gatti.toolbardemo.ui.composables.toolbar

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun ToolbarContent(
    modifier: Modifier = Modifier,
    globalToolbarActions: List<ToolbarAction> = listOf(),
    screenAdditionalToolbarActions: List<ToolbarAction> = listOf(),
    navController: NavController = rememberNavController(),
    onBackPressed: () -> Unit = { }
) {

    val currentBackStackEntry by navController.currentBackStackEntryAsState()

    val showBackButton by remember(currentBackStackEntry) {
        derivedStateOf {
            navController.previousBackStackEntry != null
        }
    }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {

        if (showBackButton) {
            IconButton(onClick = { onBackPressed() }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back button")
            }
        }

        val textPadding = if (showBackButton) 0.dp else 8.dp

        Text(
            modifier = Modifier.padding(start = textPadding),
            textAlign = TextAlign.Center,
            text = currentBackStackEntry?.destination?.route ?: "",
            style = MaterialTheme.typography.h6
        )

        Spacer(modifier = Modifier.weight(0.1f))

        screenAdditionalToolbarActions.forEach { screenAction ->
            IconButton(onClick = { screenAction.onAction() }) {
                Icon(imageVector = screenAction.imageVector, contentDescription = "")
            }
        }

        globalToolbarActions.forEach { globalAction ->
            IconButton(onClick = { globalAction.onAction() }) {
                Icon(imageVector = globalAction.imageVector, contentDescription = "")
            }
        }
    }
}