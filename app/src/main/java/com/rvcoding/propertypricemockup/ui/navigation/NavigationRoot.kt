package com.rvcoding.propertypricemockup.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.rvcoding.propertypricemockup.domain.navigation.Destination
import com.rvcoding.propertypricemockup.ui.navigation.core.NavigationAction
import com.rvcoding.propertypricemockup.ui.navigation.core.ObserveAsEvents
import com.rvcoding.propertypricemockup.ui.screen.propertydetails.PropertyDetailsScreenRoot
import com.rvcoding.propertypricemockup.ui.screen.yourproperties.YourPropertiesScreenRoot

@Composable
fun NavigationRoot(
    vm: NavigationViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {
    val navigator = vm.navigator()

    ObserveAsEvents(
        dispatchersProvider = vm.dispatchersProvider(),
        flow = navigator.navigationActions
    ) { action ->
        when (action) {
            is NavigationAction.Navigate -> navController.navigate(
                action.destination
            ) {
                action.navOptions(this)
            }

            NavigationAction.NavigateUp -> navController.navigateUp()
        }
    }

    NavigationScreen(
        navController = navController,
        startDestination = navigator.startDestination
    )
}

@Composable
fun NavigationScreen(
    navController: NavHostController = rememberNavController(),
    startDestination: Destination = Destination.YourProperties
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable<Destination.YourProperties> {
            YourPropertiesScreenRoot()
        }
        composable<Destination.PropertyDetails> {
            val id = it.toRoute<Destination.PropertyDetails>().id
            PropertyDetailsScreenRoot(id = id)
        }
    }
}