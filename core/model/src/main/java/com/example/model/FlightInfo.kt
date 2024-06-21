package com.example.model

data class FlightInfo(
    val dayOfMonth: Int,
    val dayOfWeek: DayOfWeek,
    val month: Month,
    val numOfPassengers: Int
)
