package com.ram.buspass.features.updateBusLocation.presentation


import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.Color
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.Polyline
import com.google.android.gms.maps.model.PolylineOptions
import com.ram.buspass.conductorNavigationBar.ConductorNavigation
import com.ram.buspass.interfaceUtils.UserInterfaceUtil
import com.ram.buspass.interfaceUtils.UserInterfaceUtil.Companion.showToast
import com.ram.buspass.ui.theme.Purple
import com.ram.buspass.ui.theme.White
import com.ram.buspass.utils.components.InputTextFieldView
import com.ram.buspass.utils.components.TextView
@SuppressLint("MissingPermission")
@Composable
fun UpdateBusLocationGoogleMapScreenScreen(
    bus_number: String, navController: NavHostController,
    updateBusLocationViewModel: UpdateBusLocationViewModel = hiltViewModel()
) {
    var markers by remember { mutableStateOf(emptyList<LatLng>()) }
    var latitude by remember { mutableStateOf("") }
    var longitude by remember { mutableStateOf("") }
    var mapView: MapView? by remember { mutableStateOf(null) }
    var googleMap: GoogleMap? by remember { mutableStateOf(null) }
    var polyline: Polyline? = null // Variable to hold the polyline
    val getBusLocation = updateBusLocationViewModel.busLocation
    val context = LocalContext.current
    val fusedLocationClient: FusedLocationProviderClient = remember { LocationServices.getFusedLocationProviderClient(context) }

//Location Update api call
    LaunchedEffect(key1 = latitude,key2 = longitude,block = {
        updateBusLocationViewModel.getBusLocation(bus_number ,latitude, longitude)
        Log.e("LocationDetailsLat" , "$latitude")
        Log.e("LocationDetailsLong" , "$longitude")


    })

    if (getBusLocation.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextView(
                    text = "Waiting is Page is Opening  ..",
                    style = TextStyle(
                        color = androidx.compose.ui.graphics.Color.Gray,
                        fontSize = 18.sp
                    ),
                )
                CircularProgressIndicator(
                    1f,
                    modifier = Modifier,
                    color = Purple,
                )
            }
        }
    }

    LaunchedEffect(key1 = getBusLocation.isError, block = {
        if (getBusLocation.isError.isNotEmpty()) {
            UserInterfaceUtil.showToast(context, getBusLocation.isError)

        }
    })


    LaunchedEffect(key1 = getBusLocation.isData, block = {
        if (getBusLocation.isData?.is_success == true) {
            showToast(context, "${getBusLocation.isData.message}")

        }
    })


//Google Map
    // Permission request launcher
    val requestPermissionLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                mapView?.getMapAsync { map ->
                    googleMap = map
                    map.setOnCameraIdleListener {
                        val cameraPosition = map.cameraPosition
                        latitude = cameraPosition.target.latitude.toString()
                        longitude = cameraPosition.target.longitude.toString()
                    }
                    map.uiSettings.isZoomControlsEnabled = true
                    map.uiSettings.isMapToolbarEnabled = true
                    map.isMyLocationEnabled = true // Enable My Location layer
                    markers.forEach { marker ->
                        googleMap?.addMarker(MarkerOptions().position(marker))
                    }
                }
            }
        }

    fun requestUserLocation() {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                location?.let {
                    val latLng = LatLng(location.latitude, location.longitude)
                    googleMap?.addMarker(MarkerOptions().position(latLng).title("Current Location"))
                    googleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
                }
            }
        } else {
            // Permission denied, handle accordingly
        }
    }

    fun setMapClickListener(map: GoogleMap) {
        map.setOnMapClickListener { latLng ->
            markers = markers + listOf(latLng)
            map.addMarker(MarkerOptions().position(latLng))
            if (markers.size >= 2) {
                polyline?.remove() // Remove the existing polyline if any
                val polylineOptions = PolylineOptions().apply {
                    color(Color.RED)
                    width(10f)
                    markers.forEach { marker ->
                        add(marker)
                    }
                }
                polyline = map.addPolyline(polylineOptions) // Draw new polyline
            }
            val locationData = markers.mapIndexed { index, latLng ->
                "Location${index + 1}" to hashMapOf(
                    "latitude" to latLng.latitude,
                    "longitude" to latLng.longitude
                )
            }.toMap()
            latitude = latLng.latitude.toString()
            longitude = latLng.longitude.toString()
        }
    }

    DisposableEffect(Unit) {
        mapView?.let { mapView ->
            val mapContext = context.applicationContext
            if (ContextCompat.checkSelfPermission(
                    mapContext,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
                == PackageManager.PERMISSION_GRANTED
            ) {
                mapView.getMapAsync { map ->
                    googleMap = map
                    map.setOnCameraIdleListener {
                        val cameraPosition = map.cameraPosition
                        latitude = cameraPosition.target.latitude.toString()
                        longitude = cameraPosition.target.longitude.toString()
                    }
                    map.uiSettings.isZoomControlsEnabled = true
                    map.uiSettings.isMapToolbarEnabled = true
                    map.isMyLocationEnabled = true // Enable My Location layer
                    markers.forEach { marker ->
                        googleMap?.addMarker(MarkerOptions().position(marker))
                    }
                    requestUserLocation()
                    setMapClickListener(map)
                }
            } else {
                requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
        onDispose {
            mapView?.getMapAsync { googleMap?.clear() }
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(White)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(imageVector = Icons.Default.ArrowBackIosNew, contentDescription ="" , modifier = Modifier.clickable { navController.navigate(ConductorNavigation.UpdateGeo.route) } )
        TextView(
            text = "Google Map View",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = androidx.compose.ui.graphics.Color.Black , modifier = Modifier.padding(end = 80.dp)
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 45.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {

            InputTextFieldView(
                value = latitude,
                onValueChange = { latitude = it },
                label = " Latitude",
                placeholder = "Latitude",
                textStyle = TextStyle(),
                isError = false,
                readOnly = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                invalidMessage = "",
                errorColor = androidx.compose.ui.graphics.Color.Red,
                leadingIcon = { Icon(imageVector = Icons.Default.MyLocation, contentDescription = "")},
                trailingIcon = {},
                modifier = Modifier.fillMaxWidth()

            )
            InputTextFieldView(
                value = longitude,
                onValueChange = { longitude = it },
                label = " Longitude",
                placeholder = "",
                textStyle = TextStyle(),
                isError = false,
                readOnly = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                invalidMessage = "",
                errorColor = androidx.compose.ui.graphics.Color.Red,
                leadingIcon = { Icon(imageVector = Icons.Default.MyLocation, contentDescription = "")},
                trailingIcon = {},
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth()

            )

        }
//
//        ButtonView(
//            onClick = {
//                updateBusLocationViewModel.getBusLocation(
//                    bus_number,
//                    latitude,
//                    longitude
//                )
//
//                showToast(context ,"Bus location updated successfully")
//
//            },
//            btnColor = ButtonDefaults.buttonColors(Purple),
//            text = "Update Geo",
//            textStyle = TextStyle()
//        )


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

