package com.example.network.model.ticket

import com.example.network.model.FlightPoint
import com.example.network.model.Price
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Ticket(
    @SerialName("id")
    val id: Int,
    @SerialName("badge")
    val badge: String? = null,
    @SerialName("price")
    val price: Price,
    @SerialName("provider_name")
    val providerName: String,
    @SerialName("company")
    val company: String,
    @SerialName("departure")
    val departure: FlightPoint,
    @SerialName("arrival")
    val arrival: FlightPoint,
    @SerialName("has_transfer")
    val hasTransfer: Boolean,
)
