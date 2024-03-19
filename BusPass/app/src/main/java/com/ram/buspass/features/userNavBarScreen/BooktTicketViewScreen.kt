package com.ram.buspass.features.userNavBarScreen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ram.buspass.features.components.ButtonView
import com.ram.buspass.features.components.SearchBar
import com.ram.buspass.ui.theme.Purple
import com.ram.buspass.ui.theme.White


@Composable
fun BookTicketCardDetails() {
    Column(modifier = Modifier.fillMaxWidth()) {
        TopAppBar(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Purple,
        ) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(
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
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
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


                    Text(
                        text = "Bus No:A01",
                        fontSize = 16.sp, fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp)
                    )
                    Text(
                        text = "From to :Kathmandu to Bhaktapur",
                        fontSize = 16.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp)

                    )

                    Text(
                        text = "Route:Ring road",
                        fontSize = 16.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp)

                    )
                    Text(
                        text = "Cost:Rs.75",
                        fontSize = 16.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp),

                        )
                    ButtonView(
                        onClick = {},
                        btnColor = ButtonDefaults.buttonColors(Color.Gray),
                        text = "Payment", textStyle = TextStyle(fontWeight = FontWeight.Bold)
                    )

                }
            }
        }
    }

}

