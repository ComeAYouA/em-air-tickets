package com.example.tickets.rv

import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.network.model.Offer
import com.example.tickets.databinding.ListItemOfferBinding

class OffersViewHolder(private val binding: ListItemOfferBinding): ViewHolder(binding.root) {
    fun bind(offer: Offer, @DrawableRes imgId: Int){
        binding.textViewTitle.text = offer.title
        binding.textViewCity.text = offer.town
        binding.textViewPrice.text =  "от ${offer.price.value} ₽"
        binding.shapeableImageView.setImageResource(imgId)
    }
}