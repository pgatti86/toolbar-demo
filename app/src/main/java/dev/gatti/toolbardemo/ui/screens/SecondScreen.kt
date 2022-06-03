package dev.gatti.toolbardemo.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dev.gatti.toolbardemo.R
import dev.gatti.toolbardemo.navigation.NavGraph
import dev.gatti.toolbardemo.ui.composables.toolbar.ToolbarController
import dev.gatti.toolbardemo.ui.composables.toolbar.rememberToolbarController
import dev.gatti.toolbardemo.ui.theme.ToolbarDemoTheme

@Composable
fun SecondScreen(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(),
    toolbarController: ToolbarController = rememberToolbarController()
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = stringResource(id = R.string.second_screen_title))

        Spacer(modifier = Modifier.padding(32.dp))

        Button(onClick = {
            navController.navigate(NavGraph.THIRD_SCREEN_ROUTE)
        }) {
            Text(text = stringResource(id = R.string.navigate_to_third_screen_label))
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SecondScreenPreview() {
    ToolbarDemoTheme {
        SecondScreen()
    }
}
