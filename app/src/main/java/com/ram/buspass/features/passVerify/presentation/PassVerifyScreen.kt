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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ram.buspass.features.components.TextView
import com.ram.buspass.ui.theme.White

@Composable
fun PassVerifyViewScreen(
    navController: NavHostController,
    passVerifyViewModel: PassVerifyViewModel = hiltViewModel()
) {
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







    LaunchedEffect(key1 = Unit, block = {
        passVerifyViewModel.getPassVerify()
    })

    if (passResult.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            // indicator
            CircularProgressIndicator(1f)
        }
    }
    if (passResult.isError != null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            // error message
            TextView(text = passResult.isError)
        }
    }
    passResult.isData?.data?.let { passResults ->
        Column(modifier = Modifier.fillMaxWidth()) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(White)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                TextView(
                    text = "Pass Verify ",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.Black, modifier = Modifier.padding(end = 80.dp)
                )
            }
        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp, bottom = 40.dp)) {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(passResults) { it ->
                    it?.bus_details?.let {
                        busNumber = it.bus_number ?: ""
                        busName = it.name ?: ""
                        fromTo = it.from_to ?: ""
                        route = it.route ?: ""
                        busSpeed = it.bus_speed ?: ""

                    }
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

                        VerifyPassCard(
                            id = it.id,
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
                            time = time
                        )

                    }
                }
            }
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
                text = "Status Id: ${id.toString()}",
                fontSize = 16.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp)

            )

            TextView(
                text = "Bus Ticket NO: ${ticketNo.toString()}",
                fontSize = 16.sp, fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
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
                text = "Bus No: ${busNumber.toString()}",
                fontSize = 16.sp,
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


        }
    }
}