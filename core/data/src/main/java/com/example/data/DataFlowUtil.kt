package com.example.data

import android.icu.util.Calendar
import com.example.model.FlightInfo
import com.example.model.toDayOfWeek
import com.example.model.toMonth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataFlowUtil @Inject constructor(
    private val sharedPrefUtil: UserSharedPrefUtil
){
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val _departureCity = MutableStateFlow(sharedPrefUtil.getDepartureCity())
    private val _arrivalCity = MutableStateFlow("")
    val departureCity = _departureCity.asStateFlow()
    val arrivalCity = _arrivalCity.asStateFlow()

    init {
        coroutineScope.launch {
            departureCity.collect{ city ->
                sharedPrefUtil.saveDepartureCity(city)
            }
        }
    }

    private val _flightInfo = MutableStateFlow(
        with(Calendar.getInstance()){
            FlightInfo(
                dayOfMonth = get(Calendar.DAY_OF_MONTH),
                dayOfWeek = (get(Calendar.DAY_OF_WEEK) - 1).toDayOfWeek(),
                month = (get(Calendar.MONTH) + 1).toMonth(),
                numOfPassengers = 1
            )
        }
    )
    val flightProperties = _flightInfo.asStateFlow()

    fun updateDepartureCity(newValue: String){
        _departureCity.value = newValue
    }
    fun updateArrivalCity(newValue: String){
        _arrivalCity.value = newValue
    }
    fun updateFlightProperties(block: (FlightInfo) -> FlightInfo){
        _flightInfo.value = block(flightProperties.value)
    }
}