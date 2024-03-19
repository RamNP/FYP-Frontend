package com.ram.buspass.features.bottomnavigationwithFAB

import android.annotation.SuppressLint
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainNavViewScreen(navController: NavHostController) {
    val items = listOf(
        ScreenItem.Home,
        ScreenItem.Subject,
        ScreenItem.Classroom,
        ScreenItem.Profile,
        ScreenItem.Setting

    )
    Scaffold(topBar = {
    }, bottomBar = {
        BottomNavigation(backgroundColor = MaterialTheme.colorScheme.onError) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            items.forEach { item ->
                BottomNavigationItem(selected = currentRoute == item.route,
                    label = {
                        Text(
                            text = item.title  ,fontSize = 10.sp,
                            color = if (currentRoute == item.route) Color.Blue else Color.Black
                        )
                    }, icon = {
//                              Icon(painter = painterResource(item.imageVector), contentDescription = null)
                        Icon(imageVector = item.icons,  contentDescription = null  , tint = if (currentRoute == item.route) Color.Blue else Color.Black)
                    }, onClick = {
                        if (currentRoute != item.route) {
                            navController.graph.startDestinationRoute?.let {
                                navController.popBackStack(it, true)
                            }

                            navController.navigate(item.route) {
                                launchSingleTop = true
                            }

                        }
                    }
                )
            }
        }
    })
    {
        ButtonNavigationView(navController = navController)
    }
}

