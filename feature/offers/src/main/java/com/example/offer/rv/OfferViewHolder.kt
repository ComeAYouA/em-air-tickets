package com.example.offer.rv

import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.network.model.tickets_offer.TicketsOffer
import com.example.offers.databinding.ItemOfferBinding


internal class OfferViewHolder(private val binding: ItemOfferBinding): ViewHolder(binding.root) {
    fun bind(offer: TicketsOffer, @DrawableRes iconRes: Int){
        binding.companyNameText.text = offer.title + " "
        binding.timeRangeText.text = offer.timeRange.joinToString(" ")
        binding.priceText.text = offer.price.value.toString() + " "
        binding.ticketOfferIcon.setBackgroundResource(iconRes)
    }
}