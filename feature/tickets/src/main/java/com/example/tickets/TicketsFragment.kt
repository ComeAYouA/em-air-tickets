package com.example.tickets

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ProgressBar
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.data.CitiesFlowUtil
import com.example.tickets.databinding.FragmentTicketsBinding
import com.example.tickets.di.DaggerTicketsComponent
import com.example.tickets.di.TicketsComponent
import com.example.tickets.di.deps.TicketsComponentDependenciesProvider
import com.example.tickets.rv.ListItemsDecorations
import com.example.tickets.rv.OffersAdapter
import kotlinx.coroutines.launch
import javax.inject.Inject

class TicketsFragment: Fragment() {

    private lateinit var ticketsComponent: TicketsComponent
    private val viewModel: TicketsViewModel by viewModels{
        ticketsComponent.ticketsViewModelFactory()
    }
    @Inject
    lateinit var citiesFlowUtil: CitiesFlowUtil

    private var _binding: FragmentTicketsBinding? = null
    private val binding get() = _binding!!

    private val offersAdapter = OffersAdapter()

    override fun onAttach(context: Context) {
        ticketsComponent = DaggerTicketsComponent
            .builder()
            .deps(
                (requireActivity().applicationContext as TicketsComponentDependenciesProvider)
                    .getTicketsComponentDependencies()
            )
            .build()

        ticketsComponent.inject(this)

        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTicketsBinding.inflate(inflater, container, false)

        setupCityToButton()
        setupOffersRV()
        subscribeUi()

        return binding.root
    }

    override fun onDestroyView() {
        citiesFlowUtil.departureCityChanged(binding.editTextCityFrom.text.toString())

        super.onDestroyView()

        _binding = null
    }

    private fun subscribeUi() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                launch{
                    viewModel.offers.collect{ offers ->
                        if (offers.isNotEmpty()) binding.progressBarOffers.visibility = View.INVISIBLE

                        offersAdapter.setData(offers)
                    }
                }
                launch{
                    citiesFlowUtil.departureCity.collect{
                        binding.editTextCityFrom.setText(it)
                    }
                }
                launch{
                    citiesFlowUtil.arrivalCity.collect{
                        binding.editTextCityTo.setText(it)
                    }
                }
            }
        }
    }


    private fun setupCityToButton(){
        binding.editTextCityTo.setOnClickListener{
            citiesFlowUtil.departureCityChanged(binding.editTextCityFrom.text.toString())

            val modalBottomSheet = ModalBottomSheetFragment()
            modalBottomSheet.show(requireActivity().supportFragmentManager, ModalBottomSheetFragment.TAG)
        }
    }

    private fun setupOffersRV(){
        val displayMetrics = Resources.getSystem().displayMetrics

        binding.rvOffers.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false

            )
            adapter = offersAdapter

            addItemDecoration(
                ListItemsDecorations(
                    innerDivider = TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP,
                        67f,
                        displayMetrics
                    ).toInt(),
                    outerDivider = TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP,
                        16f,
                        displayMetrics
                    ).toInt(),
                )
            )
        }
    }

}