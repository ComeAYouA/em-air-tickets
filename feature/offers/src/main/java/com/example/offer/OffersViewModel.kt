package com.example.offer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.data.CitiesFlowUtil
import com.example.network.TicketsApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class OffersViewModel (
    private val ticketsApi: TicketsApi,
    val citiesFlowUtil: CitiesFlowUtil
): ViewModel() {
    val offers = flow {
        val offers = ticketsApi.getTicketsOffers().offers
        emit(offers)
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        listOf()
    )
}

@Suppress("UNCHECKED_CAST")
class Factory @Inject constructor(
    private val ticketsApi: TicketsApi,
    private val citiesFlowUtil: CitiesFlowUtil
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return OffersViewModel(ticketsApi, citiesFlowUtil) as T
    }
}