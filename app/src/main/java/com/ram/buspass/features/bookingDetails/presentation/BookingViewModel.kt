package com.ram.buspass.features.bookingDetails.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ram.buspass.features.bookingDetails.domain.BookingDetailsUseCase
import com.ram.buspass.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BookingViewModel @Inject constructor(private val bookingDetailsUseCase: BookingDetailsUseCase) :
    ViewModel() {


    private var _booking by mutableStateOf(BookingState())
    val booking: BookingState get() = _booking

    fun getBooking() {
        bookingDetailsUseCase().onEach {
            _booking = when (it) {
                is Resource.Loading -> {
                    BookingState(isLoading = true)
                }

                is Resource.Success -> {
                    BookingState(isData = it.data)
                }

                is Resource.Error -> {
                    BookingState(isError = it.message.toString())
                }

            }
        }.launchIn(viewModelScope)
    }

}
