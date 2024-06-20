package com.example.offer.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.tickets.databinding.ItemPropertyBinding
import com.example.offer.properties

class PropertyAdapter: RecyclerView.Adapter<PropertyViewHolder>(){
    private val data = properties
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyViewHolder {

        val binding = ItemPropertyBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return PropertyViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: PropertyViewHolder, position: Int) {
        val property = data[position]
        holder.bind(property)
    }
}