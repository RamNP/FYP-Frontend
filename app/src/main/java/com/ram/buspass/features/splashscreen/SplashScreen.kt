package com.ram.eventflow.features.splashscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.ram.buspass.R
import com.ram.buspass.features.userNavigationBar.ScreenList

@Composable
fun SplashViewScreen(navHostController: NavHostController, getUserDevice: String?) {
    LaunchedEffect(key1 = true) {
        if (getUserDevice.isNullOrEmpty()) {
            navHostController.navigate(ScreenList.LoginScreen.route)
        } else {
            navHostController.navigate(ScreenList.BottomNavMenuUser.route)
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(painter = painterResource(id = R.mipmap.ic_logo), contentDescription = null)
    }
}