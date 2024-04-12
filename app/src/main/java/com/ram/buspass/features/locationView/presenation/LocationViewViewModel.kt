package com.ram.buspass.features.locationView.presenation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ram.buspass.features.locationView.domain.LocationViewUseCase
import com.ram.buspass.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LocationViewViewModel @Inject constructor(private val locationViewUseCase: LocationViewUseCase) :
    ViewModel() {

    private var _locationView by mutableStateOf(LocationViewState())
    val locationView: LocationViewState get() = _locationView

    fun getViewLocation() {
        locationViewUseCase().onEach {
            _locationView = when (it) {
                is Resource.Loading -> {
                    LocationViewState(isLoading = true)
                }

                is Resource.Success -> {
                    LocationViewState(isData = it.data)
                }

                is Resource.Error -> {
                    LocationViewState(isError = it.message.toString())
                }

            }
        }.launchIn(viewModelScope)
    }

}
