package com.ram.buspass.features.userNavigationBar


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AirplaneTicket
import androidx.compose.material.icons.filled.BookmarkAdd
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector



sealed class UserScreen(val route: String, val title: String?, val icon: ImageVector?) {
    object Book : UserScreen("book", "Book", Icons.Default.BookmarkAdd)
    object View : UserScreen("view", "View", Icons.Default.AirplaneTicket)
    object Location : UserScreen("location", null, null)
    object Payment : UserScreen("payment", "Payment", Icons.Default.Money)
    object Profile : UserScreen("profile", "Profile", Icons.Default.Person)
}




sealed class ScreenList(val route: String) {
    object SplashScreen : ScreenList("SplashScreen")
    object LoginScreen : ScreenList("LoginScreen")
    object RegisterScreen : ScreenList("RegisterScreen")
    object ForgotScreen : ScreenList("ForgotScreen")
    object LogoutScreen : ScreenList("LogoutScreen")
    object BottomNavMenuUser : ScreenList("BottomNavMenuUser")
    object BottomNavMenuConductor : ScreenList("BottomNavMenuConductor")
    object Maps : ScreenList("Maps")



}

