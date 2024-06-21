package com.example.tickets

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.model.accusative
import com.example.tickets.databinding.LayoutTicketsBinding
import com.example.tickets.di.DaggerTicketsComponent
import com.example.tickets.di.TicketsComponent
import com.example.tickets.di.deps.TicketsComponentDependenciesProvider
import com.example.tickets.rv.TicketsAdapter
import com.example.ui.dpToPx
import com.example.ui.rv.VerticalListItemDecoration
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

internal class TicketsFragment: Fragment() {

    private lateinit var ticketsComponent: TicketsComponent

    private var _binding: LayoutTicketsBinding? = null
    private val binding: LayoutTicketsBinding
        get() = _binding!!

    private val viewModel: TicketsViewModel by viewModels {
        ticketsComponent.ticketsViewModelFactory()
    }

    private val ticketsAdapter: TicketsAdapter by lazy {
        TicketsAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        subscribeUi()
    }

    override fun onAttach(context: Context) {
        ticketsComponent = DaggerTicketsComponent
            .builder()
            .deps(
                (requireActivity().applicationContext as TicketsComponentDependenciesProvider)
                    .getTicketsComponentDependencies()
            ).build()

        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LayoutTicketsBinding.inflate(
            inflater,
            container,
            false
        )

        setupTicketsInfoBar()
        setupTicketsRv()
        setupBackButton()

        return binding.root
    }

    private fun setupBackButton() {
        binding.backButton.setOnClickListener{
            findNavController().popBackStack()
        }
    }

    private fun subscribeUi() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.tickets.collect{
                    if(it.isNotEmpty()) binding.progressBar.visibility = View.INVISIBLE
                    ticketsAdapter.setData(it)
                }
            }
        }
    }

    private fun setupTicketsRv() {
        binding.ticketsRv.apply {
            adapter = ticketsAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(VerticalListItemDecoration(dpToPx(16), dpToPx(26)))
        }
    }

    private fun setupTicketsInfoBar() {
        with(viewModel.dataFlowUtil){
            binding.citiesTextView.setText("${departureCity.value}-${arrivalCity.value}")
            binding.ticketsPropertiesInfo.setText(
                with(flightProperties.value) {
                    "${dayOfMonth} ${month.accusative}, ${numOfPassengers} пассажир"
                }
            )
        }
    }
}