package com.example.tickets

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.network.TicketsApi
import kotlinx.coroutines.launch
import javax.inject.Inject

class TicketsViewModel @Inject constructor(
    private val api: TicketsApi
): ViewModel() {
    init {
        viewModelScope.launch {
            Log.d("myTag", api.getOffers().toString())
        }
    }
}