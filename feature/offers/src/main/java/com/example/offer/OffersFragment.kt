package com.example.offer

import android.app.DatePickerDialog
import android.content.Context
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.offer.di.DaggerOffersComponent
import com.example.offer.di.OffersComponent
import com.example.offer.di.deps.OffersComponentDependenciesProvider
import com.example.offer.rv.OfferAdapter
import com.example.offer.rv.PropertyAdapter
import com.example.offer.ui.model.toDayOfWeek
import com.example.offer.ui.model.toMonth
import com.example.offers.databinding.LayoutOffersBinding
import com.example.ui.dpToPx
import com.example.ui.rv.HorizontlListItemDecoration
import com.example.ui.rv.VerticalListItemDecoration
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class OffersFragment: Fragment() {

    private lateinit var offersComponent: OffersComponent

    private var _binding: LayoutOffersBinding? = null
    private val binding: LayoutOffersBinding
        get() = _binding!!

    private val propertiesAdapter by lazy {
        PropertyAdapter()
    }

    private val offerAdapter by lazy {
        OfferAdapter()
    }

    private val viewModel: OffersViewModel by viewModels {
        offersComponent.offersViewModelFactory()
    }

    private val propertiesRVonClickListener: PropertyAdapter.OnClickListener =
        object: PropertyAdapter.OnClickListener{
            override fun PropertyAdapter.onClick(position: Int) {
                when(position){
                    0 -> { this.pickDate(position) }
                }
            }

        }

    private fun PropertyAdapter.pickDate(position: Int) {
        DatePickerDialog(requireActivity()).apply {
            setOnDateSetListener { _, year, month, dayOfMonth ->
                val calendar = Calendar.getInstance()
                calendar.set(year, month, dayOfMonth, 0, 0)
                calendar.firstDayOfWeek = Calendar.MONDAY

                data[position].title = "$dayOfMonth ${(month + 1).toMonth().title}"
                val dayOfWeek =  (calendar.get(Calendar.DAY_OF_WEEK) - 1).toDayOfWeek().title

                data[position].subtitle = ", $dayOfWeek"
                notifyItemChanged(position)
            }
        }.show()
    }


    override fun onAttach(context: Context) {
        offersComponent = DaggerOffersComponent.builder()
            .deps(
                (requireActivity().applicationContext as OffersComponentDependenciesProvider)
                    .getOffersComponentDependencies()
            )
            .build()

        offersComponent.inject(this)

        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        subscribeUi()
    }

    private fun subscribeUi() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.offers.collect{
                    if (it.isNotEmpty()) binding.ticketsOffersProgressBar.visibility = View.INVISIBLE
                    offerAdapter.setData(it.take(3))
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LayoutOffersBinding.inflate(inflater, container, false)

        setupPropertiesRv()
        setupSearchBar()
        setupOffersRv()

        return binding.root
    }

    private fun setupOffersRv() {
        binding.rvTickets.apply {
            adapter = offerAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(VerticalListItemDecoration(dpToPx(8), 0))
        }
    }

    private fun setupSearchBar() {
        binding.editTextCityFrom.apply {
            setText(viewModel.citiesFlowUtil.departureCity.value)
            doAfterTextChanged { viewModel.citiesFlowUtil.departureCityChanged(it?.toString()?:"")}
        }
        binding.editTextCityTo.apply{
            setText(viewModel.citiesFlowUtil.arrivalCity.value)
            doAfterTextChanged { viewModel.citiesFlowUtil.arrivalCityChanged(it?.toString()?:"") }
        }
        binding.replaceButton.setOnClickListener{
            val temp = binding.editTextCityFrom.text
            binding.editTextCityFrom.text = binding.editTextCityTo.text
            binding.editTextCityTo.text = temp
        }
        binding.deleteButton.setOnClickListener{
            binding.editTextCityTo.setText("")
        }
    }

    override fun onDestroyView() {
        viewModel.citiesFlowUtil.departureCityChanged(binding.editTextCityFrom.text.toString())
        viewModel.citiesFlowUtil.arrivalCityChanged(binding.editTextCityTo.text.toString())

        super.onDestroyView()

        _binding = null
    }

    private fun setupPropertiesRv() {
        binding.rvProperties.apply {
            adapter = propertiesAdapter.apply {
                setOnclickListener(propertiesRVonClickListener)
            }
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            addItemDecoration(HorizontlListItemDecoration(dpToPx(8), dpToPx(16)))
        }
    }
}