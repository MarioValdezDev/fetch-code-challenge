package mx.mariovaldez.fetchcodechallenge.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import mx.mariovaldez.fetchcodechallenge.home.presentation.models.HiringUI
import mx.mariovaldez.fetchcodechallenge.navigation.Screens.DETAILS
import mx.mariovaldez.fetchcodechallenge.navigation.Screens.HOME
import mx.mariovaldez.fetchcodechallenge.navigation.Screens.SPLASH

private object Screens {
    const val SPLASH = "splash"
    const val HOME = "home"
    const val DETAILS = "detail?hiring={hiring}"
}

object Destinations {
    const val SPLASH_ROUTE = SPLASH
    const val HOME_ROUTE = HOME
    const val DETAILS_ROUTE = DETAILS
}

sealed class DestinationScreen(val route: String) {
    object SplashScreen : DestinationScreen(SPLASH)
    object HomeScreen : DestinationScreen(HOME)
    object DetailsScreen : DestinationScreen(DETAILS)
}

class NavigationActions(
    val navController: NavController,
    private val gson: Gson = GsonBuilder().create()
) {

    fun navigateToHome() {
        navController.navigate(route = DestinationScreen.HomeScreen.route) {
            navController.popBackStack()
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
        }
    }

    fun navigateToDetails(hiringUI: List<HiringUI>) {
        println(
            DestinationScreen.DetailsScreen.route.replace(
                "{hiring}",
                gson.toJson(hiringUI)
            )
        )
        navController.navigate(
            route = DestinationScreen.DetailsScreen.route.replace(
                "{hiring}",
                gson.toJson(hiringUI)
            )
        ) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
        }
    }
}
