package com.example.home.di.deps

import com.example.data.DataFlowUtil
import com.example.network.TicketsApi

interface HomeDependencies{
    val ticketsApi: TicketsApi
    val dataFlowUtil: DataFlowUtil
}
