package com.example.tickets.rv

import android.R.string
import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.network.model.formedRubles
import com.example.network.model.ticket.Ticket
import com.example.tickets.databinding.ItemTicketBinding
import com.example.ui.dpToPx
import org.joda.time.DateTime
import org.joda.time.Duration
import org.joda.time.Period
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter


internal class TicketViewHolder(private val binding: ItemTicketBinding): ViewHolder(binding.root) {
    @SuppressLint("SetTextI18n")
    fun bind(ticket: Ticket){
        binding.apply {
            if (ticket.badge != null){
                badgeTextView.text = ticket.badge
            } else {
                badgeTextView.visibility = View.INVISIBLE
                (priceTextView.layoutParams as MarginLayoutParams).apply {
                    this.setMargins(
                        0,
                        dpToPx(16),
                        0,
                        0
                    )
                }
            }

            val departureDate = ticket.departure.date.toDate()
            val arrivalDate = ticket.arrival.date.toDate()

            priceTextView.text = ticket.price.formedRubles
            departureTimeTextView.text = "${departureDate.hourOfDay().get()}:${departureDate.minuteOfHour().get()} "
            departureCityNameTextView.text = ticket.departure.airport + " "
            arrivalTimeTextView.text = "${arrivalDate.hourOfDay().get()}:${arrivalDate.minuteOfHour().get()} "
            arrivalCityNameTextView.text = ticket.arrival.airport + " "
            flightInfoTextView.text = calculateFlightTime(departureDate, arrivalDate) +
                    if (ticket.hasTransfer) " / Без пересадок" else ""
        }
    }

    private fun calculateFlightTime(departure: DateTime, arrival: DateTime): String{
        val period = Period(departure, arrival)
        return "${
            String.format("%.1f", period.hours + (period.minutes.toDouble() / 60.0))
                .replace(',', '.')
        }ч в пути"
    }


    private fun String.toDate(): DateTime{
        val formatter: DateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")
        val dt: DateTime = formatter.parseDateTime(this.replace('T', ' '))
        return dt
    }
}
