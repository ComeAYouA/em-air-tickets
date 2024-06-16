package com.example.tickets.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.network.model.Offer
import com.example.tickets.R
import com.example.tickets.databinding.ListItemOfferBinding

class OffersAdapter: RecyclerView.Adapter<OffersViewHolder>(){
    private val data = mutableListOf<Offer>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OffersViewHolder {

        val binding = ListItemOfferBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return OffersViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: OffersViewHolder, position: Int) {
        val offer = data[position]
        val imgId = when(offer.id){
            1 -> { com.example.ui.R.drawable.img_1}
            2 -> { com.example.ui.R.drawable.img_2}
            else -> { com.example.ui.R.drawable.img_3}
        }
        holder.bind(
            offer,
            imgId
        )
    }

    fun setData(input: List<Offer>){
        val diffUtilCallback = OffersDiffUtilCallBack(data, input)
        val diffUtilResult = DiffUtil.calculateDiff(diffUtilCallback)

        data.clear()
        data.addAll(input)

        diffUtilResult.dispatchUpdatesTo(this)
    }
}