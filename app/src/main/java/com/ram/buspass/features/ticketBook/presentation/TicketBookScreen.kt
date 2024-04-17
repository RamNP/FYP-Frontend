package com.ram.buspass.features.ticketBook.presentation

import android.util.Log
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BusAlert
import androidx.compose.material.icons.filled.CandlestickChart
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Paid
import androidx.compose.material.icons.filled.Route
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ram.buspass.utils.components.ButtonView
import com.ram.buspass.utils.components.IconView
import com.ram.buspass.utils.components.TextView
import com.ram.buspass.interfaceUtils.UserInterfaceUtil.Companion.showToast
import com.ram.buspass.ui.theme.Purple
import com.ram.buspass.ui.theme.White
import com.ram.buspass.userNavigationBar.UserScreen
import com.ram.buspass.utils.ClientInterceptor
import com.ram.buspass.utils.NetworkObserver
import kotlinx.coroutines.ExperimentalCoroutinesApi


@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun TicketBookViewScreens(
    navController: NavHostController,
    ticketBookViewModel: TicketBookViewModel = hiltViewModel(),
) {
    val connection by NetworkObserver.connectivityState()
    val isConnected = connection === NetworkObserver.ConnectionState.Available
    val ticketResult = ticketBookViewModel.ticket
    val bookingTicketResult = ticketBookViewModel.bookingTicket

    val context = LocalContext.current
    var busNameExpanded by remember { mutableStateOf(false) }
    var mExpanded by remember { mutableStateOf(false) }
    var mTextFieldSize by remember { mutableStateOf(Size.Zero) }
    // Up Icon when expanded and down icon when collapsed
    val icon = if (mExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown
    var selectedBusName by remember { mutableStateOf("") }
    val userId = ClientInterceptor(context).getUserId()
    var butId by remember { mutableStateOf(0) }
    var ticketId by remember { mutableStateOf(0) }



    LaunchedEffect(key1 = Unit, block = {
        ticketBookViewModel.getTicket()
    })

    if (ticketResult.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextView(
                    text = "Waiting This Page is Opening..",
                    style = TextStyle( color = Color.Gray , fontSize = 18.sp),
                )
                CircularProgressIndicator(1f, modifier = Modifier, color = Purple , )
            }
        }
    }
    if (ticketResult.isError != null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            // error message
            TextView(text = ticketResult.isError)
        }
    }

    ticketResult.isData?.data.let { result ->

        val filteredData = result?.filter {
            it.bus_details?.name == selectedBusName
        }

        val bus_Id = mutableListOf<Int>()
        val busNumber = mutableListOf<String>()
        val busName = mutableListOf<String>()
        val fromTo = mutableListOf<String>()
        val busRoute = mutableListOf<String>()
        val ticketIds = mutableListOf<Int>()
        val ticketNum = mutableListOf<Int>()
        val ticketDate = mutableListOf<String>()
        val ticketTime = mutableListOf<String>()
        val ticketCost = mutableListOf<Int>()
        result?.forEach { label ->
            label.bus_details?.let { it ->
                bus_Id.add(it.id ?: 0)
                busNumber.add(it.bus_number ?: "")
                busName.add(it.name)
                fromTo.add(it.from_to ?: "")
                busRoute.add(it.route ?: "")

            }
            label.ticket_details?.let { it ->
                ticketIds.add(it.id ?: 0)
                ticketNum.add(it.ticket_no ?: 0)
                ticketDate.add(it.date ?: "")
                ticketTime.add(it.time ?: "")
                ticketCost.add(it.cost ?: 0)
            }
        }
        var busNameText by remember { mutableStateOf(busName.getOrNull(0).toString()) }
        val scrollState = rememberScrollState()
        if (isConnected) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(White)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Ticket Booking",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .verticalScroll(scrollState)
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 28.dp)
                ) {

//            Bus Name TextFiled
                OutlinedTextField(
                    value = busNameText,
                    onValueChange = { busNameText = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                        .onGloballyPositioned { coordinates ->
                            mTextFieldSize = coordinates.size.toSize()
                        },
                    readOnly = true,
                    label = { Text(" Select Bus Name") },
                    trailingIcon = {
                        Icon(
                            icon,
                            "Icon",
                            Modifier.clickable { busNameExpanded = !busNameExpanded }
                        )
                    }
                )

                    // Create a drop-down menu with list of bus details,
                    // when clicked, set the Text Field text as the bus id selected
                DropdownMenu(
                    expanded = busNameExpanded,
                    onDismissRequest = { busNameExpanded = false },
                    modifier = Modifier
                        .width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })
                ) {
                    busName.forEach { busNames ->
                        DropdownMenuItem(
                            onClick = {
                                busNameText = busNames
                                selectedBusName = busNames
                                busNameExpanded = false
                                showToast(context, "Bus Name: $selectedBusName")

                            }
                        ) {
                            Text(text = busNames)
                        }
                    }
                }
                }

                filteredData?.forEach { item ->
                    butId = item.bus_details?.id ?: 0
                    ticketId = item.ticket_details?.id ?: 0

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    ) {
                        TicketCard(
                            busNumber = "${item.bus_details?.bus_number}",
                            busName = "${item.bus_details?.name}",
                            fromTo = "${item.bus_details?.from_to}",
                            route = "${item.bus_details?.route}",
                            busSpeed = "${item.bus_details?.bus_speed}",
                            ticketNo = "${item.ticket_details?.ticket_no}",
                            date = "${item.ticket_details?.date}",
                            time = "${item.ticket_details?.time}",
                            cost = "${item.ticket_details?.cost}",
                            navController = navController,
                        )

                    }

                    ButtonView(
                        onClick = {

                            ticketBookViewModel.getTicketBook(
                                bus = butId,
                                ticket = ticketId,
                                users = userId
                            )
                        },
                        btnColor = ButtonDefaults.buttonColors(Purple),
                        text = "Book Ticket",
                        textStyle = TextStyle()
                    )
                }
            }
            //TicketBooking details

            if (bookingTicketResult.isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    // indicator
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TextView(
                            text = "Waiting This Page is Opening..",
                            style = TextStyle( color = Color.Gray , fontSize = 18.sp),
                        )
                        CircularProgressIndicator(1f, modifier = Modifier, color = Purple , )
                    }
                }
            }
            if (bookingTicketResult.isError != null) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    // error message
                    TextView(text = bookingTicketResult.isError)
                    Log.e("BookingMessage", "${bookingTicketResult.isError}")
                }
            }
            LaunchedEffect(key1 = bookingTicketResult, block = {
                if (bookingTicketResult.isData?.isSuccess == true) {
                    navController.navigate(UserScreen.MyTicket.route)
                    showToast(context, "${bookingTicketResult.isData.message}")
                }
            })
        } else {
            Text(text = "No Internet Net")
        }
    }

}


@Composable
fun TicketCard(
    busNumber: String?,
    busName: String?,
    fromTo: String?,
    route: String?,
    busSpeed: String?,
    ticketNo: Any,
    date: String?,
    time: String?,
    cost: Any,
    navController: NavHostController

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
                text = "Bus No: ${busNumber.toString()}",
                fontSize = 16.sp, fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp)
            )
            Row(modifier = Modifier.fillMaxSize()) {
                IconView(imageVector = Icons.Default.BusAlert)

                TextView(
                    text = "Bus Name: ${busName.toString()}",
                    fontSize = 16.sp, fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp)
                )
            }

            Row(modifier = Modifier.fillMaxSize()) {
                IconView(imageVector = Icons.Default.LocationOn)

                TextView(
                    text = "FromTo:${fromTo.toString()}",
                    fontSize = 16.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp)

                )


            }

            Row(modifier = Modifier.fillMaxSize()) {
                IconView(imageVector = Icons.Default.Route)

                TextView(
                    text = "Route: ${route.toString()}",
                    fontSize = 16.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp)

                )


            }

            Row(modifier = Modifier.fillMaxSize()) {

                IconView(imageVector = Icons.Default.Speed)
                TextView(
                    text = "BusSpeed: $busSpeed",
                    fontSize = 16.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp),

                    )
            }
            Row(modifier = Modifier.fillMaxSize()) {

                IconView(imageVector = Icons.Default.CandlestickChart)
                TextView(
                    text = "Ticket No: $ticketNo",
                    fontSize = 16.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp),

                    )
            }
            Row(modifier = Modifier.fillMaxSize()) {

                IconView(imageVector = Icons.Default.DateRange)
                TextView(
                    text = "Date: $date",
                    fontSize = 16.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp),

                    )
            }
            Row(modifier = Modifier.fillMaxSize()) {

                IconView(imageVector = Icons.Default.Timer)
                TextView(
                    text = "Time: $time",
                    fontSize = 16.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp),

                    )
            }

            Row(modifier = Modifier.fillMaxSize()) {

                IconView(imageVector = Icons.Default.Paid)

                TextView(
                    text = "Cost: Rs$cost",
                    fontSize = 16.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp),

                    )
            }
//            ButtonView(
//                onClick = { navController.navigate(UserScreen.MyTikcet.route) },
//                btnColor = ButtonDefaults.buttonColors(Purple),
//                text = "Book Ticket", textStyle = TextStyle(fontWeight = FontWeight.Bold)
//            )

        }
    }
}