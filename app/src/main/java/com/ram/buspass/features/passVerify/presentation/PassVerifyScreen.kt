package com.ram.buspass.features.passVerify.presentation

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
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ram.buspass.interfaceUtils.UserInterfaceUtil.Companion.showToast
import com.ram.buspass.ui.theme.Purple
import com.ram.buspass.ui.theme.White
import com.ram.buspass.utils.NetworkObserver
import com.ram.buspass.utils.components.SearchView
import com.ram.buspass.utils.components.TextView
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun PassVerifyViewScreen(
    navController: NavHostController,
    passVerifyViewModel: PassVerifyViewModel = hiltViewModel()
) {
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = passVerifyViewModel.isRefresh)
    val context = LocalContext.current
    val connection by NetworkObserver.connectivityState()
    val isConnected = connection === NetworkObserver.ConnectionState.Available
    val passResult = passVerifyViewModel.passVerify
    var busNumber by remember { mutableStateOf("") }
    var busName by remember { mutableStateOf("") }
    var fromTo by remember { mutableStateOf("") }
    var route by remember { mutableStateOf("") }
    var busSpeed by remember { mutableStateOf("") }
    var ticketNo by remember { mutableStateOf(0) }
    var ticketDate by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }

    var cost by remember { mutableStateOf(0) }
    var userId by remember { mutableStateOf(0) }
    var userName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var passId by remember { mutableStateOf(0) }

    val textState = remember {
        mutableStateOf(TextFieldValue(""))
    }
    val searchedText = textState.value.text


    LaunchedEffect(key1 = Unit, block = {
        passVerifyViewModel.getPassVerify()
    })
//    SwipeRefresh(
//        state = swipeRefreshState,
//        onRefresh = { passVerifyViewModel.loadStuff() }, // Corrected function call
//
//    ) {
        if (passResult.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextView(
                        text = "This page is Opening.",
                        style = TextStyle(color = Color.Gray, fontSize = 18.sp),
                    )
                    CircularProgressIndicator(1f, modifier = Modifier, color = Purple)
                }
            }
        }
        if (passResult.isError != null) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                // error message
                TextView(text = passResult.isError)
            }
        }

        passResult.isData?.data?.let { passResults ->


            if (isConnected) {
                Column(modifier = Modifier.fillMaxWidth()) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(White)
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Pass Verify",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp, bottom = 40.dp)

                ) {
                    SearchView(state = textState, placeHolder = "Search here...", modifier = Modifier)


                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        val filteredTickets = passResults.filter {
                            it?.bus_details?.bus_number?.contains(searchedText, ignoreCase = true) == true
                        }
                        items(filteredTickets) { it ->
                            busNumber = it?.bus_details?.bus_number ?: ""
                            busName = it?.bus_details?.name ?: ""
                            fromTo = it?.bus_details?.from_to ?: ""
                            route = it?.bus_details?.route ?: ""
                            busSpeed = it?.bus_details?.bus_speed ?: ""

                            it?.ticket_details?.let {
                                ticketNo = it.ticket_no ?: 0
                                ticketDate = it.date ?: ""
                                cost = it.cost ?: 0
                                time = it.time ?: ""

                            }

                            it?.user_details?.let {
                                userId = it.id ?: 0
                                userName = it.username ?: ""
                                email = it.email ?: ""

                            }

                            it?.pass_details?.let {
                                passId = it.id ?: 0

                                VerifyPassCard(
                                    id = passId,
                                    status = it.status,
                                    passDate = it.created_date,
                                    userId = userId,
                                    userName = userName,
                                    email = email,
                                    busNumber = busNumber,
                                    busName = busName,
                                    fromTo = fromTo,
                                    route = route,
                                    busSpeed = busSpeed,
                                    ticketNo = ticketNo,
                                    cost = cost,
                                    date = ticketDate,
                                    time = time,
                                    onClickAction = {
                                        if (it.status == "ACTIVE") {
                                            showToast(context, "Pass is already activated")
                                        } else {
                                            showToast(context, "ID: ${it.id ?: 0}")
                                            passVerifyViewModel.getVerifyStatus(passId)
                                            showToast(context, "${passResult.isData.message}")

                                        }
                                    }
                                )

                            }
                        }
                    }
                }
            } else {
                TextView(text = "No Internet Net")
            }
        }

    }



@Composable
fun VerifyPassCard(
    id: Int?,
    status: String?,
    passDate: String?,
    userId: Int?,
    userName: String?,
    email: String?,
    busNumber: String?,
    busName: String?,
    fromTo: String?,
    route: String?,
    busSpeed: String?,
    ticketNo: Int?,
    cost: Any,
    date: Any,
    time: Any,
    onClickAction: () -> Unit,

    ) {
    val checked = remember { mutableStateOf(false) }

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
                text = "Pass Id: ${id.toString()}",
                fontSize = 16.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp)

            )


            TextView(
                text = "Bus No: ${busNumber.toString()}",
                fontSize = 16.sp, fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp)
            )
            TextView(
                text = "User Name: ${userName.toString()}",
                fontSize = 16.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp)

            )
            TextView(
                text = "Pass Date: ${passDate.toString()}",
                fontSize = 16.sp, fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp)
            )
            TextView(
                text = "Bus Name: ${busName.toString()}",
                fontSize = 16.sp,
                modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp)
            )
                TextView(
                    text = "Pass Status: ${status.toString()}",
                    fontSize = 16.sp, fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp)
                )


            TextView(
                text = "Bus Ticket NO: ${ticketNo.toString()}",
                fontSize = 16.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp)

            )
            TextView(
                text = "Email: $email",
                fontSize = 16.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp),

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
                text = "User Id: $userId",
                fontSize = 16.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp),
            )

            TextView(
                text = "Route: $route",
                fontSize = 16.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp),
            )



            TextView(
                text = "Bus Speed: $busSpeed",
                fontSize = 16.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,

                modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp),
            )

            TextView(
                text = "Ticket Date: $date",
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

            Button(onClick = { onClickAction() } , colors = ButtonDefaults.buttonColors(Purple)) {
                Text(text = "Status")
            }
        }
    }
}
