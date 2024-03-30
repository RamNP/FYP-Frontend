package com.ram.buspass.features.userNavigationBar

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ram.buspass.Maps
import com.ram.buspass.features.conductorNavigationBar.ConductorBottomBarWithFabViewScreen
import com.ram.buspass.features.login.presentation.LoginViewScreen
import com.ram.buspass.features.register.presentation.RegisterViewScreen

@Composable
fun MainNavigationScreen(navController: NavHostController, ) {
//    val navController = rememberNavController()

    NavHost(navController,
        startDestination = ScreenList.LoginScreen.route
    ) {
        composable(ScreenList.LoginScreen.route) {
            LoginViewScreen(navController)
        }
        composable(ScreenList.SplashScreen.route) {
//            SplashViewScreen(navController )
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
