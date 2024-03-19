package com.ram.buspass.features.bottomnavigationwithFAB

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ram.buspass.features.login.presentation.LoginViewScreen

//
//@Composable
//fun MainScreenNavigation(navController: NavHostController) {
//
//    NavHost(navController, startDestination = ScreenList.LoginScreen.route) {
//        composable(ScreenList.LoginScreen.route) {
//            LoginViewScreen(navController)
//        }
//        composable(ScreenList.RegisterScreen.route){
//            RegisterViewScreen(navController)
//
//        }
//        composable(ScreenList.SplashScreen.route){
//
//        }
//         composable(ScreenList.BottomNavMenuUser.route){
//            BottomBarWithFabViewScreen(navController)
//
//         }
//
//    }
//}


@Composable
fun ButtonNavigationView(navController: NavHostController) {

    NavHost(navController, startDestination = ScreenItem.Home.route) {
        composable(ScreenItem.Home.route) {
            HomeScreen()

        }

        composable(ScreenItem.Subject.route) {
            SubjectScreen()
        }
        composable(ScreenItem.Classroom.route) {
            ClassroomScreen()
        }
        composable(ScreenItem.Setting.route) {
            SettingScreen()
        }
        composable(ScreenItem.Profile.route) {
            ProfileScreen()
        }
    }
}

