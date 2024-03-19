package com.ram.buspass.features.userNavigationBar

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ram.buspass.Maps
import com.ram.buspass.features.conductorNavigationBar.ConductorBottomBarWithFabViewScreen
import com.ram.buspass.features.login.presentation.LoginViewScreen
import com.ram.buspass.features.register.presentation.RegisterViewScreen
import com.ram.eventflow.features.splashscreen.SplashViewScreen

@Composable
fun MainNavigationScreen() {
    val navController = rememberNavController()

    NavHost(navController,
        startDestination = ScreenList.SplashScreen.route
    ) {
        composable(ScreenList.LoginScreen.route) {
            SplashViewScreen(navController, getUserDevice = null)
        }

        composable(ScreenList.LoginScreen.route) {
            LoginViewScreen(navController)
        }
        composable(ScreenList.RegisterScreen.route){
            RegisterViewScreen(navController)

        }
        composable(ScreenList.SplashScreen.route){

        }
        composable(ScreenList.BottomNavMenuUser.route){
            UserBottomBarWithFabViewScreen()
        }

        composable(ScreenList.BottomNavMenuConductor.route){
            ConductorBottomBarWithFabViewScreen()
        }

        composable(ScreenList.Maps.route){
            Maps()
        }

    }
}
