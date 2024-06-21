package com.example.network.retrofit

import com.example.network.TicketsApi
import com.example.network.model.offer.OffersResponse
import com.example.network.model.ticket.TicketsResponse
import com.example.network.model.tickets_offer.TicketsOffersResponse
import retrofit2.Retrofit
import javax.inject.Inject

class TicketsApiImpl @Inject constructor(
    private val retrofit: Retrofit
): TicketsApi {
    private val api = retrofit.create(TicketsApi::class.java)

    override suspend fun getOffers(): OffersResponse = api.getOffers()
    override suspend fun getTicketsOffers(): TicketsOffersResponse = api.getTicketsOffers()
    override suspend fun getTickets(): TicketsResponse = api.getTickets()
}