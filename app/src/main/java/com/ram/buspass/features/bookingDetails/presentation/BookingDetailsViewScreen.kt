package com.ram.buspass.features.bookingDetails.presentation

import android.annotation.SuppressLint
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.BookmarkAdded
import androidx.compose.material.icons.filled.BusAlert
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Numbers
import androidx.compose.material.icons.filled.Paid
import androidx.compose.material.icons.filled.PermContactCalendar
import androidx.compose.material.icons.filled.Route
import androidx.compose.material.icons.filled.StickyNote2
import androidx.compose.material.icons.filled.Timer
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
import com.ram.buspass.features.components.IconView
import com.ram.buspass.features.components.TextView
import com.ram.buspass.utils.NetworkObserver
import com.ram.buspass.ui.theme.Purple
import com.ram.buspass.ui.theme.White
import com.ram.buspass.userNavigationBar.UserScreen

@SuppressLint("SuspiciousIndentation")
@Composable
fun BookTicketDetailsViewScreen(
    navController: NavHostController,
    bookingViewModel: BookingViewModel = hiltViewModel()
) {
    val connection by NetworkObserver.connectivityState()
    val isConnected = connection === NetworkObserver.ConnectionState.Available
    val context = LocalContext.current
    var busNumber by remember { mutableStateOf("") }
    var busName by remember { mutableStateOf("") }
    var fromTo by remember { mutableStateOf("") }
    var route by remember { mutableStateOf("") }
    var busSpeed by remember { mutableStateOf("") }
    var ticketNo by remember { mutableStateOf(0) }
    var cost by remember { mutableStateOf(0) }
    val date by remember { mutableStateOf("") } // Initialize date
    var time by remember { mutableStateOf("") } // Initialize time


    val bookingResults = bookingViewModel.booking


    LaunchedEffect(key1 = Unit, block = {
        bookingViewModel.getBooking()
    })

    if (bookingResults.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(1f)
        }
    }
    if (bookingResults.isError != null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            TextView(text = bookingResults.isError)
        }
    }

    if(isConnected) {

        bookingResults.isData?.data?.let { results ->

            Column(modifier = Modifier.fillMaxWidth()) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(White)
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconView(
                        imageVector = Icons.Default.ArrowBack,
                        modifier = Modifier.clickable { navController.navigate(UserScreen.Book.route) })
                    TextView(
                        text = "View Ticket Details",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Black, modifier = Modifier.padding(end = 80.dp)
                    )
                }


                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(results) { it ->
                        it.bus_details?.let {
                            busNumber = it.bus_number ?: ""
                            busName = it.name ?: ""
                            fromTo = it.from_to ?: ""
                            route = it.route ?: ""
                            busSpeed = it.bus_speed ?: ""

                        }
                        it.ticket_details?.let {
                            ticketNo = it.ticket_no ?: 0
                            cost = it.cost ?: 0
                            time = it.time ?: "" // Assign value to time variable

                        }
                        it.book_details?.let {
                            BookDetailsCard(
                                bookId = it.id ?: 0,
                                bookDate = it.book_date ?: "",
                                ticketId = it.ticket ?: 0,
                                userId = it.users ?: 0,
                                busNumber = busNumber,
                                busName = busName,
                                fromTo = fromTo,
                                route = route,
                                ticketNo = ticketNo,
                                cost = cost,
                                time = time,
                            )

                        }

                    }
                }
            }
        }
    }else{
        TextView(text = "No InterNet")
    }
}

@Composable
fun BookDetailsCard(
    bookId: Int?,
    ticketId: Int?,
    bookDate: Any?,
    userId: Int?,
    busNumber: String?,
    busName: String?,
    fromTo: String?,
    route: String?,
    cost: Any,
    time: Any,
    ticketNo: Int,
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 40.dp)
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


            //before

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

                IconView(imageVector = Icons.Default.Paid)

                TextView(
                    text = "Cost: Rs${cost}",
                    fontSize = 16.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
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
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp),

                    )
            }
            Row(modifier = Modifier.fillMaxSize()) {
                IconView(imageVector = Icons.Default.BookmarkAdded)

                TextView(
                    text = "BookId: ${bookId.toString()}",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp)
                )
            }
            Row(modifier = Modifier.fillMaxSize()) {
                IconView(imageVector = Icons.Default.DateRange)

                TextView(
                    text = "BookDate: ${bookDate.toString()}",
                    fontSize = 16.sp, fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp)
                )
            }
            Row(modifier = Modifier.fillMaxSize()) {
                IconView(imageVector = Icons.Default.StickyNote2)

                TextView(
                    text = "Ticket Id: ${ticketId.toString()}",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp)
                )
            }

            Row(modifier = Modifier.fillMaxSize()) {
                IconView(imageVector = Icons.Default.PermContactCalendar)

                TextView(
                    text = "User Id: ${userId.toString()}",
                    fontSize = 16.sp, fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp)
                )
            }
            Row(modifier = Modifier.fillMaxSize()) {
                IconView(imageVector = Icons.Default.Numbers)
                TextView(
                    text = "Ticket No: $ticketNo",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp)
                )
            }
            ButtonView(
                onClick = {},
                btnColor = ButtonDefaults.buttonColors(Purple),
                text = "Payment", textStyle = TextStyle(fontWeight = FontWeight.Bold)
            )

        }
    }
}

