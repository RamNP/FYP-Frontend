package com.ram.buspass.features.chanagePassword.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ram.buspass.features.chanagePassword.domain.ChangePasswordUseCase
import com.ram.buspass.helper.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(private val changePasswordUseCase: ChangePasswordUseCase) :
    ViewModel() {
    private var _password by mutableStateOf(ChangePasswordState())
    val password: ChangePasswordState get() = _password

    fun getPassword(email: String? = null,oldPassword: String? = null, newPassword: String? = null) {
        changePasswordUseCase(email, oldPassword, newPassword ).onEach { resource ->
            _password = when(resource) {
                is Resource.Loading -> {
                    ChangePasswordState(isLoading = true)
                }

                is Resource.Success -> {
                    ChangePasswordState(isData = password.isData)
                }

                is Resource.Error -> {
                    ChangePasswordState(isError = resource.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }
}