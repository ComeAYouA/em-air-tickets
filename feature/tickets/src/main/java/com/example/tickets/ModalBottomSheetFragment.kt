package com.example.tickets

import android.app.Dialog
import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.data.CitiesFlowUtil
import com.example.tickets.databinding.BottomSheetBinding
import com.example.tickets.databinding.FragmentTicketsBinding
import com.example.tickets.di.deps.TicketsComponentDependenciesProvider
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch


class ModalBottomSheetFragment : BottomSheetDialogFragment() {

    private var _binding: BottomSheetBinding? = null
    private val binding get() = _binding!!

    private lateinit var citiesFlowUtil: CitiesFlowUtil

    override fun onAttach(context: Context) {
        super.onAttach(context)
        citiesFlowUtil = (requireActivity().applicationContext as TicketsComponentDependenciesProvider)
            .getTicketsComponentDependencies().citiesFlowUtil
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

        return binding.root
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


    companion object {
        const val TAG = "ModalBottomSheet"
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