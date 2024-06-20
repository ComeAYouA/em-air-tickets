package com.example.offer.ui.model


enum class Month(val title: String){
    January("янв"),
    February("фев"),
    March ("мар"),
    April("апр"),
    May("май"),
    June("июнь"),
    July("июль"),
    August("ауг"),
    September("сен"),
    October("окт"),
    November("ноя"),
    December("дек")
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
