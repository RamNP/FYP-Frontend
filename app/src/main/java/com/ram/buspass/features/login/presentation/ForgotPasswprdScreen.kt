package com.ram.buspass.features.login.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ram.buspass.R
import com.ram.buspass.utils.components.IconView
import com.ram.buspass.utils.components.InputTextFieldView
import com.ram.buspass.utils.components.PainterImageView
import com.ram.buspass.utils.components.TextView
import com.ram.buspass.userNavigationBar.ScreenList

@Composable
fun ForgotViewScreen(navController: NavHostController) {
    var emailTextField by remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth()) {
            IconButton(onClick = { navController.navigate(ScreenList.LoginScreen.route) }) {
                IconView(imageVector = Icons.Default.ArrowBack)
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            PainterImageView(
                painter = painterResource(id = R.mipmap.ic_forgot),
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            TextView(
                text = "Forgot your password?", style = TextStyle(fontWeight = FontWeight.Bold),
                fontSize = 20.sp,
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, top = 30.dp, end = 10.dp)
        ) {
            TextView(
                text = "Don't worry! We are here to help you.Enter your email            " +
                        " address below to reset your password ",
                style = TextStyle(
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.padding(start = 10.dp, end = 10.dp)
            )
        }

        InputTextFieldView(
            value = emailTextField,
            onValueChange = { emailTextField = it },
            label = "",
            placeholder = "Enter  your email address",
            textStyle = TextStyle(),
            invalidMessage = "",
            leadingIcon = { IconView(imageVector = Icons.Default.Email) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .height(50.dp)
                    .width(200.dp),
                colors = ButtonDefaults.buttonColors()
            ) {
                TextView(text = "Reset Password")
            }
        }
    }
}