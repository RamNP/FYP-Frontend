package com.ram.buspass.features.profile.presentation


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material.icons.filled.PrivacyTip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ram.buspass.R
import com.ram.buspass.helper.resource.remote.api.ApiConstants.BASE_URL
import com.ram.buspass.interfaceUtils.UserInterfaceUtil.Companion.showToast
import com.ram.buspass.ui.theme.Purple
import com.ram.buspass.ui.theme.White
import com.ram.buspass.userNavigationBar.ScreenList
import com.ram.buspass.userNavigationBar.UserScreen
import com.ram.buspass.utils.ClientInterceptor
import com.ram.buspass.utils.NetworkObserver
import com.ram.buspass.utils.components.TextView


@Composable
fun ProfileViewScreen(
    navController: NavHostController,
    maiNavController: NavHostController,
    profileViewModel: ProfileViewModel = hiltViewModel()
    ) {
    val context = LocalContext.current
    val userId = ClientInterceptor(context).getUserId()
    val editor = ClientInterceptor(context).getPreInstEditor()

    val profileResult = profileViewModel.profile
    val connection by NetworkObserver.connectivityState()
    val isConnected = connection === NetworkObserver.ConnectionState.Available
    var showDialogBox by remember { mutableStateOf(false) }



    LaunchedEffect(key1 = Unit, block = {
        profileViewModel.getUserProfile()
    })

    if (profileResult.isLoading) {
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
            }        }
    }
    if (profileResult.isError != null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            TextView(text = profileResult.isError)
        }
    }
    profileResult.isData?.user_profile.let { results ->

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),

            ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(White)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Profile",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
            }

            ProfileCard(
                personName = results?.username,
                address = results?.address,
                email = results?.email,
                userId = userId,
                role = results?.role,
                imageUrl = BASE_URL + results?.photo_image

            )

        }
        Column(modifier = Modifier.fillMaxWidth().padding(top = 250.dp)) {

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
                    TextView(
                        text = " Edit Profile",
                        modifier = Modifier.padding(top = 8.dp),
                        style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium)
                    )
                    IconButton(onClick = { navController.navigate("EditProfile") }) {
                        Icon(imageVector = Icons.Default.Edit, contentDescription = "Icon")
                    }

                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextView(
                        text = "Change Password ",
                        modifier = Modifier.padding(top = 8.dp),
                        style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium)
                    )
                    IconButton(onClick = { navController.navigate("ChangePassword") }) {
                        Icon(imageVector = Icons.Default.Lock, contentDescription = null)
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextView(
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
                    TextView(
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
                    TextView(
                        text = "Logout",
                        modifier = Modifier.padding(top = 8.dp),
                        style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium)
                    )
                    IconButton(onClick = {
                        maiNavController.navigate(ScreenList.LoginScreen.route) {
                            popUpTo(UserScreen.Profile.route) {
                                inclusive = true
                            }
                            editor.putString("authentication", "")?.apply()
                            showDialogBox = false
                            showToast(context, ("Logout is successfully"))
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Logout, contentDescription = null)
                    }

                }
            }
        }
        }

    }

}


@Composable
fun ProfileCard(
    personName: String?,
    address: String?,
    email: String?,
    role: String?,
    userId: Int?,
    imageUrl: String,

    ) {

    Card(
        modifier = Modifier
            .padding(16.dp)
            .padding(bottom = 10.dp)
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
                        painter = painterResource(id = R.mipmap.ic_profile),
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween

            ) {
                Icon(imageVector = Icons.Default.PersonOutline, contentDescription = "")
                Text(text = "User Id: $userId", modifier = Modifier.padding(top = 2.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth().padding(start = 10.dp)
                ) {
                    Icon(imageVector = Icons.Default.People, contentDescription = "")
                    Text(
                        text = " User role: $role",
                        modifier = Modifier.padding(top = 4.dp, start = 4.dp)
                    )

                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()

            ) {
                Icon(imageVector = Icons.Default.LocationOn, contentDescription = "")
                Text(text = "$address", modifier = Modifier.padding(top = 2.dp))



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