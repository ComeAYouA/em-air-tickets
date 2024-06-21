package com.example.network.model.tickets_offer

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TicketsOffersResponse(
    @SerialName("tickets_offers")
    val offers: List<TicketsOffer>
)
