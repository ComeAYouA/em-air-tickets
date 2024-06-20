package com.example.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TicketsOffersResponse(
    @SerialName("tickets_offers")
    val offers: List<TicketsOffer>
)
