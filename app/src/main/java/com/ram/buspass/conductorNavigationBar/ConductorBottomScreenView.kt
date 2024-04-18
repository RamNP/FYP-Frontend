package com.ram.buspass.conductorNavigationBar

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ram.buspass.features.chanagePassword.presentation.ChangePasswordScreen
import com.ram.buspass.features.editProfile.presentation.EditProfileViewScreen
import com.ram.buspass.features.passVerify.presentation.PassVerifyViewScreen
import com.ram.buspass.features.profile.presentation.ProfileViewScreen
import com.ram.buspass.features.updateBusLocation.presentation.BusLocationViewScreen
import com.ram.buspass.features.verifyTicket.presentation.VerifyTicketViewScreen
import com.ram.buspass.testing.UpdateBusLocationGoogleMapScreenScreen
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
        composable(ConductorNavigation.UpdateGeo.route,)
        {
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


        composable(
            route = ScreenList.GoogleMapsScreen.route,
            arguments = listOf(
                navArgument( "bus_number") { type = NavType.StringType }
            )) {navBackStackEntry ->
            val bus_number = navBackStackEntry.arguments?.getString("bus_number") ?: ""
            UpdateBusLocationGoogleMapScreenScreen(bus_number,navController )


        }


    }

}