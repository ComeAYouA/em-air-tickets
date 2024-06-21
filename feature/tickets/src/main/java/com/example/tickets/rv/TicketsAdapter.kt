package com.example.tickets.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.network.model.ticket.Ticket
import com.example.tickets.databinding.ItemTicketBinding

internal class TicketsAdapter: RecyclerView.Adapter<TicketViewHolder>(){
    private val data = mutableListOf<Ticket>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {

        val binding = ItemTicketBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return TicketViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
        val ticket = data[position]

        holder.bind(ticket)
    }

    fun setData(input: List<Ticket>){
        data.clear()
        data.addAll(input)

        notifyDataSetChanged()
    }
}