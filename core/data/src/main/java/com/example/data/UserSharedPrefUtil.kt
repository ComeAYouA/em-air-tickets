package com.example.data

import android.content.Context
import javax.inject.Inject
import javax.inject.Singleton

private const val USER_SHARED_PREF_KEY = "USER_SHARED_PREF_KEY"
private const val DEPARTURE_CITY_KEY = "DEPARTURE_CITY_KEY"

@Singleton
class UserSharedPrefUtil @Inject constructor(
    private val context: Context
){
    fun getDepartureCity(): String {
        val sharedPref = context.getSharedPreferences(
            USER_SHARED_PREF_KEY,
            Context.MODE_PRIVATE
        )

        return sharedPref.getString(DEPARTURE_CITY_KEY, "")!!
    }

    fun saveDepartureCity(cityName: String?) {
        val sharedPref = context.getSharedPreferences(
            USER_SHARED_PREF_KEY, Context.MODE_PRIVATE
        )
        sharedPref.edit().putString(DEPARTURE_CITY_KEY, cityName).apply()
    }
}