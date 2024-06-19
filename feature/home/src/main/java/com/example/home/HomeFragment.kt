package com.example.home

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.CitiesFlowUtil
import com.example.home.databinding.FragmentHomeBinding
import com.example.home.di.DaggerHomeComponent
import com.example.home.di.HomeComponent
import com.example.home.di.deps.HomeComponentDependenciesProvider
import com.example.home.rv.ListItemsDecorations
import com.example.home.rv.OffersAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeFragment: Fragment() {

    private lateinit var homeComponent: HomeComponent
    private val viewModel: HomeViewModel by viewModels{
        homeComponent.ticketsViewModelFactory()
    }
    @Inject
    lateinit var citiesFlowUtil: CitiesFlowUtil
    @Inject
    lateinit var searchModalFragment: BottomSheetDialogFragment

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val offersAdapter = OffersAdapter()


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

            val request = NavDeepLinkRequest.Builder.fromUri(
                "android-app://com.example/search_fragment".toUri()
            ).build()

            findNavController().navigate(request)
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