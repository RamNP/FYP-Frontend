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
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ram.buspass.ui.theme.Purple
import com.ram.buspass.ui.theme.White
import com.ram.buspass.utils.components.SearchView
import com.ram.buspass.utils.components.TextView


@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("SuspiciousIndentation")
@Composable
fun VerifyTicketViewScreen(
    navController: NavHostController,
    verifyTicketViewModel: VerifyTicketViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val pullRefreshState = rememberPullRefreshState(
        refreshing = verifyTicketViewModel.isRefresh,
        onRefresh = { verifyTicketViewModel.loadStuff() }
    )

    val textState = remember {
        mutableStateOf(TextFieldValue(""))
    }

    val searchedText = textState.value.text
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
            SearchView(state = textState, placeHolder = "Search here...", modifier = Modifier)

            Column(modifier = Modifier.fillMaxWidth().padding(bottom = 30.dp)) {


//                Box(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .pullRefresh(pullRefreshState),
//                    contentAlignment = Alignment.TopCenter
//                ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val filteredTickets = ticketResults.filter {
                        it?.bus_details?.bus_number?.contains(searchedText, ignoreCase = true) == true
                    }

                    items(filteredTickets) { ticket ->
                        busNumber = ticket?.bus_details?.bus_number ?: ""
                        busName = ticket?.bus_details?.name ?: ""
                        fromTo = ticket?.bus_details?.from_to ?: ""
                        route = ticket?.bus_details?.route ?: ""
                        busSpeed = ticket?.bus_details?.bus_speed ?: ""

                        ticket?.ticket_details?.let { ticketDetails ->
                            VerifyTicketCard(
                                ticketNo = ticketDetails.ticket_no ?: 0,
                                cost = ticketDetails.cost ?: 0,
                                time = ticketDetails.time ?: "",
                                date = ticketDetails.date ?: "",
                                busNumber = busNumber,
                                busName = busName,
                                fromTo = fromTo
                            )
                        }
                    }
                }

//                    PullRefreshIndicatorView(
//                        modifier = Modifier.align(Alignment.TopCenter),
//                        refreshing = verifyTicketViewModel.isRefresh,
//                        state = pullRefreshState
//                    )
                }
            }
        }
    }


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PullRefreshIndicatorView(
    modifier: Modifier,
    refreshing: Boolean,
    state: PullRefreshState,
    backgroundColor: Color = Color.White,
    contentColor: Color = Purple,
    scale: Boolean = false,
) {
    PullRefreshIndicator(
        refreshing = refreshing,
        state = state,
        modifier = modifier,
        contentColor = contentColor,
        backgroundColor = backgroundColor,
        scale = scale
    )
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
