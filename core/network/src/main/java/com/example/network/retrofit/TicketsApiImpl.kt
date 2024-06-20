package com.example.network.retrofit

import com.example.network.TicketsApi
import com.example.network.model.OffersResponse
import com.example.network.model.TicketsOffersResponse
import retrofit2.Retrofit
import javax.inject.Inject

class TicketsApiImpl @Inject constructor(
    private val retrofit: Retrofit
): TicketsApi {
    private val api = retrofit.create(TicketsApi::class.java)

    override suspend fun getOffers(): OffersResponse = api.getOffers()
    override suspend fun getTicketsOffers(): TicketsOffersResponse = api.getTicketsOffers()
}