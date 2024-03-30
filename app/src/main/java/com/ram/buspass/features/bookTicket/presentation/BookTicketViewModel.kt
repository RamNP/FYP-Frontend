//package com.ram.buspass.features.bookTicket.presentation
//
//import androidx.compose.runtime.State
//import androidx.compose.runtime.mutableStateOf
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.ram.buspass.features.bookTicket.domain.BookTicketDetailsUseCas
//import com.ram.buspass.features.helper.Resource
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.flow.launchIn
//import kotlinx.coroutines.flow.onEach
//import javax.inject.Inject
//
//@HiltViewModel
//class BookTicketViewModel @Inject constructor(private val bookTicketDetailsUseCas: BookTicketDetailsUseCas) :
//    ViewModel() {
//
//    private val _bookTicket = mutableStateOf(BookTicketState())
//    val bookTicket: State<BookTicketState> get() = _bookTicket
//     fun getBookTicket(busNumber: String) {
//         bookTicketDetailsUseCas(busNumber).onEach {
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