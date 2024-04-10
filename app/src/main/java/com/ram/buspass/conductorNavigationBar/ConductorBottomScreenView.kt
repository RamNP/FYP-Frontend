package com.ram.buspass.conductorNavigationBar

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ram.buspass.features.busLocationView.presentation.VerifyTicketViewScreen
import com.ram.buspass.features.updateBusLocation.presentation.UpdateBusLocationViewScreen


@Composable
fun ConductorMainScreenNavigation(navController: NavHostController) {

    NavHost(navController, startDestination = ConductorNavigation.VerifyTicket.route) {
        composable(ConductorNavigation.VerifyTicket.route) {
//            TicketBookViewScreen(navController)
//            BusLocationViewScreen(navController)
            VerifyTicketViewScreen(navController)
        }
        composable(ConductorNavigation.UpdateLocation.route) {
            UpdateBusLocationViewScreen()
        }
        composable(ConductorNavigation.VerifyPass.route) {
        }



    }

}