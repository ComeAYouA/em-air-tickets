package com.example.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FlightPoint (
    @SerialName("town")
    val town: String,
    @SerialName("date")
    val date: String,
    @SerialName("airport")
    val airport: String
)
