package com.ram.buspass.features.login.presentation

import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ram.buspass.R
import com.ram.buspass.features.components.ButtonView
import com.ram.buspass.features.components.ClickableTextView
import com.ram.buspass.features.components.IconView
import com.ram.buspass.features.components.InputTextFieldView
import com.ram.buspass.features.components.PainterImageView
import com.ram.buspass.features.components.PasswordTextFieldView
import com.ram.buspass.features.components.TextView
import com.ram.buspass.features.helper.resource.remote.api.model.login.AuthResponse
import com.ram.buspass.features.userNavigationBar.ScreenList
import com.ram.buspass.interfaceUtils.UserInterfaceUtil.Companion.showToast
import com.ram.buspass.ui.theme.Purple

@SuppressLint("SuspiciousIndentation")
@Composable
fun LoginViewScreen(
    navController: NavHostController,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var getSharedPreferences =
        context.getSharedPreferences("my_preferences", ComponentActivity.MODE_PRIVATE)
    val result = loginViewModel.login
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val checked = remember { mutableStateOf(false) }
    var isEmailEmpty by remember { mutableStateOf(false) }
    var isPasswordEmpty by remember { mutableStateOf(false) }

    if (result.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(1f)
        }
    }

    if (result.isError.isNotEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            // show error
            showToast(context, result.isError)
        }
    }

    LaunchedEffect(key1 = result.isData, block = {
        if (result.isData?.isSuccess == true) {
            val authentication = AuthResponse(
                accessToken = result.isData.authResponse?.accessToken,
                role = result.isData.authResponse?.role
            )
            when (result.isData.authResponse?.role) {
                "user" -> {
                    navController.navigate(ScreenList.BottomNavMenuUser.route) {
                        popUpTo(ScreenList.LoginScreen.route) {
                            inclusive = true
                        }
                    }
                }

                else -> {
                    navController.navigate(ScreenList.BottomNavMenuConductor.route) {
                        popUpTo(ScreenList.LoginScreen.route) {
                            inclusive = true
                        }
                    }
                }
            }
            val editSharedPreferences = getSharedPreferences.edit()
            editSharedPreferences.putString("authentication", "$authentication")
                .apply()
            showToast(context, "${result.isData.message}")

        }

    })

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PainterImageView(
                painterResource(id = R.mipmap.ic_login),
                modifier = Modifier.height(300.dp)
            )
            Spacer(modifier = Modifier.padding(5.dp))
            TextView(
                text = "Welcome Back!",
                style = TextStyle(fontWeight = FontWeight.Bold, color = Color.Gray),
                fontSize = 20.sp,
            )

            TextView(
                text = "Please login in to your account first",
                style = TextStyle(fontWeight = FontWeight.Bold, color = Color.Gray),
                fontSize = 18.sp,
            )
        }
        InputTextFieldView(
            value = email,
            onValueChange = { email = it },
            label = "Email",
            placeholder = "Enter Email",
            textStyle = TextStyle(),
            isEmpty = isEmailEmpty,
            invalidMessage = "The email is empty!",
            errorColor = Color.Red,
            leadingIcon = { IconView(imageVector = Icons.Default.Email) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        )

        PasswordTextFieldView(
            value = password,
            onValueChange = { password = it },
            label = "Password",
            placeholder = "Enter Password",
            textStyle = TextStyle(),
            isEmpty = isPasswordEmpty,
            invalidMessage = "The password is empty!",
            leadingIcon = { IconView(imageVector = Icons.Default.Lock) },
            errorColor = Color.Red,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = checked.value,
                    onCheckedChange = { isChecked -> checked.value = isChecked }, colors = CheckboxDefaults.colors( // Providing default colors for the checkbox
                        checkedColor = Purple,
                        uncheckedColor = Color.Gray,
                    )
                )
                TextView(text = "Remember Password")
            }
            ClickableTextView(
                text = AnnotatedString("Forgot Password?"),
                style = TextStyle(color = Purple),
                onClick = { navController.navigate(ScreenList.ForgotScreen.route) },
                modifier = Modifier.fillMaxWidth()
            )
        }
        ButtonView(
            onClick = {
                isEmailEmpty = email.isEmpty()
                isPasswordEmpty = password.isEmpty()
                if (email.isNotEmpty() && password.isNotEmpty()) {
                    loginViewModel.getLoginUser(email, password)
                }
            },
            btnColor = ButtonDefaults.buttonColors(Purple),
            text = "Login In",
            textStyle = TextStyle(Color.White, fontWeight = FontWeight.Bold),
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            TextView(text = "Don't have an account?", color = Color.Gray)
            Spacer(modifier = Modifier.padding(5.dp))
            ClickableTextView(
                text = AnnotatedString("Register Now"),
                style = TextStyle(color = Purple),
                onClick = { navController.navigate(ScreenList.RegisterScreen.route) },
                modifier = Modifier
                    .fillMaxWidth()
            )

        }

    }
}



