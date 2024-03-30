package com.ram.buspass.features.conductorNavigationBar

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ram.buspass.features.conductorNavBarScreen.UpdateBusLocationScreen


@Composable
fun ConductorMainScreenNavigation(navController: NavHostController) {

    NavHost(navController, startDestination = ConductorNavigation.VerifyTicket.route) {
        composable(ConductorNavigation.VerifyTicket.route) {
//            TicketBookViewScreen(navController)
        }
        composable(ConductorNavigation.UpdateLocation.route) {
            UpdateBusLocationScreen()
        }
        composable(ConductorNavigation.VerifyPass.route) {
        }

    }

}