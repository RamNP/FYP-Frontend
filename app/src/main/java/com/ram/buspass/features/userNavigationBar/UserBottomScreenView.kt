package com.ram.buspass.features.userNavigationBar

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ram.buspass.features.bottomnavigationwithFAB.ProfileScreen
import com.ram.buspass.features.ticketBook.presentation.TicketBookViewScreens



@Composable
fun UserMainScreenNavigation(navController: NavHostController) {

    NavHost(navController, startDestination = UserScreen.Book.route) {
        composable(UserScreen.Book.route) {
            TicketBookViewScreens()
        }
        composable(UserScreen.MyTikcet.route) {
//            ViewTicketScreen(navController)

        }
        composable(UserScreen.Location.route) {
//            LocationViewScreen(navController)
        }

        composable(UserScreen.Payment.route) {

        }
        composable(UserScreen.Profile.route) {
          ProfileScreen()

        }

    }

}