package dev.gatti.toolbardemo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import dev.gatti.toolbardemo.ui.composables.toolbar.ToolbarController
import dev.gatti.toolbardemo.ui.screens.FirstScreen
import dev.gatti.toolbardemo.ui.screens.SecondScreen
import dev.gatti.toolbardemo.ui.screens.ThirdScreen

sealed class NavGraph {

    companion object {
        const val FIRST_SCREEN_ROUTE = "firstScreen"
        const val SECOND_SCREEN_ROUTE = "secondScreen"
        const val THIRD_SCREEN_ROUTE = "thirdScreen"

        const val startDestination = FIRST_SCREEN_ROUTE
        val destinations = listOf(FirstScreen, SecondScreen, ThirdScreen)
    }

    abstract val route: String

    abstract val content: @Composable (
        modifier: Modifier,
        navController: NavController,
        toolbarController: ToolbarController,
        backStackEntry: NavBackStackEntry
    ) -> Unit

    object FirstScreen : NavGraph() {

        override val route: String
            get() = FIRST_SCREEN_ROUTE

        override val content: @Composable (Modifier, NavController, ToolbarController, NavBackStackEntry) -> Unit
            get() = { modifier, navController, toolbarController, backStackEntry ->
                FirstScreen(
                    modifier = modifier,
                    navController = navController,
                    toolbarController = toolbarController
                )
            }
    }

    object SecondScreen : NavGraph() {

        override val route: String
            get() = SECOND_SCREEN_ROUTE

        override val content: @Composable (Modifier, NavController, ToolbarController, NavBackStackEntry) -> Unit
            get() = { modifier, navController, toolbarController, backStackEntry ->
                SecondScreen(
                    modifier = modifier,
                    navController = navController,
                    toolbarController = toolbarController
                )
            }
    }

    object ThirdScreen : NavGraph() {

        override val route: String
            get() = THIRD_SCREEN_ROUTE

        override val content: @Composable (Modifier, NavController, ToolbarController, NavBackStackEntry) -> Unit
            get() = { modifier, navController, toolbarController, backStackEntry ->
                ThirdScreen(
                    modifier = modifier,
                    navController = navController,
                    toolbarController = toolbarController
                )
            }
    }
}