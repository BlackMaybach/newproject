package com.example.newproject.ui.calculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.newproject.R
import com.example.newproject.databinding.FragmentCalculatorBinding
import com.example.newproject.utils.gone


class CalculatorFragment : Fragment() {


    private lateinit var binding : FragmentCalculatorBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalculatorBinding.inflate(inflater,container,false)
        binding.toolbar1.textViewStart.text = "Кредитный калькулятор"
        binding.toolbar1.toolbarExitButton.gone()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val arrСalculation = listOf("Платежи равными долями")

        val mEditSpinnerCalculation = binding.editSpinnerCalculation
        val adapterCalculation = ArrayAdapter(
            requireContext(), android.R.layout.simple_spinner_dropdown_item,
            arrСalculation
        )

        mEditSpinnerCalculation.setAdapter(adapterCalculation)
    }


}