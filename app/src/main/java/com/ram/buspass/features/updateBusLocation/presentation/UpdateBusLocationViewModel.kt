package com.ram.buspass.features.updateBusLocation.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ram.buspass.features.updateBusLocation.domain.UpdateBusLocationUseCase
import com.ram.buspass.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UpdateBusLocationViewModel @Inject constructor(private val updateBusLocationUseCase: UpdateBusLocationUseCase) :
    ViewModel() {
    private var _busLocation by mutableStateOf(UpdateBusLocationState())
    private var _bookingBus by mutableStateOf(BookingBusState())
    val busLocation: UpdateBusLocationState get() = _busLocation
    val bookingBus: BookingBusState get() = _bookingBus

    fun getBusLocation(busNumber: String? = null,latitude: String? = null, longitude: String? = null) {
        updateBusLocationUseCase(busNumber,latitude , longitude, ).onEach { resource ->
            _busLocation = when(resource) {
                is Resource.Loading -> {
                    UpdateBusLocationState(isLoading = true)
                }

                is Resource.Success -> {
                    UpdateBusLocationState(isData = busLocation.isData)
                }

                is Resource.Error -> {
                    UpdateBusLocationState(isError = resource.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }

    //get Booking Bus View Model
    fun getBookingBusDetails() {
        updateBusLocationUseCase().onEach {
            _bookingBus = when(it) {
                is Resource.Loading -> {
                    BookingBusState(isLoading = true)
                }

                is Resource.Success -> {
                    BookingBusState(isData = it.data)
                }

                is Resource.Error -> {
                    BookingBusState(isError = it.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }

}