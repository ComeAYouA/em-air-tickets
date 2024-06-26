package com.example.search

import android.app.Dialog
import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.net.toUri
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import com.example.data.DataFlowUtil
import com.example.search.databinding.BottomSheetBinding
import com.example.search.di.DaggerSearchComponent
import com.example.search.di.SearchComponent
import com.example.search.di.deps.SearchComponentDependenciesProvider
import com.example.ui.defaultNavAnimationsOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import javax.inject.Inject


internal class ModalSearchFragment: BottomSheetDialogFragment() {
    private var _binding: BottomSheetBinding? = null
    private val binding get() = _binding!!

    private lateinit var searchComponent: SearchComponent
    @Inject
    lateinit var dataFlowUtil: DataFlowUtil

    override fun onAttach(context: Context) {
        searchComponent = DaggerSearchComponent
            .builder()
            .deps(
                (requireActivity().applicationContext as SearchComponentDependenciesProvider)
                    .getSearchComponentDependencies()
            ).build()
        searchComponent.inject(this)

        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetBinding.inflate(
            inflater,
            container,
            false
        )

        setupPopularPlacesList()
        setupDeleteButton()
        setupFiltersList()
        setupSearchBar()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener { dialogInterface ->
            val bottomSheetDialog = dialogInterface as BottomSheetDialog
            setupFullHeight(bottomSheetDialog)
        }
        return dialog
    }

    private fun setupSearchBar() {
        binding.searchIcon.setOnClickListener{
            val request = NavDeepLinkRequest.Builder.fromUri(
                "android-app://com.example/offers_fragment".toUri()
            ).build()
            findNavController().navigate(
                request,
                defaultNavAnimationsOptions
            )
        }
        binding.editTextCityFrom.apply {
            setText(dataFlowUtil.departureCity.value)
            doAfterTextChanged { dataFlowUtil.updateDepartureCity(it?.toString()?:"")}
        }
        binding.editTextCityTo.apply{
            setText(dataFlowUtil.arrivalCity.value)
            doAfterTextChanged { dataFlowUtil.updateArrivalCity(it?.toString()?:"") }
        }
    }

    private fun setupFiltersList() {
        val action: (View) -> Unit = {
            findNavController().navigate(R.id.action_fragment_search_to_fragment_filter)
        }
        binding.filter1View.setOnClickListener(action)
        binding.filter3View.setOnClickListener(action)
        binding.filter4View.setOnClickListener(action)
        binding.filter2View.setOnClickListener{
            binding.editTextCityTo.setText(getString(com.example.ui.R.string.anywhere))
        }
    }

    private fun setupDeleteButton() {
        binding.deleteButton.setOnClickListener{
            binding.editTextCityTo.setText("")
        }
    }

    private fun setupPopularPlacesList() {
        val action: (View) -> Unit = {
            binding.editTextCityTo.setText(it.findViewById<TextView>(R.id.text_view_city).text)
        }
        binding.popularPlace1.setOnClickListener(action)
        binding.popularPlace2.setOnClickListener(action)
        binding.popularPlace3.setOnClickListener(action)
    }

    private fun setupFullHeight(bottomSheetDialog: BottomSheetDialog) {
        val bottomSheet =
            bottomSheetDialog.findViewById<View>(R.id.bottom_sheet) as LinearLayout?
        val behavior = bottomSheetDialog.behavior
        val layoutParams = bottomSheet!!.layoutParams

        val displayMetrics = Resources.getSystem().displayMetrics

        val windowHeight = displayMetrics.heightPixels
        layoutParams.height = windowHeight

        bottomSheet.layoutParams = layoutParams
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

}