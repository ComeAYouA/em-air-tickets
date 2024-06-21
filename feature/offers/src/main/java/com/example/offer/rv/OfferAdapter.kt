package com.example.offer.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.network.model.tickets_offer.TicketsOffer
import com.example.ui.R
import com.example.offers.databinding.ItemOfferBinding

internal  class OfferAdapter: RecyclerView.Adapter<OfferViewHolder>(){
    private val data = mutableListOf<TicketsOffer>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {

        val binding = ItemOfferBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return OfferViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        val offer = data[position]
        val icon = when(position % 3){
            0 -> R.drawable.background_circle_red
            1 -> R.drawable.background_circle_blue
            else -> R.drawable.background_circle_white
        }

        holder.bind(offer, icon)
    }

    fun setData(input: List<TicketsOffer>){
        data.clear()
        data.addAll(input)

        notifyItemRangeInserted(0, input.lastIndex)
    }
}