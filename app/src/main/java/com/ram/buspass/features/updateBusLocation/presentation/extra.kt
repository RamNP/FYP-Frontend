package com.ram.buspass.features.updateBusLocation.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@Composable
fun BusLocationScreen() {
    var latitude by remember { mutableStateOf("") }
    var longitude by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = latitude,
            onValueChange = { latitude = it },
            label = { Text("Latitude") },
            modifier = Modifier.padding(16.dp)
        )

        OutlinedTextField(
            value = longitude,
            onValueChange = { longitude = it },
            label = { Text("Longitude") },
            modifier = Modifier.padding(16.dp)
        )

        Button(
            onClick = {
                // Call the function to update bus location
                updateBusLocation(latitude, longitude)
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Update Bus Location")
        }
    }
}

fun updateBusLocation(latitude: String, longitude: String) {
    val database = Firebase.database
    val reference = database.getReference("/buses/bus_number") // Update with the correct path

    val busData = mapOf(
        "latitude" to latitude.toDouble(),
        "longitude" to longitude.toDouble()
    )

    // Perform the update within a coroutine
    CoroutineScope(Dispatchers.IO).launch {
        try {
            withContext(Dispatchers.IO) {
                reference.setValue(busData)
            }
        } catch (e: Exception) {
            // Handle exception
            e.printStackTrace()
        }
    }
}

