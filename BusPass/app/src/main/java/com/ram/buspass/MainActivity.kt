package com.ram.buspass

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ram.buspass.googleMap.presentation.MyApp
import com.ram.buspass.ui.theme.BusPassTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusPassTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
//                    color = Purple
                ) {
//                    MainNavigationScreen()
//                    UserBottomBarWithFabViewScreen()
                    Maps()
                }
            }
        }
    }
}


@Composable
fun Maps() {
    MyApp()
//    GoogleMapViewScreen()


}



