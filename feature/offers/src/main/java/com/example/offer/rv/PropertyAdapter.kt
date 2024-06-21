package com.example.offer.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.offer.ui.model.Property
import com.example.offers.databinding.ItemPropertyBinding

internal class PropertyAdapter: RecyclerView.Adapter<PropertyViewHolder>(){

    interface OnClickListener{

        fun PropertyAdapter.onClick(position: Int)
    }

    private var onClickListener: OnClickListener? = null

    val data = mutableListOf<Property>()
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

        holder.bind(property) { onClickListener?.run { onClick(position) } }
    }

    fun setData(input: List<Property>){
        data.clear()
        data.addAll(input)

        notifyDataSetChanged()
    }
    fun PropertyAdapter.setOnclickListener(onClickListener: OnClickListener){
        this.onClickListener = onClickListener
    }

}