package com.example.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Price(
    @SerialName("value")
    val value: Int
)

val Price.formedRubles: String
    get() {
        val num = value.toString()
        val res = StringBuilder()

        for (idx in num.lastIndex downTo 0){
            if ((num.lastIndex - idx) % 3 == 0 && idx != num.lastIndex) res.append(" ")
            res.append(num[idx])
        }

        return res.reverse().append(" ₽").toString()
    }
