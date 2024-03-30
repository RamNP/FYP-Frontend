//package com.ram.buspass.features.bookTicket.data
//
//import com.ram.buspass.features.bookTicket.domain.BookTicketDetailsRepository
//import com.ram.buspass.features.helper.resource.remote.api.ApiService
//import com.ram.buspass.features.helper.resource.remote.api.model.booTicketDetails.BookPojo
//
//class BookTicketDetailsImpl(private val apiService: ApiService) :BookTicketDetailsRepository{
//    override suspend fun getBookDetails(busNumber: String?): BookPojo {
//        try {
//            return apiService.getBookDetails(busNumber)
//        }catch (e: Exception)
//        {
//            throw Exception (e)
//        }
//    }
//
//
//}
