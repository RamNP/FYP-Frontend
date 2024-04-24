package com.ram.buspass.conductorNavigationBar

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ram.buspass.ui.theme.Purple
import com.ram.buspass.ui.theme.WhiteGrey


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ConductorBottomBarWithFabViewScreen(maiNavController: NavHostController) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomAppBar(
                modifier = Modifier
                    .fillMaxWidth(),
//                    .clip(RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp)),
                contentColor = Color.Green,
                cutoutShape = CircleShape,
                backgroundColor = WhiteGrey,
                elevation = 22.dp
            ) {
                ConductorBottomNavViewScreen(navController = navController)
            }
        },
    ) {
        ConductorMainScreenNavigation(navController, maiNavController)
    }
}


@SuppressLint("ResourceAsColor")
@Composable
fun ConductorBottomNavViewScreen(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    BottomNavigation(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = WhiteGrey,

        elevation = 0.dp
    ) {
        conductorItems.forEach { items ->
            BottomNavigationItem(
                icon = {
                    items.icons?.let { icon ->
                        val iconColor = if (currentRoute == items.route) {
                            Purple // Color for selected icon
                        } else {
                            Color.Black // Color for unselected icon
                        }
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            modifier = Modifier
                                .height(30.dp)
                                .width(30.dp),
                            tint = iconColor
                        )
                    }
                },
                label = {
                    items.title?.let { title ->
                        val textColor = if (currentRoute == items.route) {
                            Purple // Color for selected text
                        } else {
                            Color.Black // Color for unselected text
                        }
                        Text(
                            text = title,
                            color = textColor,
                            fontSize = 12.sp
                        )
                    }
                },
                selected = currentRoute == items.route,
                selectedContentColor = Color.Red,
                unselectedContentColor = WhiteGrey,
                onClick = {
                    items.route.let { route ->
                        navController.navigate(route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}




