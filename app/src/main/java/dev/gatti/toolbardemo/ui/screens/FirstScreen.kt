package dev.gatti.toolbardemo.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dev.gatti.toolbardemo.R
import dev.gatti.toolbardemo.navigation.NavGraph
import dev.gatti.toolbardemo.ui.composables.toolbar.SetActions
import dev.gatti.toolbardemo.ui.composables.toolbar.ToolbarAction
import dev.gatti.toolbardemo.ui.composables.toolbar.ToolbarController
import dev.gatti.toolbardemo.ui.composables.toolbar.rememberToolbarController
import dev.gatti.toolbardemo.ui.theme.ToolbarDemoTheme

@Composable
fun FirstScreen(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(),
    toolbarController: ToolbarController = rememberToolbarController()
) {

    val context = LocalContext.current

    toolbarController.SetActions(
        route = NavGraph.FIRST_SCREEN_ROUTE,
        actions = listOf(ToolbarAction.OpenSettings {
            Toast.makeText(context, "Settings Action", Toast.LENGTH_SHORT).show()
        })
    )

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = stringResource(id = R.string.first_screen_title))

        Spacer(modifier = Modifier.padding(32.dp))

        Button(onClick = {
            navController.navigate(NavGraph.SECOND_SCREEN_ROUTE)
        }) {
            Text(text = stringResource(id = R.string.navigate_to_second_screen_label))
        }
    }
}

@Composable
@Preview(showBackground = true)
fun FirstScreenPreview() {
    ToolbarDemoTheme {
        FirstScreen()
    }
}

