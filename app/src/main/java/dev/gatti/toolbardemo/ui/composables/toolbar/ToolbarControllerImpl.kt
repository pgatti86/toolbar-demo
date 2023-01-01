package dev.gatti.toolbardemo.ui.composables.toolbar

import androidx.compose.runtime.*

class ToolbarControllerImpl(
    toolbarActions: Map<String, List<ToolbarAction>> = emptyMap()
) : ToolbarController {

    private val actionsMap = mutableStateMapOf<String, List<ToolbarAction>>()

    init {
        actionsMap.putAll(toolbarActions)
    }

    override fun getToolbarActions(route: String): List<ToolbarAction> {
        return actionsMap[route] ?: emptyList()
    }

    override fun setToolbarActions(route: String, actions: List<ToolbarAction>) {
        actionsMap.clear()
        actionsMap[route] = actions
    }
}

@Composable
fun rememberToolbarController(): ToolbarController = remember { ToolbarControllerImpl() }

@Composable
fun ToolbarController.SetActions(route: String, actions: List<ToolbarAction>, key: Any = Unit) {

    LaunchedEffect(key1 = key) {
        setToolbarActions(route = route, actions = actions)
    }
}