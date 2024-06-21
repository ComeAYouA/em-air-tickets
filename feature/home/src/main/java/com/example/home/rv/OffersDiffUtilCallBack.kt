package com.example.home.rv

import androidx.recyclerview.widget.DiffUtil
import com.example.network.model.offer.Offer


internal class OffersDiffUtilCallBack(
    private val oldList: List<Offer>,
    private val newList: List<Offer>
): DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}