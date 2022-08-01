package com.example.newproject.ui.credits

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newproject.customView.ItemMainAdapter
import com.example.newproject.databinding.FragmentCreditMainBinding
import com.example.newproject.ui.api.models.creditTest.creditTest


class CreditMainFragment : Fragment() {

    private lateinit var binding: FragmentCreditMainBinding
    private val adapter by lazy { ItemMainAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCreditMainBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var testMain = arrayListOf(
            creditTest(5.25, 1.00, 100.00),
            creditTest(6.25, 1.00, 200.00),
            creditTest(7.25, 1.00, 300.00),
            creditTest(8.25, 1.00, 400.00),
            creditTest(9.25, 1.00, 500.00),
            creditTest(10.25, 1.00, 600.00),
            creditTest(11.25, 1.00, 700.00),
        )

        //ItemMainAdapter - adapter
        //item_main - layout
        //model - creditTest

        adapter.setList(testMain)
        binding.testMain.adapter = adapter
        binding.testMain.layoutManager = LinearLayoutManager(context)

    }

}