//package com.ram.buspass.features.bookTicket.domain
//
//import com.ram.buspass.features.helper.Resource
//import com.ram.buspass.features.helper.resource.remote.api.model.booTicketDetails.BookPojo
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.flow
//
//class BookTicketDetailsUseCas(private val bookTicketDetailsRepository: BookTicketDetailsRepository) {
//
//    operator fun invoke(busNumber: String? = null,): Flow<Resource<BookPojo?>> = flow {
//        emit(Resource.Loading())
//        try {
//            emit(Resource.Success(bookTicketDetailsRepository.getBookDetails(busNumber = busNumber)))
//        } catch (e: Exception){
//            emit(Resource.Error(e.message.toString()))
//        }
//    }
//}