package com.ram.buspass.features.editProfile.presentation

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material.icons.filled.LocationOn
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.ram.buspass.helper.ClientInterceptor
import com.ram.buspass.interfaceUtils.UserInterfaceUtil.Companion.showToast
import com.ram.buspass.ui.theme.Purple
import com.ram.buspass.ui.theme.White
import com.ram.buspass.utils.components.ButtonView
import com.ram.buspass.utils.components.ConvertUriToFiles.convertUriToFile
import com.ram.buspass.utils.components.IconView
import com.ram.buspass.utils.components.InputTextFieldView
import com.ram.buspass.utils.components.TextView
import java.io.File


@Composable
fun EditProfileViewScreen(
    navController: NavHostController,
    editProfileViewModel: EditProfileViewModel = hiltViewModel()
) {
    val visible by remember { mutableStateOf(true) }
    var userName by remember { mutableStateOf("") }
    val isNameEmpty by remember { mutableStateOf(false) }
    var address by remember { mutableStateOf("") }
    val isEmailEmpty by remember { mutableStateOf(false) }
     val editProfileResult = editProfileViewModel.editProfile
    val editProfileResultImage = editProfileViewModel.editProfileImage
    var getNewImage by remember { mutableStateOf<Uri?>(null) }
    var imageFiles by remember { mutableStateOf<File?>(null) }
    var showDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val userId = ClientInterceptor(context).getUserId()
    var imageUri by remember { mutableStateOf<String?>(null) }




    val galleryImageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            getNewImage = uri
            uri?.let {
                imageFiles = convertUriToFile(context, uri)
                showDialog = true


            }
        }
    )

    if (editProfileResult.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextView(
                    text = "This page is Opening.",
                    style = TextStyle(color = Color.Gray, fontSize = 18.sp),
                )
                CircularProgressIndicator(1f, modifier = Modifier, color = Purple)
            }
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

    //edit Profile Image

    if (editProfileResultImage.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextView(
                    text = "This page is Opening.",
                    style = TextStyle(color = Color.Gray, fontSize = 18.sp),
                )
                CircularProgressIndicator(1f, modifier = Modifier, color = Purple)
            }
        }
    }
    if (editProfileResultImage.isError.isNotEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            TextView(text = editProfileResult.isError)
        }
    }

    LaunchedEffect(key1 = editProfileResultImage.isData, block = {
        if (editProfileResult.isData?.is_success == true) {
            editProfileViewModel.updateProfileImage(userId ,imageFiles)

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

            EditProfileCard(getNewImage = getNewImage , onClickPickImages = {galleryImageLauncher.launch("image/*")})
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

            InputTextFieldView(
                value = address ,
                onValueChange = { address = it },
                label = "Address",
                placeholder = "Enter Address",
                textStyle = TextStyle(),
                isEmpty = isEmailEmpty,
                invalidMessage = "The Address is empty!",
                errorColor = Color.Red,
                leadingIcon = { IconView(imageVector = Icons.Default.LocationOn) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)

            )


            ButtonView(
                onClick = {
                    editProfileViewModel.getUserEditProfile(userId ,userName ,address)
                    editProfileViewModel.updateProfileImage(userId ,imageFiles)
//                    navController.navigate("Profile")
                    showToast(context ,"Profile updated successfully")






                },

                btnColor = ButtonDefaults.buttonColors(Purple),
                text = "Update",
                textStyle = TextStyle()
            )

        }
    }
}

@Composable
fun EditProfileCard(
    onClickPickImages: () -> Unit,
    getNewImage: Uri?
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

        AsyncImage(
            model = getNewImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth(),
        )
    }
    TextView(text = "Change Picture" , modifier = Modifier.clickable { onClickPickImages() })

}

