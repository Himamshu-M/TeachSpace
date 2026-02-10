package com.example.notificationbaisc.navigation


import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.notificationbaisc.screen.DetailsScreen
import com.example.notificationbaisc.screen.MainScreen
import com.example.notificationbaisc.uix.MainActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.notificationbaisc.uix.MainViewModel


const val MY_URI = "https://stevdza-san.com"
const val MY_ARG = "message"

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = Screen.Main.route
    ) {
        composable(route = Screen.Main.route) {
            MainScreen(navController = navController)
        }
        composable(
            route = Screen.Details.route,
            arguments = listOf(navArgument(MY_ARG) { type = NavType.StringType }),
            deepLinks = listOf(navDeepLink { uriPattern = "$MY_URI/$MY_ARG={$MY_ARG}" })
        ) {
            val arguments = it.arguments
            arguments?.getString(MY_ARG)?.let { message ->
                DetailsScreen(message = message)
            }
        }
    }
}


