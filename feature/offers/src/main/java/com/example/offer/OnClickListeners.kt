package com.example.offer

import android.app.DatePickerDialog
import android.icu.util.Calendar
import androidx.fragment.app.Fragment
import com.example.model.toDayOfWeek
import com.example.model.toMonth
import com.example.offer.rv.PropertyAdapter

internal fun Fragment.propertiesRVonClickListener(
    viewModel: OffersViewModel
): PropertyAdapter.OnClickListener
    = object: PropertyAdapter.OnClickListener{
        override fun PropertyAdapter.onClick(position: Int) {
            when(position){
                0 -> {
                    pickDate{ year, month, dayOfMonth ->
                        updateData(position, year, month, dayOfMonth, viewModel)
                    }
                }
            }
        }

    }

internal fun Fragment.pickDate(onDatePicked: (Int, Int, Int) -> Unit) {
    DatePickerDialog(requireActivity()).apply {
        setOnDateSetListener { _, year, month, dayOfMonth ->
            onDatePicked(year, month, dayOfMonth)
        }
    }.show()
}

internal fun PropertyAdapter.updateData(
    position: Int,
    year: Int, month: Int,
    dayOfMonth: Int,
    viewModel: OffersViewModel
){
    val calendar = Calendar.getInstance()
    calendar.set(year, month, dayOfMonth, 0, 0)
    calendar.firstDayOfWeek = Calendar.MONDAY

    data[position].title = "$dayOfMonth ${(month + 1).toMonth().title}"
    val dayOfWeek =  (calendar.get(Calendar.DAY_OF_WEEK) - 1).toDayOfWeek()

    data[position].subtitle = ", ${dayOfWeek.title}"
    notifyItemChanged(position)

    viewModel.flightDateChanged(dayOfMonth, dayOfWeek, (month + 1).toMonth())
}

