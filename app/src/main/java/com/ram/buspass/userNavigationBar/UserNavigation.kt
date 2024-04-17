package com.ram.buspass.userNavigationBar


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AirplaneTicket
import androidx.compose.material.icons.filled.BookmarkAdd
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector



sealed class UserScreen(val route: String, val title: String?, val icon: ImageVector?) {
    object Book : UserScreen("book", "Book", Icons.Default.BookmarkAdd)
    object MyTicket : UserScreen("myTicket", "MyTicket", Icons.Default.AirplaneTicket)
    object Location : UserScreen("location", "Location", Icons.Default.LocationOn)
    object Profile : UserScreen("profile", "Profile", Icons.Default.Person)

}




sealed class ScreenList(val route: String) {
    object LoginScreen : ScreenList("LoginScreen")
    object RegisterScreen : ScreenList("RegisterScreen")
    object ForgotScreen : ScreenList("ForgotScreen")
    object BottomNavMenuUser : ScreenList("BottomNavMenuUser")
    object BottomNavMenuConductor : ScreenList("BottomNavMenuConductor")
    object EditProfile : ScreenList("EditProfile")
    object ChangePassword : ScreenList("ChangePassword")
    object GoogleMapsScreen : ScreenList("GoogleMapsScreen/{bus_number}")
    object ShowGoogleMapsScreen : ScreenList("ShowGoogleMapsScreen")




}
