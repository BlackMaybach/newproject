package com.example.newproject.ui.calculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newproject.customView.CalculationAdapter
import com.example.newproject.databinding.FragmentShowCalculationBinding


class ShowCalculationFragment : Fragment() {

    private lateinit var binding: FragmentShowCalculationBinding

    private val args: ShowCalculationFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentShowCalculationBinding.inflate(inflater,container,false)
        binding.toolbar2.textToolbar.text = "Результат расчёта"
        binding.toolbar2.backButton.setOnClickListener {
            activity?.onBackPressed()
        }
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val array = args.calculation
        val recycler = binding.recyclerCalculation
        recycler.layoutManager = LinearLayoutManager(requireContext())

        recycler.adapter = CalculationAdapter(array)

    }


}