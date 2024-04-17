package com.ram.buspass.features.register.presentation

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ram.buspass.R
import com.ram.buspass.utils.components.ButtonView
import com.ram.buspass.utils.components.IconView
import com.ram.buspass.utils.components.InputTextFieldView
import com.ram.buspass.utils.components.PainterImageView
import com.ram.buspass.utils.components.PasswordTextFieldView
import com.ram.buspass.utils.components.TextView
import com.ram.buspass.interfaceUtils.UserInterfaceUtil.Companion.showToast
import com.ram.buspass.ui.theme.Purple
import com.ram.buspass.userNavigationBar.ScreenList
import com.ram.buspass.utils.NetworkObserver
import com.ram.buspass.utils.Validators.isValidEmailAddress
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun RegisterViewScreen(
    navHostController: NavHostController,
    registerViewModel: RegisterViewModel = hiltViewModel()
) {

    val getRegister = registerViewModel.register
    val connection by NetworkObserver.connectivityState()
    val isConnected = connection === NetworkObserver.ConnectionState.Available
    val context = LocalContext.current
    var role by remember { mutableStateOf("") }
    val isRoleEmpty by remember { derivedStateOf { role.isEmpty() } }

    var userName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isNameEmpty by remember { mutableStateOf(false) }
    var isPasswordEmpty by remember { mutableStateOf(false) }
    var mExpanded by remember { mutableStateOf(false) }
    val users = listOf("User", "Conductor")
    var isValidEmail by remember { mutableStateOf(false) }
    var roleEmptyValue by remember { mutableStateOf(false) }



    var mTextFieldSize by remember { mutableStateOf(Size.Zero) }
    val icon = if (mExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown


//    val onClick: () -> Unit = {
//        MainScope().launch {
//            registerViewModel.getRegisterUser(email, userName, password, role)
//        }
//    }


    // register button onClickAction
    val onClick = {
        if (isConnected) {
            MainScope().launch {
                if (isValidEmailAddress(email)) {
                    registerViewModel.getRegisterUser(email, userName, password, role)
                } else {
                    isValidEmail = true
                }
            }
        } else {
            showToast(context, "No Internet connection")
        }
    }

    if (getRegister.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//            showToast(context, "Loading")
            CircularProgressIndicator(1f)
        }
    }

    LaunchedEffect(key1 = getRegister.isError, block = {
        if (getRegister.isError.isNotEmpty()) {
            showToast(context, getRegister.isError)
            Log.e("isErrorRes:","$getRegister.isError")
        }
    })

    LaunchedEffect(getRegister.isData) {
        if (getRegister.isData?.is_success == true) {
            showToast(context, "${getRegister.isData.message}")
            Log.e("messageSus:", "${getRegister.isData.message}")
            navHostController.navigate(ScreenList.LoginScreen.route)

        }
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            IconButton(onClick = { navHostController.navigate(ScreenList.LoginScreen.route) }) {
                IconView(imageVector = Icons.Default.ArrowBack)
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                PainterImageView(
                    painterResource(id = R.mipmap.ic_register),
                    modifier = Modifier.height(250.dp)

                )
                Spacer(modifier = Modifier.padding(5.dp))
                TextView(
                    text = "Create an new account free",
                    style = TextStyle(fontWeight = FontWeight.Bold, color = Color.Gray),
                    fontSize = 20.sp,
                )


            }
//            InputTextFieldView(
//                value = role,
//                onValueChange = { role = it },
//                label = "Role",
//                placeholder = "Enter Role",
//                textStyle = TextStyle(),
//                isError = roleEmptyValue,
//                invalidMessage = "Role Text field is Empty!",
//                errorColor = Color.Red,
//                leadingIcon = { IconView(imageVector = Icons.Default.Person) },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(5.dp)
//            )


            Column(
                modifier = Modifier.fillMaxWidth().padding(5.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                InputTextFieldView(
                    value = role,
                    onValueChange = { role = it },
                    label = "Role",
                    trailingIcon = {
                        Icon(icon, "contentDescription",
                            Modifier.clickable { mExpanded = !mExpanded })
                    },

                    modifier = Modifier
                        .onGloballyPositioned { coordinates ->
                            mTextFieldSize = coordinates.size.toSize()
                        }
                        .fillMaxWidth(),
                    placeholder = " Enter Role",
                    textStyle = TextStyle(),
                    invalidMessage = "",
                )

                DropdownMenu(
                    expanded = mExpanded,
                    onDismissRequest = { mExpanded = false },
                    modifier = Modifier
                        .width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })
                ) {
                    users.forEach { label ->
                        DropdownMenuItem(onClick = {
                            role = label
                            mExpanded = false
                        }) {
                            Text(text = label)
                        }
                    }
                }
            }

            InputTextFieldView(
                value = userName,
                onValueChange = { userName = it },
                label = "UserName",
                placeholder = "Enter UserName",
                textStyle = TextStyle(),
                isError = isNameEmpty,
                invalidMessage = "UserName Text field is Empty!",
                errorColor = Color.Red,
                leadingIcon = { IconView(imageVector = Icons.Default.Person) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            )
            InputTextFieldView(
                value = email,
                onValueChange = { email = it },
                label = "Email",
                placeholder = "Enter Email",
                textStyle = TextStyle(),
                isError = isValidEmail,
                invalidMessage = "Enter a valid email.",
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
                invalidMessage = "Password Text field is Empty!",
                errorColor = Color.Red,
                leadingIcon = { IconView(imageVector = Icons.Default.Lock) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            )


//            ButtonView(
//                onClick = {
//                    isNameEmpty = role.isEmpty()
//                    isEmailEmpty = email.isEmpty()
//                    isPasswordEmpty = password.isEmpty()
//                    if (role.isNotEmpty() || email.isNotEmpty() || password.isNotEmpty()) {
//                        onClick.invoke()
//                        navHostController.navigate(ScreenList.LoginScreen.route)
//                    } else {
//                        showToast(context, "The above field is empty!")
//                    }
//                },
//                btnColor = ButtonDefaults.buttonColors(Purple),
//                text = "Register now",
//                textStyle = TextStyle(Color.White, fontWeight = FontWeight.Bold),
//                modifier = Modifier.fillMaxWidth()
//            )

            ButtonView(
                onClick = {
                    isNameEmpty = userName.isEmpty() // isNameEmpty // name error message
                    isValidEmail = isValidEmail // email error message
                    isPasswordEmpty  = password.isEmpty() // password error message
                    roleEmptyValue = isRoleEmpty
                    if (userName.isNotEmpty() && !isValidEmail && !isPasswordEmpty && !isRoleEmpty) {
                        onClick()
                    }
                },
                btnColor = ButtonDefaults.buttonColors(Purple),
                text = "Register now",
                textStyle = TextStyle(Color.White, fontWeight = FontWeight.Bold),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}




