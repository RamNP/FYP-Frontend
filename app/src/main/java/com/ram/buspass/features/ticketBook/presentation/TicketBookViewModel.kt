package com.ram.buspass.features.ticketBook.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ram.buspass.features.ticketBook.domain.BookDto
import com.ram.buspass.features.ticketBook.domain.TicketBookUseCase
import com.ram.buspass.helper.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TicketBookViewModel @Inject constructor(private val ticketBookUseCase: TicketBookUseCase) :
    ViewModel() {
    private var _ticket by mutableStateOf(TicketBookState())
    val ticket: TicketBookState get() = _ticket

    private var _bookingTicket by mutableStateOf(TicketBookingState())
    val bookingTicket: TicketBookingState get() = _bookingTicket


    fun getTicket() {
        ticketBookUseCase().onEach {
            _ticket = when (it) {
                is Resource.Loading -> {
                    TicketBookState(isLoading = true)
                }

                is Resource.Success -> {
                    TicketBookState(isData = it.data)
                }

                is Resource.Error -> {
                    TicketBookState(isError = it.message.toString())
                }

            }
        }.launchIn(viewModelScope)
    }
    fun getTicketBook(bus :Int?, ticket: Int?, users: Int?) {
        val bookDto = BookDto(bus,ticket,users)
        ticketBookUseCase.invoke(bookDto).onEach {
            _bookingTicket = when (it) {
                is Resource.Loading -> {
                    TicketBookingState(isLoading = true)
                }

                is Resource.Success -> {
                    TicketBookingState(isData = it.data)
                }

                is Resource.Error -> {
                    TicketBookingState(isError = it.message.toString())
                }

            }
        }.launchIn(viewModelScope)
    }
}

