package com.ram.buspass.features.verifyTicket.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ram.buspass.features.verifyTicket.domain.VerifyTicketUseCase
import com.ram.buspass.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class VerifyTicketViewModel @Inject constructor(private val verifyTicketUseCase: VerifyTicketUseCase) :
    ViewModel() {

    var isRefresh by mutableStateOf(false)
    private var _vTicket by mutableStateOf(VerifyTicketState())
    val vTicket: VerifyTicketState get() = _vTicket
    fun getVerifyTick() {
        verifyTicketUseCase().onEach {
            _vTicket = when (it) {
                is Resource.Loading -> {
                    VerifyTicketState(isLoading = true)
                }

                is Resource.Success -> {
                    VerifyTicketState(isData = it.data)
                }

                is Resource.Error -> {
                    VerifyTicketState(isError = it.message.toString())
                }

            }
        }.launchIn(viewModelScope)
    }


    fun loadStuff(){
        isRefresh = true
        verifyTicketUseCase.invoke().onEach {
            when (it) {
                is Resource.Loading -> {
                    isRefresh = true
                }

                is Resource.Success -> {
                    _vTicket = VerifyTicketState(isData = it.data)
                    isRefresh = false
                }

                is Resource.Error -> {
                    _vTicket = VerifyTicketState(isError = it.message.toString())
                    isRefresh =false
                }

            }
        }.launchIn(viewModelScope)
    }
}
