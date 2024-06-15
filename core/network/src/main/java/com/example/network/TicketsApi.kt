package com.example.network

import com.example.network.model.OffersResponse
import retrofit2.http.GET

interface TicketsApi {
    @GET("214a1713-bac0-4853-907c-a1dfc3cd05fd")
    suspend fun getOffers(): OffersResponse
}