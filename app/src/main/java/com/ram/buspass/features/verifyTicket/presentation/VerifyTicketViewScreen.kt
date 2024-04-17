package com.ram.buspass.features.verifyTicket.presentation

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
import androidx.compose.material.Text
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
import com.ram.buspass.utils.components.TextView
import com.ram.buspass.ui.theme.Purple
import com.ram.buspass.ui.theme.White


@SuppressLint("SuspiciousIndentation")
@Composable
fun VerifyTicketViewScreen(
    navController: NavHostController,
    verifyTicketViewModel: VerifyTicketViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var busNumber by remember { mutableStateOf("") }
    var busName by remember { mutableStateOf("") }
    var fromTo by remember { mutableStateOf("") }
    var route by remember { mutableStateOf("") }
    var busSpeed by remember { mutableStateOf("") }
    val verifyTicketResults = verifyTicketViewModel.vTicket


    LaunchedEffect(key1 = Unit, block = {
        verifyTicketViewModel.getVerifyTick()
    })


    if (verifyTicketResults.isLoading) {
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
    if (verifyTicketResults.isError != null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            TextView(text = verifyTicketResults.isError)
        }
    }

    verifyTicketResults.isData?.data?.let { ticketResults ->


        Column(modifier = Modifier.fillMaxWidth()) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(White)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Verify Ticket",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
            }
            Column(modifier = Modifier.fillMaxWidth()) {


                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(ticketResults) { it ->
                        it?.bus_details?.let {
                            busNumber = it.bus_number ?: ""
                            busName = it.name ?: ""
                            fromTo = it.from_to ?: ""
                            route = it.route ?: ""
                            busSpeed = it.bus_speed ?: ""

                        }
                        it?.ticket_details?.let {
                            VerifyTicketCard(
                                ticketNo = it.ticket_no ?: 0,
                                cost = it.cost ?: 0,
                                time = it.time ?: "",
                                date = it.date ?: "",
                                busNumber = busNumber,
                                busName = busName,
                                fromTo = fromTo,

                                )
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun VerifyTicketCard(
    busNumber: String?,
    busName: String?,
    fromTo: String?,
    ticketNo: Int?,
    cost: Any,
    date: Any,
    time: Any,
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
                text = "Bus Ticket NO: ${ticketNo.toString()}",
                fontSize = 16.sp, fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp)

            )

            TextView(
                text = "Bus Name: ${busName.toString()}",
                fontSize = 16.sp,
                modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp)
            )
            TextView(
                text = "Bus No: ${busNumber.toString()}",
                fontSize = 16.sp, fontWeight = FontWeight.Bold,
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
                text = "Cost: Rs${cost}",
                fontSize = 16.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp),

                )

            TextView(
                text = "Time: $time",
                fontSize = 16.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,

                modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp),

                )
            TextView(
                text = "CreatedDate: $date",
                fontSize = 16.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp),

                )
        }
    }
}
