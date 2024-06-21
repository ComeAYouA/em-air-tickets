package com.example.offer.rv

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.offer.ui.model.Property
import com.example.offers.databinding.ItemPropertyBinding
import com.example.ui.dpToPx


internal class PropertyViewHolder(private val binding: ItemPropertyBinding): ViewHolder(binding.root) {
    fun bind(property: Property, click: (() -> Unit)?){

        binding.root.setOnClickListener{ click?.invoke() }

        if (property.icon != null){
            binding.propertyIcon.setImageResource(property.icon!!)
            binding.propertyIcon.layoutParams.width = dpToPx(14)
            binding.divider1.layoutParams.width = dpToPx(8)
        }else{
            binding.propertyIcon.layoutParams.width = 0
            binding.divider2.layoutParams.width = 0
        }
        if (property.title != null){
            binding.propertyTitle.text = property.title + " "
        }
        if (property.subtitle != null){
            binding.propertySubtitle.text = property.subtitle + " "
        }

    }
}