package com.ram.buspass.userNavigationBar

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ram.buspass.features.bookingDetails.presentation.BookTicketDetailsViewScreen
import com.ram.buspass.features.profile.presentation.ProfileViewScreen
import com.ram.buspass.features.ticketBook.presentation.TicketBookViewScreens



@Composable
fun UserMainScreenNavigation(navController: NavHostController) {

    NavHost(navController, startDestination = UserScreen.Book.route) {
        composable(UserScreen.Book.route) {
            TicketBookViewScreens(navController)
        }
        composable(UserScreen.MyTikcet.route) {
            BookTicketDetailsViewScreen(navController)

        }
        composable(UserScreen.Location.route) {
//            LocationViewScreen(navController)
        }

        composable(UserScreen.Payment.route) {
            BookTicketDetailsViewScreen(navController)


        }
        composable(UserScreen.Profile.route) {
            ProfileViewScreen(navController)

        }

    }

}