package com.example.tickets.di.deps

import com.example.data.CitiesFlowUtil
import com.example.network.TicketsApi

interface TicketsDependencies{
    val ticketsApi: TicketsApi
    val citiesFlowUtil: CitiesFlowUtil
}
