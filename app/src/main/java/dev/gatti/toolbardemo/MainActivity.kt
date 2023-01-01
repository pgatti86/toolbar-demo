package dev.gatti.toolbardemo

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.gatti.toolbardemo.navigation.NavGraph
import dev.gatti.toolbardemo.ui.composables.toolbar.ToolbarAction
import dev.gatti.toolbardemo.ui.composables.toolbar.ToolbarContent
import dev.gatti.toolbardemo.ui.composables.toolbar.rememberToolbarController
import dev.gatti.toolbardemo.ui.theme.ToolbarDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController: NavHostController = rememberNavController()
            MainContent(navController)
        }
    }
}

@Composable
fun MainContent(navController: NavHostController = rememberNavController()) {

    val context = LocalContext.current

    val currentBackStackEntry by navController.currentBackStackEntryAsState()

    val toolbarController = rememberToolbarController()
    val screenToolbarActions by remember {
        derivedStateOf {
            val route = currentBackStackEntry?.destination?.route ?: ""
            toolbarController.getToolbarActions(route = route)
        }
    }

    val globalActions = remember {
        listOf(ToolbarAction.Notifications {
            Toast.makeText(context, "Notifications", Toast.LENGTH_SHORT).show()
        })
    }

    ToolbarDemoTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar {
                    ToolbarContent(
                        modifier = Modifier.fillMaxWidth(),
                        globalToolbarActions = globalActions,
                        screenAdditionalToolbarActions = screenToolbarActions,
                        navController = navController,
                        onBackPressed = {
                            navController.popBackStack()
                        }
                    )
                }
            }
        ) { scaffoldPadding ->
            NavHost(
                navController = navController,
                startDestination = NavGraph.startDestination
            ) {
                NavGraph.destinations.forEach { destination ->
                    composable(destination.route) { backStackEntry ->
                        destination.content(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(scaffoldPadding),
                            navController = navController,
                            toolbarController = toolbarController,
                            backStackEntry = backStackEntry
                        )
                    }
                }
            }
        }
    }
}