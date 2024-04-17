package com.ram.buspass.features.register.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ram.buspass.features.register.domain.RegisterUseCase
import com.ram.buspass.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val registerUseCase: RegisterUseCase) : ViewModel() {
    private var _register by mutableStateOf(RegisterState())
    val register: RegisterState get() = _register
    fun getRegisterUser(email: String?,username:String?, password: String?, role: String?) {

        registerUseCase.invoke(email, username , password, role).onEach { resource ->
            _register = when(resource) {
                is Resource.Loading -> {
                    RegisterState(isLoading = true)
                }

                is Resource.Success -> {
                    RegisterState(data = register.data, message  = resource.data?.message)
                }

                is Resource.Error -> {
                    RegisterState(isError = resource.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }
}