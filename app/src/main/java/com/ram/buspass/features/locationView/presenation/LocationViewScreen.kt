package com.ram.buspass.features.locationView.presenation

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.ram.buspass.features.components.ButtonView
import com.ram.buspass.features.components.TextView
import com.ram.buspass.features.locationView.data.BusDetailsItem
import com.ram.buspass.ui.theme.Purple
import com.ram.buspass.ui.theme.White


@SuppressLint("SuspiciousIndentation")
@Composable
fun LocationViewScreen(
    navController: NavHostController,
    locationViewViewModel: LocationViewViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val locationViewResults = locationViewViewModel.locationView


    LaunchedEffect(key1 = Unit, block = {
        locationViewViewModel.getViewLocation()
    })


    if (locationViewResults.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            // indicator
            CircularProgressIndicator(1f)
        }
    }
    if (locationViewResults.isError != null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            TextView(text = locationViewResults.isError)
        }
    }

    locationViewResults.isData?.bus_details.let { locationResults ->

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 40.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(White)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center,
            ) {

                TextView(
                    text = "Location View",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.Black, modifier = Modifier.padding(end = 80.dp)
                )
            }
            Column(modifier = Modifier.fillMaxWidth()) {


                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(locationResults ?: listOf()) { it: BusDetailsItem ->
                        // Use it directly without null-checking or let block
                        LocationViewCard(
                            busNumber = it.bus_number ?: "",
                            busName = it.name ?: "",
                            fromTo = it.from_to ?: "",
                            route = it.route ?: "",
                            busSpeed = it.bus_speed ?: "",
                            latitude = it.latitude ?: 0.0,
                            longitude = it.longitude,
                            navController = navController,
                            context = context
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun LocationViewCard(
    busNumber: String?,
    busName: String?,
    fromTo: String?,
    route: String?,
    busSpeed: String?,
    latitude: Double?,
    longitude: Double?,
    navController: NavHostController,
    context: Context
) {
    var mapDisplayed by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxSize()
            .border(
                width = 1.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(10.dp)
            ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp,

            ),
        colors = CardDefaults.cardColors(White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center

        ) {

            TextView(
                text = "Bus Number: ${busNumber.toString()}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp)

            )

            TextView(
                text = "Bus Name: ${busName.toString()}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp)
            )


            TextView(
                text = "FromTo:${fromTo.toString()}",
                fontSize = 16.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp)

            )


            TextView(
                text = " Route$route",
                fontSize = 16.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp),

                )
            TextView(
                text = "Bus Speed:${busSpeed.toString()}",
                fontSize = 16.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp)

            )

            TextView(
                text = "Latitude: $latitude",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,

                modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp),

                )
            TextView(
                text = "Longitude: $longitude",
                fontSize = 16.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp),

                )

        }


    }
    Column(modifier = Modifier.fillMaxSize()) {


        Box(modifier = Modifier.fillMaxSize()) {

            ButtonView(
                onClick = {
                    mapDisplayed = true
//                    navController.navigate(ScreenList.ShowGoogleMapsScreen.route)
                },
                btnColor = ButtonDefaults.buttonColors(Purple),
                text = "Show Geo",
                textStyle = TextStyle(), modifier = Modifier.padding(start = 10.dp, bottom = 10.dp)
            )
                if (mapDisplayed) {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .padding(top = 2.dp)
                    ) {

                        MapViewContainer(latitude = latitude ?: 0.0, longitude = longitude ?: 0.0)
                    }
                }




        }
    }
}


@Composable
fun MapViewContainer(latitude: Double, longitude: Double) {
    val context = LocalContext.current


        AndroidView(

            factory = { context ->
                MapView(context).apply {
                    onCreate(Bundle())
                    getMapAsync { googleMap ->
                        val location = LatLng(latitude, longitude)
                        googleMap.addMarker(
                            MarkerOptions().position(location).title("Bus Current Location")
                        )
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15f))
                    }
                }
            },
            modifier = Modifier
                .fillMaxSize()
        )
    }
