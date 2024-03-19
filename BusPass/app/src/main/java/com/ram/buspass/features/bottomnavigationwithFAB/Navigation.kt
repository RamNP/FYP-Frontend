package com.ram.buspass.features.bottomnavigationwithFAB

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.SafetyCheck
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Subscriptions
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ScreenItem(val route: String, val title: String, val icons: ImageVector) {
    object Home : ScreenItem("home ", "Home", Icons.Default.Home)
    object Subject : ScreenItem("subject ", "Subject", Icons.Default.Subscriptions)
    object Classroom : ScreenItem("classroom ", "null" , Icons.Default.SafetyCheck)
    object Profile : ScreenItem("profile ", "Profile", Icons.Default.Person)
    object Setting : ScreenItem("setting ", "Setting", (Icons.Default.Settings))


}

//sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
//    object Home : BottomNavItem("home", Icons.Default.Home, "Home")
//    object Search : BottomNavItem("search", Icons.Default.Search, "Search")
//    object Profile : BottomNavItem("profile", Icons.Default.Person, "Profile")
//}

