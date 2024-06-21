package com.example.tickets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.data.DataFlowUtil
import com.example.network.TicketsApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

internal class TicketsViewModel (
    private val ticketsApi: TicketsApi,
    val dataFlowUtil: DataFlowUtil
): ViewModel() {
    val tickets = flow {
        val offers = ticketsApi.getTickets().tickets
        emit(offers)
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        listOf()
    )
}

@Suppress("UNCHECKED_CAST")
internal class Factory @Inject constructor(
    private val ticketsApi: TicketsApi,
    private val dataFlowUtil: DataFlowUtil
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TicketsViewModel(ticketsApi, dataFlowUtil) as T
    }
}