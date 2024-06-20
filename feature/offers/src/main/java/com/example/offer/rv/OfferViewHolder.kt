package com.example.offer.rv

import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.network.model.TicketsOffer
import com.example.offer.ui.model.Property
import com.example.offers.databinding.ItemOfferBinding
import com.example.offers.databinding.ItemPropertyBinding
import com.example.ui.dpToPx


class OfferViewHolder(private val binding: ItemOfferBinding): ViewHolder(binding.root) {
    fun bind(offer: TicketsOffer, @DrawableRes iconRes: Int){
        binding.companyNameText.text = offer.title + " "
        binding.timeRangeText.text = offer.timeRange.joinToString(" ")
        binding.priceText.text = offer.price.value.toString() + " "
        binding.ticketOfferIcon.setBackgroundResource(iconRes)
    }
}