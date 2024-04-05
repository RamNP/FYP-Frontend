package com.ram.buspass.features.profile


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.ManageAccounts
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material.icons.filled.PrivacyTip
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ram.buspass.R
import com.ram.buspass.ui.theme.Purple
import com.ram.buspass.ui.theme.White


@Composable
fun ProfileViewScreen(navController: NavHostController) {
//    val navController = rememberNavController()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),

        ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Purple)
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Profile",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        }
        Divider(modifier = Modifier.fillMaxWidth())

        ProfileCard(
            personName = "Ram Pariyar",
            location = "Islington College",
            email = "Pariyarram@gmail.com",
            userId = 2,
            role = "User"
        )
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                White
            ),
        ) {
            Text(
                text = "More Options",
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp),
                modifier = Modifier.padding(top = 16.dp, start = 16.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = " Edit Profile",
                        modifier = Modifier.padding(top = 8.dp),
                        style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium)
                    )
                    IconButton(onClick = { navController.navigate("EditProfileScreen") }) {
                        Icon(imageVector = Icons.Default.Edit, contentDescription = "Icon")
                    }

                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Manage Account",
                        modifier = Modifier.padding(top = 8.dp),
                        style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium)
                    )
                    IconButton(onClick = { navController.navigate("ManagePasswordScreen") }) {
                        Icon(imageVector = Icons.Default.ManageAccounts, contentDescription = null)
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Privacy  Policy",
                        modifier = Modifier.padding(top = 8.dp),
                        style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium)
                    )
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Default.PrivacyTip, contentDescription = null)
                    }


                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "About",
                        modifier = Modifier.padding(top = 8.dp),
                        style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium)
                    )
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Default.Info, contentDescription = null)
                    }


                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = " Help & Feedback",
                        modifier = Modifier.padding(top = 8.dp),
                        style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium)
                    )
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Default.Help, contentDescription = null)
                    }


                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Share",
                        modifier = Modifier.padding(top = 8.dp),
                        style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium)
                    )
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Default.Share, contentDescription = null)
                    }

                }
            }
        }
    }
}


@Composable
fun ProfileCard(
    personName: String?,
    location: String?,
    email: String?,
    role: String?,
    userId: Int?

) {

    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            White
        ),

        ) {
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .width(100.dp)
                    .height(100.dp)
                    .clickable { },
                shape = RoundedCornerShape(150.dp),
                colors = CardDefaults.cardColors(Purple),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {

                    Image(
                        painter = painterResource(id = R.mipmap.ic_bus),
                        contentDescription = null
                    )

                }

            }
            Text(
                text = "$personName",
                fontSize = 20.sp, fontWeight = FontWeight.Bold
            )

        }
        Column(
            modifier = Modifier.fillMaxWidth().padding( start = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween

                ) {
                Icon(imageVector = Icons.Default.PersonOutline, contentDescription = "")
                Text(text = "$userId", modifier = Modifier.padding(top = 2.dp))



                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Icon(imageVector = Icons.Default.People, contentDescription = "")
                    Text(text = "$role", modifier = Modifier.padding(top = 4.dp, start = 4.dp))

                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()

            ) {
                Icon(imageVector = Icons.Default.LocationOn, contentDescription = "")
                Text(text = "$location", modifier = Modifier.padding(top = 2.dp))



                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp)
                ) {
                    Icon(imageVector = Icons.Default.Email, contentDescription = "")
                    Text(text = "$email", modifier = Modifier.padding(top = 4.dp, start = 4.dp))
                }
            }
        }
    }
}