package com.ram.buspass.userNavigationBar

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ram.buspass.features.bookingDetails.presentation.BookTicketDetailsViewScreen
import com.ram.buspass.features.chanagePassword.presentation.ChangePasswordScreen
import com.ram.buspass.features.editProfile.presentation.EditProfileViewScreen
import com.ram.buspass.features.locationView.presenation.LocationViewScreen
import com.ram.buspass.features.profile.presentation.ProfileViewScreen
import com.ram.buspass.features.ticketBook.presentation.TicketBookViewScreens
import com.ram.buspass.features.updateBusLocation.presentation.UpdateBusLocationViewScreen


@Composable
fun UserMainScreenNavigation(navController: NavHostController, maiNavController: NavHostController) {

    NavHost(navController, startDestination = UserScreen.Book.route) {
        composable(UserScreen.Book.route) {
            TicketBookViewScreens(navController)
        }
        composable(UserScreen.MyTicket.route) {
            BookTicketDetailsViewScreen(navController)

        }
        composable(UserScreen.Location.route) {
            LocationViewScreen(navController)
        }

        composable(UserScreen.Payment.route) {

        }
        composable(UserScreen.Profile.route) {
            ProfileViewScreen(navController, maiNavController)
        }

        composable(ScreenList.EditProfile.route) {
            EditProfileViewScreen(navController)
        }
        composable(ScreenList.ChangePassword.route) {
            ChangePasswordScreen(navController)
        }


        composable(ScreenList.GoogleMapsScreen.route){
            UpdateBusLocationViewScreen(navController)
        }

    }

}