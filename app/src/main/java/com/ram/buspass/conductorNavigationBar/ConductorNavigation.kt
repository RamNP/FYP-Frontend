package com.ram.buspass.conductorNavigationBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Class
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector


sealed class ConductorNavigation(val route: String, val title: String?, val icons: ImageVector?) {
    object VerifyTicket  : ConductorNavigation("verifyTicket", "VerifyTicket", Icons.Default.Home)
    object UpdateLocation : ConductorNavigation("updateLocation ", null, null)
    object VerifyPass  : ConductorNavigation("verifyPass ", "VerifyPass", Icons.Default.Class)


}


