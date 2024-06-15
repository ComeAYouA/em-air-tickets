package com.example.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OffersResponse(
    @SerialName("offers")
    val offers: List<Offer>
)
