package com.ram.buspass.features.locationView.presenation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
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
    var busNumber by remember { mutableStateOf("") }
    var busName by remember { mutableStateOf("") }
    var fromTo by remember { mutableStateOf("") }
    var route by remember { mutableStateOf("") }
    var busSpeed by remember { mutableStateOf("") }
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

        Column(modifier = Modifier.fillMaxWidth().padding(bottom = 40.dp)) {

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
                            latitude = it.latitude,
                            longitude = it.longitude ,
                            navController =  navController
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
    latitude: Any?,
    longitude: Any?,
    navController: NavHostController
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
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


        ButtonView(
            onClick = { val uri = "geo:${latitude},${longitude}?z=15"
                navController.navigate(uri) },
            btnColor = ButtonDefaults.buttonColors(Purple),
            text = "Show Geo",
            textStyle = TextStyle() , modifier = Modifier.padding(start = 10.dp , bottom = 10.dp)
        )
    }
}
