package com.example.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.home.databinding.FragmentHomeBinding
import com.example.home.di.DaggerHomeComponent
import com.example.home.di.HomeComponent
import com.example.home.di.deps.HomeComponentDependenciesProvider
import com.example.home.rv.OffersAdapter
import com.example.ui.defaultNavAnimationsOptions
import com.example.ui.dpToPx
import com.example.ui.rv.HorizontalListItemDecoration
import kotlinx.coroutines.launch

internal class HomeFragment: Fragment() {
    private lateinit var homeComponent: HomeComponent

    private val viewModel: HomeViewModel by viewModels{
        homeComponent.ticketsViewModelFactory()
    }

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val offersAdapter by lazy { OffersAdapter() }

    override fun onAttach(context: Context) {
        homeComponent = DaggerHomeComponent
            .builder()
            .deps(
                (requireActivity().applicationContext as HomeComponentDependenciesProvider)
                    .getHomeComponentDependencies()
            )
            .build()

        homeComponent.inject(this)

        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        setupCityToButton()
        setupOffersRV()
        subscribeUi()

        return binding.root
    }

    override fun onDestroyView() {
        viewModel.dataFlowUtil.updateDepartureCity(binding.editTextCityFrom.text.toString())
        viewModel.dataFlowUtil.updateArrivalCity(binding.editTextCityTo.text.toString())

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
                    viewModel.dataFlowUtil.departureCity.collect{
                        binding.editTextCityFrom.setText(it)
                    }
                }
                launch{
                    viewModel.dataFlowUtil.arrivalCity.collect{
                        binding.editTextCityTo.setText(it)
                    }
                }
            }
        }
    }

    private fun setupCityToButton(){
        binding.editTextCityTo.setOnClickListener{
            viewModel.dataFlowUtil.updateDepartureCity(binding.editTextCityFrom.text.toString())

            val request = NavDeepLinkRequest.Builder.fromUri(
                "android-app://com.example/search_fragment".toUri()
            ).build()

            findNavController().navigate(
                request,
                defaultNavAnimationsOptions
            )
        }
    }

    private fun setupOffersRV(){
        binding.rvOffers.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false

            )
            adapter = offersAdapter

            addItemDecoration(
                HorizontalListItemDecoration(
                    innerDivider = dpToPx(67),
                    outerDivider = dpToPx(16),
                )
            )
        }
    }

}