package com.ram.buspass.features.updateBusLocation.presentation

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ram.buspass.features.components.ButtonView
import com.ram.buspass.features.components.IconView
import com.ram.buspass.features.components.InputTextFieldView
import com.ram.buspass.interfaceUtils.UserInterfaceUtil.Companion.showToast
import com.ram.buspass.ui.theme.Purple

@Composable
fun BusLocationViewScreen(
    navController: NavHostController,
    updateBusLocationViewModel: UpdateBusLocationViewModel = hiltViewModel()
) {
    val getBusLocation = updateBusLocationViewModel.busLocation
    val context = LocalContext.current
    var busNumber by remember { mutableStateOf("") }
    var latitude by remember { mutableStateOf(0f) }
    var longitude by remember { mutableStateOf(0f) }
    var isBusNumberEmpty by remember { mutableStateOf(false) }
    var isLatitudeEmpty by remember { mutableStateOf(false) }
    var isLongitudeEmpty by remember { mutableStateOf(false) }
    var mExpanded by remember { mutableStateOf(false) }



    if (getBusLocation.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(1f)
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
            Log.e("is_success" ,"${getBusLocation.isData.message}" )


        }
    })
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        InputTextFieldView(
            value = busNumber,
            onValueChange = { busNumber = it },
            label = "Bus Number",
            placeholder = "Enter BusNumber",
            textStyle = TextStyle(),
            isError = isBusNumberEmpty,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),

            invalidMessage = "Bus Number Text field is Empty!",
            errorColor = Color.Red,
            leadingIcon = { IconView(imageVector = Icons.Default.Person) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        )

        InputTextFieldView(
            value = latitude.toString(),
            onValueChange = { latitude= it.toFloat() },
            label = "Bus Latitude",
            placeholder = "Enter Latitude",
            textStyle = TextStyle(),
            isError = isLatitudeEmpty,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            invalidMessage = " Latitude Text field is Empty!",
            errorColor = Color.Red,
            leadingIcon = { IconView(imageVector = Icons.Default.Person) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        )

        InputTextFieldView(
            value = longitude.toString(), // Convert longitude to String
            onValueChange = { longitude = it.toFloat()},
            label = "Bus Longitude",
            placeholder = "Enter Longitude",
            textStyle = TextStyle(),
            isError = isLongitudeEmpty,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            invalidMessage = " Longitude Text field is Empty!",
            errorColor = Color.Red,
            leadingIcon = { IconView(imageVector = Icons.Default.Person) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        )

        ButtonView(
            onClick = {
                updateBusLocationViewModel.getBusLocation(busNumber, latitude, longitude)

            },
            btnColor = ButtonDefaults.buttonColors(Purple),
            text = "Update",
            textStyle = TextStyle()
        )

    }

}
