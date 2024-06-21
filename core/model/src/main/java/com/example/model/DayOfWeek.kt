package com.example.model

enum class DayOfWeek(val title: String) {
    Monday("пн"),
    Tuesday("вт"),
    Wednesday("ср"),
    Thursday("чт"),
    Friday("пт"),
    Saturday("сб"),
    Sunday("вс"),
}

fun Int.toDayOfWeek(): DayOfWeek =
    when(this%7){
        1 -> DayOfWeek.Monday
        2 -> DayOfWeek.Tuesday
        3 -> DayOfWeek.Wednesday
        4 -> DayOfWeek.Thursday
        5 -> DayOfWeek.Friday
        6 -> DayOfWeek.Saturday
        else -> DayOfWeek.Sunday
    }