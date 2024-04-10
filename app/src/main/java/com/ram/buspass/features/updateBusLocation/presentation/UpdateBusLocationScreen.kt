package com.ram.buspass.features.updateBusLocation.presentation

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.Color
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.Polyline
import com.google.android.gms.maps.model.PolylineOptions
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


@Composable
fun UpdateBusLocationViewScreen() {
    var isMapOpened by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                isMapOpened = true
            },
            enabled = !isMapOpened
        ) {
            Text("Update bus location Map")
        }

        if (isMapOpened) {
            MainGoogleMap()
        }
    }

}



@SuppressLint("MissingPermission")
@Composable
fun MainGoogleMap() {
    var markers by remember { mutableStateOf(emptyList<LatLng>()) }
    var lat by remember { mutableStateOf("") }
    var lng by remember { mutableStateOf("") }
    var mapView: MapView? by remember { mutableStateOf(null) }
    var googleMap: GoogleMap? by remember { mutableStateOf(null) }
    val database = Firebase.database
    val databaseRef = database.getReference("buses")
    var polyline: Polyline? = null // Variable to hold the polyline

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    // Permission request launcher
    val requestPermissionLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
        if (isGranted) {
            mapView?.getMapAsync { map ->
                googleMap = map
                map.setOnCameraIdleListener {
                    val cameraPosition = map.cameraPosition
                    lat = cameraPosition.target.latitude.toString()
                    lng = cameraPosition.target.longitude.toString()
                }
                map.uiSettings.isZoomControlsEnabled = true
                map.uiSettings.isMapToolbarEnabled = true
                map.isMyLocationEnabled = true // Enable My Location layer
                markers.forEach { marker ->
                    googleMap?.addMarker(MarkerOptions().position(marker))
                }
//                setMapClickListener()
            }
        } else {
            // Permission denied, handle accordingly
        }
    }

    fun setMapClickListener() {
        googleMap?.setOnMapClickListener { latLng ->
            markers = markers + listOf(latLng)
            googleMap?.addMarker(MarkerOptions().position(latLng))
            if (markers.size >= 2) {
                polyline?.remove() // Remove the existing polyline if any
                val polylineOptions = PolylineOptions().apply {
                    color(Color.RED)
                    width(5f)
                    markers.forEach { marker ->
                        add(marker)
                    }
                }
                polyline = googleMap?.addPolyline(polylineOptions) // Draw new polyline
            }
            val locationData = markers.mapIndexed { index, latLng ->
                "Location${index + 1}" to hashMapOf(
                    "latitude" to latLng.latitude,
                    "longitude" to latLng.longitude
                )
            }.toMap()
            databaseRef.setValue(locationData)
        }
    }

    DisposableEffect(Unit) {
        mapView?.let { mapView ->
            val mapContext = context.applicationContext
            if (ContextCompat.checkSelfPermission(mapContext, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
                mapView.getMapAsync { map ->
                    googleMap = map
                    map.setOnCameraIdleListener {
                        val cameraPosition = map.cameraPosition
                        lat = cameraPosition.target.latitude.toString()
                        lng = cameraPosition.target.longitude.toString()
                    }
                    map.uiSettings.isZoomControlsEnabled = true
                    map.uiSettings.isMapToolbarEnabled = true
                    map.isMyLocationEnabled = true // Enable My Location layer
                    markers.forEach { marker ->
                        googleMap?.addMarker(MarkerOptions().position(marker))
                    }
                    setMapClickListener()
                }
            } else {
                requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
        onDispose {
            mapView?.getMapAsync { googleMap?.clear() }
        }
    }

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
                value = "Latitude:${lat}",
                onValueChange = { lat = it },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = Modifier.width(16.dp))
            BasicTextField(
                value = "Longitude:${lng}",
                onValueChange = { lng = it },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        AndroidView(
            factory = { context ->
                MapView(context).apply {
                    mapView = this
                    onCreate(null)
                }
            }, modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 50.dp)
        )
    }
}
