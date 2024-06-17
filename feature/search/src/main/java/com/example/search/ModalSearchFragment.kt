package com.example.search

import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.app.Dialog
import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.data.CitiesFlowUtil
import com.example.search.databinding.BottomSheetBinding
import com.example.search.di.DaggerSearchComponent
import com.example.search.di.SearchComponent
import com.example.search.di.deps.SearchComponentDependenciesProvider
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch
import javax.inject.Inject


class ModalSearchFragment: BottomSheetDialogFragment() {
    private var _binding: BottomSheetBinding? = null
    private val binding get() = _binding!!

    private lateinit var searchComponent: SearchComponent
    @Inject
    lateinit var citiesFlowUtil: CitiesFlowUtil

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

        subscribeUi()
        setupPopularPlacesList()
        setupDeleteButton()

        return binding.root
    }

    private fun setupDeleteButton() {
        binding.deleteButton.setOnClickListener{
            binding.editTextCityTo.setText("")
        }
    }

    private fun setupPopularPlacesList() {
        binding.listPopularPlaces.popularPlace1.setOnClickListener {
            binding.editTextCityTo.setText(it.findViewById<TextView>(R.id.text_view_city_1).text)
        }
        binding.listPopularPlaces.popularPlace2.setOnClickListener {
            binding.editTextCityTo.setText(it.findViewById<TextView>(R.id.text_view_city_2).text)
        }
        binding.listPopularPlaces.popularPlace3.setOnClickListener {
            binding.editTextCityTo.setText(it.findViewById<TextView>(R.id.text_view_city_3).text)
        }
    }


    private fun subscribeUi() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
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

    override fun onDestroyView() {
        citiesFlowUtil.departureCityChanged(binding.editTextCityFrom.text.toString())
        citiesFlowUtil.arrivalCityChanged(binding.editTextCityTo.text.toString())

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