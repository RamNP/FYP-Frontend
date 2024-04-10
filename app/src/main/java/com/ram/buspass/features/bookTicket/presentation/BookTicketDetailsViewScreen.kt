//package com.ram.buspass.features.bookTicket.presentation
//
//import android.annotation.SuppressLint
//import androidx.compose.foundation.border
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.BusAlert
//import androidx.compose.material.icons.filled.LocationOn
//import androidx.compose.material.icons.filled.Paid
//import androidx.compose.material.icons.filled.Route
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardDefaults
//import androidx.compose.material3.CircularProgressIndicator
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextOverflow
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.navigation.NavHostController
//import com.ram.buspass.features.components.ButtonView
//import com.ram.buspass.features.components.IconView
//import com.ram.buspass.features.components.TextView
//import com.ram.buspass.ui.theme.White
//
//@SuppressLint("SuspiciousIndentation")
//@Composable
//fun BookTicketDetailsViewScreen(
//    navController: NavHostController,
//    bookTicketViewModel: BookTicketViewModel = hiltViewModel()
//) {
//    val context = LocalContext.current
//    val busNumber by remember { mutableStateOf("") }
//    var cost by remember { mutableStateOf(0) }
//    var date by remember { mutableStateOf("") }
//    var time by remember { mutableStateOf("") }
//
//    val ticketResults = bookTicketViewModel.bookTicket.value
//
//    Column(modifier = Modifier.fillMaxWidth()) {
//        TopAppBar(
//            modifier = Modifier.fillMaxWidth(),
//            backgroundColor = Purple,
//        ) {
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.Center
//            ) {
//                TextView(
//                    text = "Book Ticket",
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 18.sp,
//                    color = Color.White
//                )
//            }
//        }
//
//
//        // Button to trigger the API call
//        Button(
//            onClick = {
//                bookTicketViewModel.getBookTicket(busNumber = busNumber)
//            },
//            modifier = Modifier.padding(8.dp)
//        ) {
//            Text("Load Data")
//        }
//
//        if (ticketResults.isLoading) {
//            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//                // indicator
//                CircularProgressIndicator(1f)
//            }
//        }
//        if (ticketResults.isError.isNotBlank()) {
//            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//                TextView(text = ticketResults.isError)
//            }
//        }
//
//        ticketResults.isData?.let { results ->
//            LazyColumn(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(20.dp),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                items(results) { it ->
//                    it.ticketDetails?.let {
//                        cost = it.cost ?: 0
//                    }
//                    it.busDetails?.let {
//                        TicketCard(
//                            busNumber = it.busNumber,
//                            busName = it.name,
//                            fromTo = it.fromTo,
//                            route = it.route,
//                            cost = cost,
//                            date = date,
//                            time = time,
//                        )
//                    }
//                }
//            }
//        }
//    }
//
//
//
//@Composable
//fun TicketCard(
//    busNumber: String?,
//    busName: String?,
//    fromTo: String?,
//    route: String?,
//    cost: Any,
//    date:Any,
//    time:Any,
//) {
//
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .border(
//                width = 1.dp,
//                color = Color.Gray,
//                shape = RoundedCornerShape(10.dp)
//            ),
//        elevation = CardDefaults.cardElevation(
//            defaultElevation = 10.dp,
//
//            ),
//        colors = CardDefaults.cardColors(White)
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp),
//            verticalArrangement = Arrangement.Center
//        ) {
//
//            TextView(
//                text = "Bus No: ${busNumber.toString()}",
//                fontSize = 16.sp, fontWeight = FontWeight.Bold,
//                modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp)
//            )
//            Row(modifier = Modifier.fillMaxSize()) {
//                IconView(imageVector = Icons.Default.BusAlert)
//
//                TextView(
//                    text = "Bus Name: ${busName.toString()}",
//                    fontSize = 16.sp, fontWeight = FontWeight.Bold,
//                    modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp)
//                )
//            }
//
//            Row(modifier = Modifier.fillMaxSize()) {
//                IconView(imageVector = Icons.Default.LocationOn)
//
//                TextView(
//                    text = "FromTo:${fromTo.toString()}",
//                    fontSize = 16.sp,
//                    maxLines = 2,
//                    overflow = TextOverflow.Ellipsis,
//                    modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp)
//
//                )
//
//
//            }
//
//            Row(modifier = Modifier.fillMaxSize()) {
//                IconView(imageVector = Icons.Default.Route)
//
//                TextView(
//                    text = "Route: ${route.toString()}",
//                    fontSize = 16.sp,
//                    maxLines = 2,
//                    overflow = TextOverflow.Ellipsis,
//                    modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp)
//
//                )
//
//
//            }
//
//            Row(modifier = Modifier.fillMaxSize()) {
//
//                IconView(imageVector = Icons.Default.Paid)
//
//                TextView(
//                    text = "Cost: Rs${cost}",
//                    fontSize = 16.sp,
//                    maxLines = 2,
//                    overflow = TextOverflow.Ellipsis,
//                    fontWeight = FontWeight.Bold,
//                    modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp),
//
//                    )
//            }
//
//            Row(modifier = Modifier.fillMaxSize()) {
//
//                IconView(imageVector = Icons.Default.Paid)
//
//                TextView(
//                    text = "Time: $time",
//                    fontSize = 16.sp,
//                    maxLines = 2,
//                    overflow = TextOverflow.Ellipsis,
//                    fontWeight = FontWeight.Bold,
//                    modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp),
//
//                    )
//            }
//
//            Row(modifier = Modifier.fillMaxSize()) {
//
//                IconView(imageVector = Icons.Default.Paid)
//
//                TextView(
//                    text = "Date: $date",
//                    fontSize = 16.sp,
//                    maxLines = 2,
//                    overflow = TextOverflow.Ellipsis,
//                    fontWeight = FontWeight.Bold,
//                    modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp),
//
//                    )
//            }
//            ButtonView(
//                onClick = {},
//                btnColor = ButtonDefaults.buttonColors(Color.Gray),
//                text = "Payment", textStyle = TextStyle(fontWeight = FontWeight.Bold)
//            )
//
//        }
//    }
//}
//
//@Composable
//fun ViewTicketCard(
//    busNumber: String?,
//    busName: String?,
//    fromTo: String?,
//    route: String?,
//    cost: Any,
//    date: Any,
//    ticketNo: Int
//
//) {
//
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .border(
//                width = 1.dp,
//                color = Color.Gray,
//                shape = RoundedCornerShape(10.dp)
//            ),
//        elevation = CardDefaults.cardElevation(
//            defaultElevation = 10.dp,
//
//            ),
//        colors = CardDefaults.cardColors(White)
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp),
//            verticalArrangement = Arrangement.Center
//        ) {
//
//
//            TextView(
//                text = "Bus No: ${busNumber.toString()}",
//                fontSize = 16.sp, fontWeight = FontWeight.Bold,
//                modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp)
//            )
//
//
//            TextView(
//                text = "Bus Name: ${busName.toString()}",
//                fontSize = 16.sp,
//                modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp)
//            )
//            TextView(
//                text = "FromTo:${fromTo.toString()}",
//                fontSize = 16.sp,
//                maxLines = 2,
//                overflow = TextOverflow.Ellipsis,
//                modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp)
//
//            )
//
//            TextView(
//                text = "Route: ${route.toString()}",
//                fontSize = 16.sp,
//                maxLines = 2,
//                overflow = TextOverflow.Ellipsis,
//                modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp)
//
//            )
//            TextView(
//                text = "Ticket No:${ticketNo}",
//                fontSize = 16.sp,
//                maxLines = 2,
//                overflow = TextOverflow.Ellipsis,
//                modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp),
//
//                )
//            TextView(
//                text = "Cost: Rs${cost}",
//                fontSize = 16.sp,
//                maxLines = 2,
//                overflow = TextOverflow.Ellipsis,
//                fontWeight = FontWeight.Bold,
//                modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp),
//
//                )
//            TextView(
//                text = "CreatedDate:${date}",
//                fontSize = 16.sp,
//                maxLines = 2,
//                overflow = TextOverflow.Ellipsis,
//                modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp),
//
//                )
//
//        }
//    }
//}
//
