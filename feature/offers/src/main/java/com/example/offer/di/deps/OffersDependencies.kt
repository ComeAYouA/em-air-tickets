package com.example.offer.di.deps

import com.example.data.DataFlowUtil
import com.example.network.TicketsApi

interface OffersDependencies{
    val ticketsApi: TicketsApi
    val dataFlowUtil: DataFlowUtil
}
