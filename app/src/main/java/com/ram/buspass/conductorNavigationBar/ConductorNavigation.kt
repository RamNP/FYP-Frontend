package com.ram.buspass.conductorNavigationBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Class
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector


sealed class ConductorNavigation(val route: String, val title: String?, val icons: ImageVector?) {
    object VerifyTicket  : ConductorNavigation("verifyTicket", "VerifyTicket", Icons.Default.Home)
    object UpdateGeo : ConductorNavigation("updateGeo", "UpdateGeo", Icons.Default.LocationOn)
    object VerifyPass  : ConductorNavigation("verifyPass" ,"VerifyPass", Icons.Default.Class)

    object Profile  : ConductorNavigation("profile ", "Profile", Icons.Default.Person)


}


