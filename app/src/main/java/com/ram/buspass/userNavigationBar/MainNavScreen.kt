package com.ram.buspass.userNavigationBar

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ram.buspass.Maps
import com.ram.buspass.conductorNavigationBar.ConductorBottomBarWithFabViewScreen
import com.ram.buspass.features.login.presentation.LoginViewScreen
import com.ram.buspass.features.register.presentation.RegisterViewScreen
import com.ram.buspass.helper.ClientInterceptor

@Composable
fun MainNavigationScreen() {
    val navController = rememberNavController()
    val context = LocalContext.current

    val interceptor = ClientInterceptor(context)
    val token = interceptor.getToken()
    val userRole = interceptor.getUserRole()

    NavHost(navController,
        startDestination = if(token != null){
            when (userRole){
                "user" -> {
                    ScreenList.BottomNavMenuUser.route
                }
                else -> {
                    ScreenList.BottomNavMenuConductor.route
                }
            }
        } else {
            ScreenList.LoginScreen.route
        }
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
            UserBottomBarWithFabViewScreen(navController)
        }

        composable(ScreenList.BottomNavMenuConductor.route){
            ConductorBottomBarWithFabViewScreen(navController)
        }

        composable(ScreenList.Maps.route){
            Maps()
        }

    }
}
