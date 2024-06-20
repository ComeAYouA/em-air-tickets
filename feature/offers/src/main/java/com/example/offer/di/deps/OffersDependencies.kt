package com.example.offer.di.deps

import com.example.data.CitiesFlowUtil
import com.example.network.TicketsApi

interface OffersDependencies{
    val ticketsApi: TicketsApi
    val citiesFlowUtil: CitiesFlowUtil
}
