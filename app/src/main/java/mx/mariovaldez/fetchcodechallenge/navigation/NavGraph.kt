package mx.mariovaldez.fetchcodechallenge.navigation

import android.content.Context
import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import mx.mariovaldez.fetchcodechallenge.details.DetailScreen
import mx.mariovaldez.fetchcodechallenge.home.presentation.HomeScreen
import mx.mariovaldez.fetchcodechallenge.home.presentation.models.HiringUI
import mx.mariovaldez.fetchcodechallenge.home.presentation.models.ListUI
import mx.mariovaldez.fetchcodechallenge.splash.SplashScreen

@Composable
fun NavGraph(
    context: Context,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    startDestination: String = Destinations.SPLASH_ROUTE,
    navActions: NavigationActions = remember(navController) {
        NavigationActions(navController)
    },
    gson: Gson = GsonBuilder().create()
) {

    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentNavBackStackEntry?.destination?.route ?: startDestination

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(
            route = Destinations.SPLASH_ROUTE,
        ) {
            SplashScreen(navController = navController)
        }

        composable(
            route = Destinations.HOME_ROUTE,
        ) {
            HomeScreen(navController = navController)
        }

        composable(
            route = Destinations.DETAILS_ROUTE,
        ) {
            val hiring = it.arguments?.getString("hiring")
            DetailScreen(
                values = gson.fromJson(
                    hiring, object : TypeToken<List<HiringUI>>() {}.type
                )
            )
        }
    }
}