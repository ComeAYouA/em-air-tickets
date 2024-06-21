package com.example.search.di.deps

import com.example.data.DataFlowUtil
import com.example.network.TicketsApi

interface SearchDependencies{
    val ticketsApi: TicketsApi
    val dataFlowUtil: DataFlowUtil
}
