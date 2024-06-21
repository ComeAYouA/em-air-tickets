package com.example.tickets.di.deps

import com.example.data.DataFlowUtil
import com.example.network.TicketsApi

interface TicketsDependencies{
    val ticketsApi: TicketsApi
    val dataFlowUtil: DataFlowUtil
}
