package com.ram.buspass.conductorNavigationBar

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ram.buspass.features.chanagePassword.presentation.ChangePasswordScreen
import com.ram.buspass.features.editProfile.presentation.EditProfileViewScreen
import com.ram.buspass.features.passVerify.presentation.PassVerifyViewScreen
import com.ram.buspass.features.profile.presentation.ProfileViewScreen
import com.ram.buspass.features.updateBusLocation.presentation.BusLocationViewScreen
import com.ram.buspass.features.verifyTicket.presentation.VerifyTicketViewScreen
import com.ram.buspass.userNavigationBar.ScreenList


@Composable
fun ConductorMainScreenNavigation(
    navController: NavHostController,
    maiNavController: NavHostController
) {

    NavHost(navController, startDestination = ConductorNavigation.VerifyTicket.route) {
        composable(ConductorNavigation.VerifyTicket.route) {
            VerifyTicketViewScreen(navController)
        }
        composable(ConductorNavigation.UpdateLocation.route) {
            BusLocationViewScreen(navController)

        }
        composable(ConductorNavigation.VerifyPass.route) {
            PassVerifyViewScreen(navController)
        }

        composable(ConductorNavigation.Profile.route) {
            ProfileViewScreen(navController, maiNavController)
        }

        composable(ScreenList.EditProfile.route) {
            EditProfileViewScreen(navController)
        }
        composable(ScreenList.ChangePassword.route) {
            ChangePasswordScreen(navController)
        }

    }

}