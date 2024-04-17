package com.ram.buspass.features.editProfile.presentation

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ButtonDefaults
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
import com.ram.buspass.utils.components.ButtonView
import com.ram.buspass.utils.components.IconView
import com.ram.buspass.utils.components.InputTextFieldView
import com.ram.buspass.utils.components.TextView
import com.ram.buspass.interfaceUtils.UserInterfaceUtil.Companion.showToast
import com.ram.buspass.ui.theme.Purple
import com.ram.buspass.ui.theme.White


@Composable
fun EditProfileViewScreen(
    navController: NavHostController,
    editProfileLocationViewModel: EditProfileLocationViewModel = hiltViewModel()
) {
    val visible by remember { mutableStateOf(true) }
    var userName by remember { mutableStateOf("") }
    val isNameEmpty by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("") }
    val isEmailEmpty by remember { mutableStateOf(false) }
     val editProfileResult = editProfileLocationViewModel.editProfile
    val context = LocalContext.current


    if (editProfileResult.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(1f)
        }
    }
    if (editProfileResult.isError.isNotEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            TextView(text = editProfileResult.isError)
        }
    }

    LaunchedEffect(key1 = editProfileResult.isData, block = {
        if (editProfileResult.isData?.is_success == true) {
            showToast(context, "${editProfileResult.isData.message}")

        }
    })

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
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "",
                modifier = Modifier.clickable { navController.navigateUp() })
            Text(text = " Edit Profile", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
            Icon(imageVector = Icons.Default.Share, contentDescription = "")
        }

        Column(
            modifier = Modifier
                .fillMaxWidth(), verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
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

                Image(
                    painter = painterResource(id = R.mipmap.ic_profile),
                    contentDescription = null
                )
            }
            //
            AnimatedVisibility(visible, modifier = Modifier

                .clickable {})
            {
                Text(
                    text = "Change picture", fontSize = 18.sp
                )
            }
        }
        //
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            InputTextFieldView(
                value = email ,
                onValueChange = { email = it },
                label = "Email",
                placeholder = "Enter Email",
                textStyle = TextStyle(),
                isEmpty = isEmailEmpty,
                invalidMessage = "The Email is empty!",
                errorColor = Color.Red,
                leadingIcon = { IconView(imageVector = Icons.Default.Email) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)

            )

            InputTextFieldView(
                value = userName,
                onValueChange = { userName = it },
                label = "UserName",
                placeholder = "Enter UserName",
                textStyle = TextStyle(),
                isEmpty = isNameEmpty,
                invalidMessage = "The UserName is empty!",

                errorColor = Color.Red,

                leadingIcon = { IconView(imageVector = Icons.Default.Person) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            )


            ButtonView(
                onClick = {

                    editProfileLocationViewModel.getUserEditProfile(email ,userName)
//                    showToast(context, "${editProfileResult.isData?.message}")
                    navController.navigate("Profile")

                },

                btnColor = ButtonDefaults.buttonColors(Purple),
                text = "Update",
                textStyle = TextStyle()
            )

        }
    }
}

