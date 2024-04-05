package com.ram.buspass

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ram.buspass.features.userNavigationBar.MainNavigationScreen
import com.ram.buspass.googleMap.presentation.GoogleMapViewScreen
import com.ram.buspass.ui.theme.BusPassTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val getSharedPreferences = getSharedPreferences("my_preferences", MODE_PRIVATE)
        val getUserDevice = getSharedPreferences.getString("login_screen", "")
        setContent {
            BusPassTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
//                    color = Purple
                ) {
                    MainNavigationScreen()
//                    UserBottomBarWithFabViewScreen()
//                      ConductorBottomBarWithFabViewScreen()
//                    Maps()
                }
            }
        }
    }
}


@Composable
fun Maps(
) {
//    MyApp()
    GoogleMapViewScreen()


}




