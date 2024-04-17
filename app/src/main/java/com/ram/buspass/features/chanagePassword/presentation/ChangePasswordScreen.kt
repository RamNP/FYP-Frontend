package com.ram.buspass.features.chanagePassword.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ram.buspass.utils.components.ButtonView
import com.ram.buspass.utils.components.IconView
import com.ram.buspass.utils.components.InputTextFieldView
import com.ram.buspass.utils.components.PasswordTextFieldView
import com.ram.buspass.utils.components.TextView
import com.ram.buspass.interfaceUtils.UserInterfaceUtil.Companion.showToast
import com.ram.buspass.ui.theme.Purple
import com.ram.buspass.ui.theme.White


@Composable
fun ChangePasswordScreen(
    navController: NavHostController,
    changePasswordViewModel: ChangePasswordViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val isEmailEmpty by remember { mutableStateOf(false) }
    val isOldPasswordEmpty by remember { mutableStateOf(false) }
    val isNewPasswordEmpty by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("") }
    var oldPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    val passwordResult = changePasswordViewModel.password

    if (passwordResult.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(1f)
        }
    }
    if (passwordResult.isError.isNotEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            TextView(text = passwordResult.isError)
        }
    }

    LaunchedEffect(key1 = passwordResult.isData, block = {
        if (passwordResult.isData?.is_success == true) {
            showToast(context, "${passwordResult.isData.message}")

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
                modifier = Modifier.clickable { navController.navigate("Profile") })
            Text(
                text = "Change Password",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(end = 80.dp)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            InputTextFieldView(
                value = email,
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
            PasswordTextFieldView(
                value = oldPassword,
                onValueChange = { oldPassword = it },
                label = "Old Password",
                placeholder = "Enter Password",
                textStyle = TextStyle(),
                isEmpty = isOldPasswordEmpty,
                invalidMessage = "The old password is empty!",
                leadingIcon = { IconView(imageVector = Icons.Default.Lock) },
                errorColor = Color.Red,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            )

            PasswordTextFieldView(
                value = newPassword,
                onValueChange = { newPassword = it },
                label = " New Password",
                placeholder = "Enter Password",
                textStyle = TextStyle(),
                isEmpty = isNewPasswordEmpty,
                invalidMessage = "The new password is empty!",
                leadingIcon = { IconView(imageVector = Icons.Default.Lock) },
                errorColor = Color.Red,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            )


            ButtonView(
                onClick = {
                    changePasswordViewModel.getPassword(email, oldPassword, newPassword)
                    showToast(context, "${passwordResult.isData?.message}")
                    navController.navigate("Profile")

                },
                btnColor = ButtonDefaults.buttonColors(Purple),
                text = "Save",
                textStyle = TextStyle()
            )


        }
    }
}