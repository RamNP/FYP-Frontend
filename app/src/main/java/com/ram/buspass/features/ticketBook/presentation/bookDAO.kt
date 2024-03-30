package com.ram.buspass.features.ticketBook.presentation

import com.google.gson.annotations.SerializedName

data class bookDAO(
    @SerializedName("busNumber")
    val busNumber : String? = null,
    @SerializedName("name")
    val name : String? = null,
    @SerializedName("fromTo")
    val fromTo : String? = null,
)
