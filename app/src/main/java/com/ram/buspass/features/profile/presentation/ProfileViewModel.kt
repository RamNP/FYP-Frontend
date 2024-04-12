package com.ram.buspass.features.profile.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ram.buspass.features.profile.domain.ProfileUseCase
import com.ram.buspass.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val profileUseCase: ProfileUseCase) :
    ViewModel() {

    private var _profile by mutableStateOf(ProfileState())
    val profile: ProfileState get() = _profile

    fun getUserProfile() {
        profileUseCase().onEach {
            _profile = when (it) {
                is Resource.Loading -> {
                    ProfileState(isLoading = true)
                }

                is Resource.Success -> {
                    ProfileState(isData = it.data)
                }

                is Resource.Error -> {
                    ProfileState(isError = it.message.toString())
                }

            }
        }.launchIn(viewModelScope)
    }

}
