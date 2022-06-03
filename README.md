# Jetpack compose toolbar actions demo

Before Jetpack Compose release we were used to add menu items in Fragments or Activities classes with **xml** files and **onCreateOptionsMenu** methods.

The new UI tool is a game changer and via **Scaffold** composable it's easy to add global actions to **TopAppBar**. With global actions I mean items that remain visible for the entire life cycle of the controller class. When using Jetpack Navigation however there is only one Activity and the NavHost swaps composable destinations on the screen.

In this scenario is useful to have a concise way to easily add or remove actions from the ToolBar for each navigation route. This sample project aims to resolve this problem.

Define a toolbarController instance (Hoist instance as up as possible)

```kotlin
val toolbarController = rememberToolbarController()

val screenToolbarActions by derivedStateOf {  
	val route = currentBackStackEntry?.destination?.route ?: ""  
	toolbarController.getToolbarActions(route = route)  
}

...
Scaffold( 
	topBar = {  
	  TopAppBar {  
		  ToolbarContent(
			  ...
			  screenAdditionalToolbarActions = screenToolbarActions,  
			  navController = navController,
			)
		}
	}
....
```

In composable Destination define toolbar items
```kotlin
@Composable
fun FirstScreen(
toolbarController: ToolbarController = rememberToolbarController()
) {

	toolbarController.SetActions(  
		route = NavGraph.FIRST_SCREEN_ROUTE,  
		actions = listOf(ToolbarAction.OpenSettings {  
		Toast.makeText(context, "Settings Action", Toast.LENGTH_SHORT).show()  
		})  
	)
}
```


