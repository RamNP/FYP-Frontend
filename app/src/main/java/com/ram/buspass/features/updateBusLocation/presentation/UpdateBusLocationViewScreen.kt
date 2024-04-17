package com.ram.buspass.features.updateBusLocation.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ram.buspass.utils.components.ButtonView
import com.ram.buspass.utils.components.IconView
import com.ram.buspass.utils.components.InputTextFieldView
import com.ram.buspass.utils.components.TextView
import com.ram.buspass.interfaceUtils.UserInterfaceUtil.Companion.showToast
import com.ram.buspass.ui.theme.Purple
import com.ram.buspass.ui.theme.White
import com.ram.buspass.utils.NetworkObserver

@Composable
fun BusLocationViewScreen(
    latitude: String,
    longitude: String,
    navController: NavHostController,
    updateBusLocationViewModel: UpdateBusLocationViewModel = hiltViewModel()
) {
    val getBusLocation = updateBusLocationViewModel.busLocation
    val getBookingBus = updateBusLocationViewModel.bookingBus
    val connection by NetworkObserver.connectivityState()
    val isConnected = connection === NetworkObserver.ConnectionState.Available
    val context = LocalContext.current
    var selectedBusNumber by remember { mutableStateOf("") }
    var busNumberExpanded by remember { mutableStateOf(false) }
    var mExpanded by remember { mutableStateOf(false) }
    var mTextFieldSize by remember { mutableStateOf(Size.Zero) }
    var busId by remember { mutableStateOf(0) }

    val icon = if (mExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown

    var isBusNumberEmpty by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit, block = {
        updateBusLocationViewModel.getBookingBusDetails()
    })

    if (getBookingBus.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(1f)
        }
    }

    LaunchedEffect(key1 = getBookingBus.isError, block = {
        if (getBookingBus.isError?.isNotEmpty() == true) {
            showToast(context, getBookingBus.isError)
        }
    })

    getBookingBus.isData?.data.let { busResults ->

        val filteredData = busResults?.filter {
            it.bus_number == selectedBusNumber
        }
        val busNumber = mutableListOf<String>()

        busResults?.forEach { label ->
            busNumber.add(label.bus_number ?: "")

        }

        var busNumberText by remember { mutableStateOf(busNumber.getOrNull(0).toString()) }


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
                horizontalArrangement = Arrangement.Center
            ) {
                TextView(
                    text = "Location Update",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

//            Bus Number TextFiled
                InputTextFieldView(
                    value = busNumberText,
                    onValueChange = { busNumberText = it },
                    label = "Select Bus Number",
                    placeholder = "",
                    textStyle = TextStyle(),
                    isError = false,
                    readOnly = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    invalidMessage = " Latitude Text field is Empty!",
                    errorColor = Color.Red,
                    leadingIcon = { },
                    trailingIcon = {
                        Icon(
                            icon,
                            "Icon",
                            Modifier.clickable { busNumberExpanded = !busNumberExpanded }
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxWidth()
                        .onGloballyPositioned { coordinates ->
                            mTextFieldSize = coordinates.size.toSize()
                        },
                )
                // Create a drop-down menu with list of bus details,
                // when clicked, set the Text Field text as the bus id selected
                DropdownMenu(
                    expanded = busNumberExpanded,
                    onDismissRequest = { busNumberExpanded = false },
                    modifier = Modifier
                        .width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })
                ) {
                    busNumber.forEach { busNames ->
                        DropdownMenuItem(
                            onClick = {
                                busNumberText = busNames
                                selectedBusNumber = busNames
                                busNumberExpanded = false
                                showToast(context, "Bus Number: $selectedBusNumber")

                            }
                        ) {
                            Text(text = busNames)
                        }
                    }
                }
            }
            filteredData?.forEach { item ->
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)) {


                    BookingBusCard(
                        busNumber = item.bus_number ?: "",
                        busName = item.name ?: "",
                        fromTo = item.from_to ?: "",
                        route = item.route ?: "",
                        busSpeed = item.bus_speed ?: "",
                    )
                }
            }
        }
    }


//Location Update api call
    if (getBusLocation.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextView(
                    text = "Waiting is Page is Opening  ..",
                    style = TextStyle(color = Color.Gray, fontSize = 18.sp),
                )
                androidx.compose.material3.CircularProgressIndicator(
                    1f,
                    modifier = Modifier,
                    color = Purple,
                )
            }
        }
    }

    LaunchedEffect(key1 = getBusLocation.isError, block = {
        if (getBusLocation.isError.isNotEmpty()) {
            showToast(context, getBusLocation.isError)

        }
    })


    LaunchedEffect(key1 = getBusLocation.isData, block = {
        if (getBusLocation.isData?.is_success == true) {
            showToast(context, "${getBusLocation.isData.message}")

        }
    })
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 320.dp)
        ) {
            InputTextFieldView(
                value = latitude,
                onValueChange = { },
                label = "Bus Latitude",
                placeholder = "Enter Latitude",
                textStyle = TextStyle(),
                isError = false,
                readOnly = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                invalidMessage = "",
                errorColor = Color.Red,
                leadingIcon = { IconView(imageVector = Icons.Default.MyLocation) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            )

            InputTextFieldView(
                value = longitude.toString(), // Convert longitude to String
                onValueChange = {},
                label = "Bus Longitude",
                placeholder = "Enter Longitude",
                textStyle = TextStyle(),
                readOnly = true,
                isError = false,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                invalidMessage = "",
                errorColor = Color.Red,
                leadingIcon = { IconView(imageVector = Icons.Default.MyLocation) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            )


        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            ButtonView(
                onClick = {
                    updateBusLocationViewModel.getBusLocation(
                        selectedBusNumber,
                        latitude,
                        longitude
                    )
//                    Log.e("is_success", "${getBusLocation.isData?.message}")
                    showToast(context, "Bus location updated successfully")


                },
                btnColor = ButtonDefaults.buttonColors(Purple),
                text = "Update",
                textStyle = TextStyle()
            )


            ButtonView(
                onClick = { navController.navigate("GoogleMapsScreen") },
                btnColor = ButtonDefaults.buttonColors(Purple),
                text = "Update Bus Geo",
                textStyle = TextStyle()
            )


        }
    }
}

@Composable
fun BookingBusCard(
    busNumber: String?,
    busName: String?,
    fromTo: String?,
    route: String?,
    busSpeed: String?,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
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


        }

    }
}