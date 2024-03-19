package com.ram.buspass.features.ticketBook.presentation

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
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BusAlert
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Paid
import androidx.compose.material.icons.filled.Route
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ram.buspass.features.components.ButtonView
import com.ram.buspass.features.components.IconView
import com.ram.buspass.features.components.SearchBar
import com.ram.buspass.features.components.TextView
import com.ram.buspass.ui.theme.Purple
import com.ram.buspass.ui.theme.White

@Composable
fun TicketBookViewScreen(
    navController: NavHostController,
    ticketBookViewModel: TicketBookViewModel = hiltViewModel()
) {
//    val context = LocalContext.current
    val ticketResult = ticketBookViewModel.ticket.value
    LaunchedEffect(key1 = Unit, block = {
        ticketBookViewModel.getTicket(3)
    })
    var cost by remember { mutableStateOf(0) }
    if (ticketResult.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            // indicator
            CircularProgressIndicator(1f)
        }
    }
    if (ticketResult.isError.isNotBlank()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            // error message
            TextView(text = ticketResult.isError)
        }
    }
    ticketResult.isData?.let { result ->

        Column(modifier = Modifier.fillMaxWidth()) {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = Purple,
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    TextView(
                        text = "Book Ticket",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.White
                    )
                }
            }
            SearchBar(hint = "Search...") { searchText ->
            }
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(result) { it ->
                        it.ticket_details?.let {
                            cost = it.cost ?: 0
                        }
                        it.bus_details?.let {
                            TicketCard(
                                busNumber = it.bus_number,
                                busName = it.name,
                                fromTo = it.from_to,
                                route = it.route,
                                cost = cost,
                                onClickAction = {}
                            )
                        }


                    }
                }
            }
        }
//let
    }
}


@Composable
fun TicketCard(
    busNumber: String?,
    busName: String?,
    fromTo: String?,
    route: String?,
    cost: Any,
    onClickAction: () -> Unit,
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
            ButtonView(
                onClick = {},
                btnColor = ButtonDefaults.buttonColors(Color.Gray),
                text = "Payment", textStyle = TextStyle(fontWeight = FontWeight.Bold)
            )

        }
    }
}


@Composable
fun ViewTicketCard(
    busNumber: String?,
    busName: String?,
    fromTo: String?,
    route: String?,
    cost: Any,
    date: Any,
    ticketNo: Int

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


            TextView(
                text = "Bus Name: ${busName.toString()}",
                fontSize = 16.sp,
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
                text = "Route: ${route.toString()}",
                fontSize = 16.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp)

            )
            TextView(
                text = "Ticket No:${ticketNo}",
                fontSize = 16.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp),

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
                text = "CreatedDate:${date}",
                fontSize = 16.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp),

                )

        }
    }
}

