package com.example.offer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.data.DataFlowUtil
import com.example.model.DayOfWeek
import com.example.model.Month
import com.example.model.abbreviation
import com.example.network.TicketsApi
import com.example.offer.ui.model.Property
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

internal class OffersViewModel (
    private val ticketsApi: TicketsApi,
    val dataFlowUtil: DataFlowUtil
): ViewModel() {
    val offers = flow {
        val offers = ticketsApi.getTicketsOffers().offers
        emit(offers)
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        listOf()
    )

    val properties =  dataFlowUtil.flightProperties.value.let { properties ->
        listOf(
            Property(
                title = "${properties.dayOfMonth} ${properties.month.abbreviation}",
                subtitle = ", ${properties.dayOfWeek.title}"
            ),
            Property(
                icon = com.example.ui.R.drawable.ic_member,
                title = "${properties.numOfPassengers}, эконом",
            ),
            Property(
                icon = com.example.ui.R.drawable.ic_settings,
                title = "фильтры",
            )
        )
    }

    fun flightDateChanged(dayOfMonth: Int, dayOfWeek: DayOfWeek, month: Month){
        dataFlowUtil.updateFlightProperties {
            it.copy(
                dayOfMonth = dayOfMonth,
                dayOfWeek = dayOfWeek,
                month = month
            )
        }
    }
}

@Suppress("UNCHECKED_CAST")
internal class Factory @Inject constructor(
    private val ticketsApi: TicketsApi,
    private val dataFlowUtil: DataFlowUtil
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return OffersViewModel(ticketsApi, dataFlowUtil) as T
    }
}