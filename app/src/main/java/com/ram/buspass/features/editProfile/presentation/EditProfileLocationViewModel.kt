package com.ram.buspass.features.editProfile.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ram.buspass.features.editProfile.domain.EditProfileUseCase
import com.ram.buspass.helper.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class EditProfileLocationViewModel @Inject constructor(private val editProfileUseCase: EditProfileUseCase) :
    ViewModel() {
    private var _editProfile by mutableStateOf(EditProfileState())
    val editProfile: EditProfileState get() = _editProfile

    fun getUserEditProfile(email: String? = null,username: String? = null,) {
        editProfileUseCase(email, username, ).onEach { resource ->
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
}