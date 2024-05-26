package com.ram.buspass.features.forgetPassword.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ram.buspass.R
import com.ram.buspass.interfaceUtils.UserInterfaceUtil
import com.ram.buspass.ui.theme.Purple
import com.ram.buspass.userNavigationBar.ScreenList
import com.ram.buspass.utils.components.ButtonView
import com.ram.buspass.utils.components.IconView
import com.ram.buspass.utils.components.InputTextFieldView
import com.ram.buspass.utils.components.PainterImageView
import com.ram.buspass.utils.components.TextView

@Composable
fun ForgotViewScreen(
    navController: NavHostController,
    forgotPasswordViewModel: ForgotPasswordViewModel = hiltViewModel()
) {

    var emailTextField by remember { mutableStateOf("") }
    var newPasswordTextField by remember { mutableStateOf("") }

    val context = LocalContext.current
    val updatePasswordResult = forgotPasswordViewModel.forgotPasswordState


    if (updatePasswordResult.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(1f)
        }
    }
    if (updatePasswordResult.isError.isNotEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            TextView(text = updatePasswordResult.isError)
        }
    }

    LaunchedEffect(key1 = updatePasswordResult.isData, block = {
        if (updatePasswordResult.isData?.is_success == true) {
            UserInterfaceUtil.showToast(context, "${updatePasswordResult.isData.message}")

        }
    })
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


        InputTextFieldView(
            value = newPasswordTextField,
            onValueChange = { newPasswordTextField = it },
            label = "",
            placeholder = "Enter Your New Password",
            textStyle = TextStyle(),
            invalidMessage = "",
            leadingIcon = { IconView(imageVector = Icons.Default.Password) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {

            ButtonView(
                onClick = {
                    forgotPasswordViewModel.setForgotPassword(emailTextField, newPasswordTextField)
                    UserInterfaceUtil.showToast(context, "Your password has been successfully updated")
                    navController.navigate("LoginScreen")
                },
                btnColor = ButtonDefaults.buttonColors(Purple),
                text = "Reset Password",
                textStyle = TextStyle()
            )
        }
    }
}