package com.example.tickets.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.network.model.Offer
import com.example.tickets.R

class OffersAdapter: RecyclerView.Adapter<OffersViewHolder>(){
    private val data = mutableListOf<Offer>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OffersViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.list_item_offer,
            parent,
            false
        )

        return OffersViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: OffersViewHolder, position: Int) {
        holder.bind()
    }
}