package com.ram.buspass.googleMap.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions



@Composable
fun MapWithMarkers() {
    var lat by remember { mutableStateOf("") }
    var lng by remember { mutableStateOf("") }
    val context = LocalContext.current
    var mapView: MapView? by remember { mutableStateOf(null) }
    var googleMap: GoogleMap? by remember { mutableStateOf(null) }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {


        Row(
            modifier = Modifier.padding(top = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                value = lat,
                onValueChange = { lat = it },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = Modifier.width(16.dp))
            BasicTextField(
                value = lng,
                onValueChange = { lng = it },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val latDouble = lat.toDoubleOrNull()
            val lngDouble = lng.toDoubleOrNull()
            if (latDouble != null && lngDouble != null) {
                val coordinates = LatLng(latDouble, lngDouble)
                googleMap?.apply {
                    clear()
                    addMarker(MarkerOptions().position(coordinates))
                    moveCamera(CameraUpdateFactory.newLatLngZoom(coordinates, 10f))
                }
            }
        }) {
            Text(text = "Update Map")
        }
        Spacer(modifier = Modifier.height(16.dp))
        AndroidView({ context ->
            MapView(context).apply {
                mapView = this
                onCreate(null)
                getMapAsync { map ->
                    googleMap = map
                    map.setOnCameraIdleListener {
                        val cameraPosition: CameraPosition = map.cameraPosition
                        lat = cameraPosition.target.latitude.toString()
                        lng = cameraPosition.target.longitude.toString()
                    }
                    // Add any initial setup here if needed
                    map.uiSettings.isZoomControlsEnabled = true
                    map.uiSettings.isMapToolbarEnabled = true
                    // Add more setup as needed
                }
            }
        }, modifier = Modifier.fillMaxSize().padding(bottom = 50.dp))
    }
}


