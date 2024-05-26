package com.ram.buspass.features.forgetPassword.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ram.buspass.features.forgetPassword.domain.ForgotPasswordUseCase
import com.ram.buspass.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject



@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(private val forgotPasswordUseCase: ForgotPasswordUseCase) : ViewModel() {

    private var _forgotPasswordState by mutableStateOf(ForgotPasswordState())
    val forgotPasswordState: ForgotPasswordState get() = _forgotPasswordState


    // Forgot password
    fun setForgotPassword(email: String?, newPassword: String?){
        forgotPasswordUseCase.invoke(email?.trim(), newPassword?.trim()).onEach { result ->
            _forgotPasswordState = when (result) {
                is Resource.Loading -> {
                    ForgotPasswordState(isLoading = true)
                }

                is Resource.Success -> {
                    ForgotPasswordState(isData = result.data)
                }

                is Resource.Error -> {
                    ForgotPasswordState(isError = result.message !!)
                }
            }
        }.launchIn(viewModelScope)
    }
}