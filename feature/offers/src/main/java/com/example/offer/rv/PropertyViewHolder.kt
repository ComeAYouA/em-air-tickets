package com.example.offer.rv

import android.content.res.Resources
import android.util.TypedValue
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.offer.Property
import com.example.tickets.databinding.ItemPropertyBinding
import com.example.ui.dpToPx


class PropertyViewHolder(private val binding: ItemPropertyBinding): ViewHolder(binding.root) {
    fun bind(property: Property){

        if (property.icon != null){
            binding.propertyIcon.setImageResource(property.icon!!)
            binding.propertyIcon.layoutParams.width = dpToPx(14)
        }
        binding.divider1.layoutParams.width = dpToPx(8)
        if (property.title != null){
            binding.propertyTitle.text = property.title
        }
        if (property.subtitle != null){
            binding.propertySubtitle.text = property.subtitle
        }

    }
}