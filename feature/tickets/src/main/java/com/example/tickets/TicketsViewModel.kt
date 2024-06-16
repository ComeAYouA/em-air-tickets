package com.example.tickets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.network.TicketsApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class TicketsViewModel (
    private val api: TicketsApi
): ViewModel() {

    val offers = flow {
        val offers = api.getOffers().offers
        emit(offers)
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        listOf()
    )

}

@Suppress("UNCHECKED_CAST")
class Factory @Inject constructor(
    private val api: TicketsApi
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TicketsViewModel(api) as T
    }
}