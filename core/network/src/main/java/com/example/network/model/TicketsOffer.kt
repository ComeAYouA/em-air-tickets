package com.example.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TicketsOffer(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("time_range")
    val timeRange: List<String>,
    @SerialName("price")
    val price: Price
)