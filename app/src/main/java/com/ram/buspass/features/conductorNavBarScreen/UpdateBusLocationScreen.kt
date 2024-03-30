package com.ram.buspass.features.conductorNavBarScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ram.buspass.googleMap.presentation.MapWithMarkers
import com.ram.buspass.ui.theme.Purple






@Composable
fun UpdateBusLocationScreen() {
    var showMap by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Show the map if showMap is true
        if (showMap) {
            MapWithMarkers()
        } else {
            // Show button to go to MapWithMarkers screen
            Button(onClick = { showMap = true } , colors = ButtonDefaults.buttonColors(Purple)) {
                Text(text = "Update Geo Location")
            }
        }
    }
}