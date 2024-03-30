package com.ram.buspass.features.ticketBook.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ram.buspass.features.helper.Resource
import com.ram.buspass.features.ticketBook.domain.TicketBookUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TicketBookViewModel @Inject constructor(private val ticketBookUseCase: TicketBookUseCase) :
    ViewModel() {
    private val _ticket = mutableStateOf(TicketBookState())
    val ticket: State<TicketBookState> get() = _ticket
    private val _bookTicket = mutableStateOf(BookTicketState())
    val bookTicket: State<BookTicketState> get() = _bookTicket

    fun getTicket() {
        ticketBookUseCase().onEach {
            when (it) {
                is Resource.Loading -> {
                    _ticket.value = TicketBookState(isLoading = true)
                }

                is Resource.Success -> {
                    _ticket.value = TicketBookState(isData = it.data?.data)
                }

                is Resource.Error -> {
                    _ticket.value = TicketBookState(isError = it.message.toString())
                }

            }
        }.launchIn(viewModelScope)
    }
}
//
//    fun getBookTicket() {
//        ticketBookUseCase().onEach {
//            when (it) {
//                is Resource.Loading -> {
//                    _bookTicket.value = BookTicketState(isLoading = true)
//                }
//                is Resource.Success -> {
//                    _bookTicket.value = BookTicketState(isData = it.data?.data)
//                }
//                is Resource.Error -> {
//                    _bookTicket.value = BookTicketState(isError = it.message.toString())
//                }
//
//            }
//        }.launchIn(viewModelScope)
//    }
//}

