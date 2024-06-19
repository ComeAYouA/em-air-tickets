package com.example.offer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.tickets.databinding.ItemPropertyBinding
import com.example.tickets.databinding.LayoutTicketsBinding
import com.example.offer.rv.ListItemsDecorations
import com.example.offer.rv.PropertyAdapter
import com.example.ui.dpToPx

class TicketsFragment: Fragment() {
    private var _binding: LayoutTicketsBinding? = null
    private val binding: LayoutTicketsBinding
        get() = _binding!!

    private val propertiesAdapter by lazy {
        PropertyAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LayoutTicketsBinding.inflate(inflater, container, false)

        setupPropertiesRv()
        return binding.root
    }

    private fun setupPropertiesRv() {
        binding.rvProperties.apply {
            adapter = propertiesAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            addItemDecoration(ListItemsDecorations(dpToPx(8), dpToPx(16)))
        }
    }
}