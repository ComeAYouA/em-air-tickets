package com.example.data

import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CitiesFlowUtil @Inject constructor(){
    private val _departureCity = MutableStateFlow("")
    private val _arrivalCity = MutableStateFlow("")
    val departureCity = _departureCity.asStateFlow()
    val arrivalCity = _arrivalCity.asStateFlow()

    fun departureCityChanged(newValue: String){
        _departureCity.value = newValue
        Log.d("myTag", "$newValue $this")
    }
    fun arrivalCityChanged(newValue: String){
        _arrivalCity.value = newValue
    }
}