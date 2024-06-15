package com.example.tickets

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tickets.databinding.FragmentTicketsBinding
import com.example.tickets.di.DaggerTicketsComponent
import com.example.tickets.di.TicketsComponent
import com.example.tickets.di.TicketsComponentDependenciesProvider
import com.example.tickets.rv.ListItemsDecorations
import com.example.tickets.rv.OffersAdapter
import javax.inject.Inject

class TicketsFragment: Fragment() {

    internal lateinit var ticketsComponent: TicketsComponent
    @Inject
    lateinit var viewModel: TicketsViewModel

    private var _binding: FragmentTicketsBinding? = null
    private val binding get() = _binding!!

    private lateinit var cityToEditText: EditText
    private lateinit var offersRV: RecyclerView
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

        cityToEditText = binding.editTextCityTo

        cityToEditText.setOnClickListener{
            val modalBottomSheet = ModalBottomSheetFragment()
            modalBottomSheet.show(requireActivity().supportFragmentManager, ModalBottomSheetFragment.TAG)
        }

        offersRV = binding.rvOffers

        val displayMetrics = Resources.getSystem().displayMetrics

        offersRV.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
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
        return binding.root
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}