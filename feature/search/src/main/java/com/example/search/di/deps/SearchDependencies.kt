package com.example.search.di.deps

import com.example.data.CitiesFlowUtil
import com.example.network.TicketsApi
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

interface SearchDependencies{
    val ticketsApi: TicketsApi
    val citiesFlowUtil: CitiesFlowUtil
}
