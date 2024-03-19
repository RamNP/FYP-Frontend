package com.ram.buspass.features.conductorNavigationBar

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ram.buspass.features.ticketBook.presentation.TicketBookViewScreen
import com.ram.buspass.googleMap.presentation.GoogleMapViewScreen


@Composable
fun ConductorMainScreenNavigation(navController: NavHostController) {

    NavHost(navController, startDestination = ConductorNavigation.VerifyTicket.route) {
        composable(ConductorNavigation.VerifyTicket.route) {
            TicketBookViewScreen(navController)
        }
        composable(ConductorNavigation.UpdateLocation.route) {
            GoogleMapViewScreen()
        }
        composable(ConductorNavigation.VerifyPass.route) {
        }

    }

}