package com.ram.buspass.features.login.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ram.buspass.utils.Resource
import com.ram.buspass.features.login.domain.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCases: LoginUseCase) :
    ViewModel() {
    private var _login by mutableStateOf(LoginState())
    val login: LoginState get() = _login

    fun getLoginUser(email: String? , password: String?) {
        _login = LoginState(isLoading = true)
        loginUseCases(email, password).onEach { resource ->
            _login = when(resource) {
                is Resource.Loading -> {
                    LoginState(isLoading = true)
                }

                is Resource.Success -> {
                    LoginState(isData = resource.data)
                }

                is Resource.Error -> {
                    LoginState(isError = resource.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }
}
