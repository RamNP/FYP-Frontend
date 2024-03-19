package com.ram.buspass.features.userNavigationBar

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ram.buspass.features.bottomnavigationwithFAB.ProfileScreen
import com.ram.buspass.features.ticketBook.presentation.TicketBookViewScreen
import com.ram.buspass.features.userNavBarScreen.LocationViewScreen
import com.ram.buspass.features.userNavBarScreen.PaymentViewScreen
import com.ram.buspass.features.userNavBarScreen.ViewTicketScreen


@Composable
fun UserMainScreenNavigation(navController: NavHostController) {

    NavHost(navController, startDestination = UserScreen.Book.route) {
        composable(UserScreen.Book.route) {
            TicketBookViewScreen(navController)
        }
        composable(UserScreen.View.route) {
            ViewTicketScreen(navController)

        }
        composable(UserScreen.Location.route) {
            LocationViewScreen(navController)
        }

        composable(UserScreen.Payment.route) {
            PaymentViewScreen()

        }
        composable(UserScreen.Profile.route) {
          ProfileScreen()

        }

    }

}