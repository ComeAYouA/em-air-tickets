package com.example.offer

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.offer.di.DaggerOffersComponent
import com.example.offer.di.OffersComponent
import com.example.offer.di.deps.OffersComponentDependenciesProvider
import com.example.offer.rv.OfferAdapter
import com.example.offer.rv.PropertyAdapter
import com.example.offers.databinding.LayoutOffersBinding
import com.example.ui.defaultNavAnimationsOptions
import com.example.ui.dpToPx
import com.example.ui.rv.HorizontalListItemDecoration
import com.example.ui.rv.VerticalListItemDecoration
import kotlinx.coroutines.launch

internal class OffersFragment: Fragment() {
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LayoutOffersBinding.inflate(inflater, container, false)

        setupPropertiesRv()
        setupSearchBar()
        setupOffersRv()
        setupSeeAllButton()

        return binding.root
    }

    override fun onDestroyView() {
        viewModel.dataFlowUtil.updateDepartureCity(binding.editTextCityFrom.text.toString())
        viewModel.dataFlowUtil.updateArrivalCity(binding.editTextCityTo.text.toString())

        super.onDestroyView()

        _binding = null
    }

    private fun setupSeeAllButton() {
        binding.seeAllButton.setOnClickListener{
            val request = NavDeepLinkRequest.Builder.fromUri(
                "android-app://com.example/tickets_fragment".toUri()
            ).build()
            findNavController().navigate(
                request,
                defaultNavAnimationsOptions
            )
        }
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

    private fun setupOffersRv() {
        binding.rvTickets.apply {
            adapter = offerAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(VerticalListItemDecoration(dpToPx(8), 0))
        }
    }

    private fun setupSearchBar() {
        binding.editTextCityFrom.apply {
            setText(viewModel.dataFlowUtil.departureCity.value)
            doAfterTextChanged { viewModel.dataFlowUtil.updateDepartureCity(it?.toString()?:"")}
        }
        binding.editTextCityTo.apply{
            setText(viewModel.dataFlowUtil.arrivalCity.value)
            doAfterTextChanged { viewModel.dataFlowUtil.updateArrivalCity(it?.toString()?:"") }
        }
        binding.replaceButton.setOnClickListener{
            val temp = binding.editTextCityFrom.text
            binding.editTextCityFrom.text = binding.editTextCityTo.text
            binding.editTextCityTo.text = temp
        }
        binding.deleteButton.setOnClickListener{
            binding.editTextCityTo.setText("")
        }
        binding.backButton.setOnClickListener{
            findNavController().popBackStack()
        }
    }

    private fun setupPropertiesRv() {
        binding.rvProperties.apply {
            adapter = propertiesAdapter.apply {
                setData(viewModel.properties)
                setOnclickListener(
                    propertiesRVonClickListener(viewModel)
                )
            }
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            addItemDecoration(HorizontalListItemDecoration(dpToPx(8), dpToPx(16)))
        }
    }
}