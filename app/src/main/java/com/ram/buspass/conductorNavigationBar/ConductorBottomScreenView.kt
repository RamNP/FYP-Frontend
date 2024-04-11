package com.ram.buspass.conductorNavigationBar

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ram.buspass.features.editProfile.presentation.EditProfileViewScreen
import com.ram.buspass.features.profile.presentation.ProfileViewScreen
import com.ram.buspass.features.updateBusLocation.presentation.BusLocationViewScreen
import com.ram.buspass.features.verifyTicket.presentation.VerifyTicketViewScreen
import com.ram.buspass.userNavigationBar.ScreenList


@Composable
fun ConductorMainScreenNavigation(navController: NavHostController) {

    NavHost(navController, startDestination = ConductorNavigation.VerifyTicket.route) {
        composable(ConductorNavigation.VerifyTicket.route) {
//            TicketBookViewScreen(navController)
//            BusLocationViewScreen(navController)
            VerifyTicketViewScreen(navController)
        }
        composable(ConductorNavigation.UpdateLocation.route) {
            BusLocationViewScreen(navController)

        }
        composable(ConductorNavigation.VerifyPass.route) {
            ProfileViewScreen(navController)
        }

        composable(ScreenList.EditProfile.route) {
            EditProfileViewScreen(navController)
        }

    }

}