package io.osemwota.bankd.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import io.osemwota.bankd.ui.login.LoginScreen

@Composable
fun BankdNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Destinations.LOGIN.name
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {

        composable(Destinations.LOGIN.name) {
            LoginScreen(navController)
        }

        composable(
            Destinations.HOME.toString(),
            arguments = listOf( navArgument("customerId") { type = NavType.StringType } )
        ) {

        }
    }
}