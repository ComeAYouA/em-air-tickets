package com.example.model


enum class Month(
    val title: String,
){
    January("январь"),
    February("февраль"),
    March ("март"),
    April("апрель"),
    May("май"),
    June("июнь"),
    July("июль"),
    August("август"),
    September("сентябрь"),
    October("октябрь"),
    November("ноябрь"),
    December("декабрь")
}

val Month.abbreviation: String
    get() =
        when(this){
            Month.January -> { "янв" }
            Month.February -> { "фев" }
            Month.March  -> { "мар" }
            Month.April -> { "апр" }
            Month.May -> { "май" }
            Month.June -> { "июн" }
            Month.July -> { "июл" }
            Month.August -> { "ауг" }
            Month.September -> { "сен" }
            Month.October -> { "окт" }
            Month.November -> { "ноя" }
            Month.December -> { "дек " }
    }

val Month.accusative: String
    get() =
        when(this){
            Month.January -> { "января" }
            Month.February -> { "февраля" }
            Month.March  -> { "марта" }
            Month.April -> { "апреля" }
            Month.May -> { "мая" }
            Month.June -> { "июня" }
            Month.July -> { "июля" }
            Month.August -> { "августа" }
            Month.September -> { "сентября" }
            Month.October -> { "октября" }
            Month.November -> { "ноября" }
            Month.December -> { "декабря" }
        }

fun Int.toMonth(): Month =
    when(this%12){
        1 -> Month.January
        2 -> Month.February
        3 -> Month.March
        4 -> Month.April
        5 -> Month.May
        6 -> Month.June
        7 -> Month.July
        8 -> Month.August
        9 -> Month.September
        10 -> Month.October
        11-> Month.November
        else -> Month.December
    }
