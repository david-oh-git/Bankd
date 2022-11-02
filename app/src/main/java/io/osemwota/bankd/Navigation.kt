package io.osemwota.bankd

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
            LoginScreen(
                { navController.navigate(Destinations.HOME.name) }
            )
        }

        composable(Destinations.HOME.name) {}
    }
}