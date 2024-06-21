package com.example.home.rv

import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.home.databinding.ListItemOfferBinding
import com.example.network.model.formedRubles
import com.example.network.model.offer.Offer


internal class OffersViewHolder(private val binding: ListItemOfferBinding): ViewHolder(binding.root) {
    fun bind(offer: Offer, @DrawableRes imgId: Int){
        binding.textViewTitle.text = offer.title
        binding.textViewCity.text = offer.town
        binding.textViewPrice.text =  "от ${offer.price.formedRubles}"
        binding.shapeableImageView.setImageResource(imgId)
    }
}