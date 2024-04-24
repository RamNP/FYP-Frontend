package com.ram.buspass.userNavigationBar

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.ram.buspass.conductorNavigationBar.ConductorBottomBarWithFabViewScreen
import com.ram.buspass.features.introScreen.OnBoardingScreen
import com.ram.buspass.features.login.presentation.ForgotViewScreen
import com.ram.buspass.features.login.presentation.LoginViewScreen
import com.ram.buspass.features.register.presentation.RegisterViewScreen
import com.ram.buspass.helper.ClientInterceptor

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainNavigationScreen() {
    val navController = rememberNavController()
    val context = LocalContext.current

    val interceptor = ClientInterceptor(context)
    val token = interceptor.getToken()
    val userRole = interceptor.getUserRole()
    val appInstall = interceptor.installApp()
    NavHost(
        navController = navController,
        startDestination =
        if (appInstall.isEmpty()){
            ScreenList.OnBoardingScreen.route
        } else {
            if (token != null) {
                when (userRole) {
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
        }
    ) {
        composable(ScreenList.LoginScreen.route) {
            LoginViewScreen(navController)
        }
        composable(ScreenList.RegisterScreen.route) {
            RegisterViewScreen(navController)
        }
        composable(ScreenList.ForgotScreen.route) {
            ForgotViewScreen(navController)
        }

        composable(ScreenList.OnBoardingScreen.route) {
            OnBoardingScreen(navController)
        }

        composable(ScreenList.BottomNavMenuUser.route) {
            UserBottomBarWithFabViewScreen(navController)
        }
        composable(ScreenList.BottomNavMenuConductor.route) {
            ConductorBottomBarWithFabViewScreen(navController)
        }

    }
}
