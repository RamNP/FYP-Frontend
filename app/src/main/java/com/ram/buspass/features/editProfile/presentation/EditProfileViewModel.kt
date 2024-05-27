package com.ram.buspass.features.editProfile.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ram.buspass.features.editProfile.domain.EditProfileUseCase
import com.ram.buspass.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.io.File
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(private val editProfileUseCase: EditProfileUseCase) :
    ViewModel() {
    private var _editProfile by mutableStateOf(EditProfileState())
    val editProfile: EditProfileState get() = _editProfile

    private var _editProfileImage by mutableStateOf(EditProfileImageState())
    val editProfileImage: EditProfileImageState get() = _editProfileImage

    fun getUserEditProfile( userId: Int? ,username: String? = null, address: String? = null,) {
        editProfileUseCase(userId, username, address ).onEach { resource ->
            _editProfile = when(resource) {
                is Resource.Loading -> {
                    EditProfileState(isLoading = true)
                }

                is Resource.Success -> {
                    EditProfileState(isData = editProfile.isData)
                }

                is Resource.Error -> {
                    EditProfileState(isError = resource.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }


    fun updateProfileImage(userId: Int, imageFile: File?) {
        editProfileUseCase.invoke(userId, imageFile).onEach { result ->
            _editProfileImage = when (result) {
                is Resource.Loading -> {
                    EditProfileImageState(isLoading = true)
                }

                is Resource.Success -> {
                    EditProfileImageState(isData = editProfileImage.isData)
                }

                is Resource.Error -> {
                    EditProfileImageState(isError = result.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }
}