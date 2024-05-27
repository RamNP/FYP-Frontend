package com.ram.buspass.features.passVerify.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ram.buspass.features.passVerify.domain.PassVerifyUseCase
import com.ram.buspass.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PassVerifyViewModel @Inject constructor(private val passVerifyUseCase: PassVerifyUseCase) : ViewModel() {
    private var _passVerify by mutableStateOf(PassVerifyState())
    val passVerify: PassVerifyState get() = _passVerify

    var isRefresh by mutableStateOf(false)


    fun getPassVerify() {
        passVerifyUseCase().onEach {
            _passVerify = when (it) {
                is Resource.Loading -> {
                    PassVerifyState(isLoading = true)
                }

                is Resource.Success -> {
                    PassVerifyState(isData = it.data)
                }
                is Resource.Error -> {
                    PassVerifyState(isError = it.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }


    fun getVerifyStatus(passId: Int?) {
        passVerifyUseCase.invoke(passId).onEach {
            _passVerify = when (it) {
                is Resource.Loading -> {
                    _passVerify.copy(isLoading = true)
                }

                is Resource.Success -> {
                    Log.e("" ,"${it.message}")
                    _passVerify.copy(isData = it.data, isLoading = false)
                }
                is Resource.Error -> {
                    _passVerify.copy(isError = it.message.toString(), isLoading = false)
                }
            }
        }.launchIn(viewModelScope)
    }





}

