package com.ram.buspass.features.ticketBook.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
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
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import com.ram.buspass.features.components.TextView
import com.ram.buspass.interfaceUtils.UserInterfaceUtil


@Composable
fun TicketBookViewScreens(
    ticketBookViewModel: TicketBookViewModel = hiltViewModel(),
) {

    val ticketResult = ticketBookViewModel.ticket.value
    val bookTicketResult = ticketBookViewModel.bookTicket.value
    // Declaring a boolean value to store
    // the expanded state of the Text Field
    val context = LocalContext.current
    var busIdExpanded by remember { mutableStateOf(false) }
    var mExpanded by remember { mutableStateOf(false) }
    var busNumberExpanded by remember { mutableStateOf(false) }
    var busNameExpanded by remember { mutableStateOf(false) }
    var fromToExpanded by remember { mutableStateOf(false) }
    var busRouteExpanded by remember { mutableStateOf(false) }
    var ticketNumExpanded by remember { mutableStateOf(false) }
    var ticketDateExpanded by remember { mutableStateOf(false) }
    var ticketTimeExpanded by remember { mutableStateOf(false) }
    var ticketCostExpanded by remember { mutableStateOf(false) }
    var mTextFieldSize by remember { mutableStateOf(Size.Zero) }
    // Up Icon when expanded and down icon when collapsed
    val icon = if (mExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown
    var selectedBusId by remember { mutableStateOf(0) }

    LaunchedEffect(key1 = Unit, block = {
        ticketBookViewModel.getTicket()
    })

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
        val filteredData = result.filter {
            it.bus_details?.id == selectedBusId
        }

        val bus_Id = mutableListOf<Int>()
        val busNumber = mutableListOf<String>()
        val busName = mutableListOf<String>()
        val fromTo = mutableListOf<String>()
        val busRoute = mutableListOf<String>()
        val ticketNum = mutableListOf<Int>()
        val ticketDate = mutableListOf<String>()
        val ticketTime = mutableListOf<String>()
        val ticketCost = mutableListOf<Int>()
        result.forEach { label ->
            label.bus_details?.let { it ->
                bus_Id.add(it.id ?: 0)
                busNumber.add(it.bus_number ?: "")
                busName.add(it.name ?: "")
                fromTo.add(it.from_to ?: "")
                busRoute.add(it.route ?: "")

            }
            label.ticket_details?.let { it ->
                ticketNum.add(it.ticket_no ?: 0)
                ticketDate.add(it.date ?: "")
                ticketTime.add(it.time ?: "")
                ticketCost.add(it.cost ?: 0)
            }
        }
        var busIdText by remember { mutableStateOf(bus_Id.getOrNull(0).toString()) }
        var busNumberText by remember { mutableStateOf(busNumber.getOrNull(0).toString()) }
        var busNameText by remember { mutableStateOf(busName.getOrNull(0).toString()) }
        var fromText by remember { mutableStateOf(fromTo.getOrNull(0).toString()) }
        var routeText by remember { mutableStateOf(busRoute.getOrNull(0).toString()) }
        var ticketNumText by remember { mutableStateOf(ticketNum.getOrNull(0).toString()) }
        var ticketDateText by remember { mutableStateOf(ticketDate.getOrNull(0).toString()) }
        var ticketTimeText by remember { mutableStateOf(ticketTime.getOrNull(0).toString()) }
        var ticketCostText by remember { mutableStateOf(ticketCost.getOrNull(0).toString()) }
        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .verticalScroll(scrollState)
        ) {
            //busId
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

//                    Bus Number TextFiled
                OutlinedTextField(
                    value = busIdText,
                    onValueChange = { busIdText = it },
                    modifier = Modifier
                        .width(150.dp)
                        .padding(top = 16.dp)
                        .onGloballyPositioned { coordinates ->
                            mTextFieldSize = coordinates.size.toSize()
                        },
                    label = { Text(" Select Bus Id") },
                    trailingIcon = {
                        Icon(
                            icon,
                            "Icon",
                            Modifier.clickable { busIdExpanded = !busIdExpanded }
                        )
                    }
                )

                // Create a drop-down menu with list of bus details,
                // when clicked, set the Text Field text as the bus id selected
                DropdownMenu(
                    expanded = busIdExpanded,
                    onDismissRequest = { busIdExpanded = false },
                    modifier = Modifier
                        .width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })
                ) {
                    bus_Id.forEach { busId ->
                        DropdownMenuItem(
                            onClick = {
                                busIdText = busId.toString()
                                selectedBusId = busId
                                busIdExpanded = false
                                UserInterfaceUtil.showToast(context, "Bus ID : $selectedBusId")

                            }
                        ) {
                            Text(text = busId.toString())
                        }
                    }
                }
            }
        }
        filteredData.forEach { item ->
            Text(text = "Bus ID: ${item.bus_details?.bus_number}")
            Text(text = "Ticket Number: ${item.ticket_details?.ticket_no}")


        }
    }
}
