package com.example.network.model.ticket

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TicketsResponse(
    @SerialName("tickets")
    val tickets: List<Ticket>
)
