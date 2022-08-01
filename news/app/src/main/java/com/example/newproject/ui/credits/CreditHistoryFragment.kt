package com.example.newproject.ui.credits

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newproject.customView.ItemHistoryAdapter
import com.example.newproject.databinding.FragmentCreditHistoryBinding
import com.example.newproject.ui.api.models.creditTest.creditTestHisory


class CreditHistoryFragment : Fragment(), ItemHistoryAdapter.OnClick {

    private lateinit var binding: FragmentCreditHistoryBinding


    private val adapter by lazy { ItemHistoryAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCreditHistoryBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val test = arrayListOf(
            creditTestHisory(
                1,
                "Кредит от 28.01.2022 ",
                "На рассмотрении",
                "Доступный на короткое время, с частым погашением в отделениях",
                10000,
                "14%",
                "12 мес"
            ),
            creditTestHisory(
                2,
                "Кредит от 28.01.2022 ",
                "Погашен",
                "Доступный на короткое время, с частым погашением в отделениях",
                10000,
                "14%",
                "12 мес"
            ),
            creditTestHisory(
                3,
                "Кредит от 28.01.2022 ",
                "На рассмотрении",
                "Доступный на короткое время, с частым погашением в отделениях",
                10000,
                "14%",
                "12 мес"
            ),
            creditTestHisory(
                4,
                "Кредит от 28.01.2022 ",
                "Отклонён",
                "Доступный на короткое время, с частым погашением в отделениях",
                10000,
                "14%",
                "12 мес"
            )
        )

        //ItemHistoryAdapter - adapter

        //item_history - layout

        //model - creditTestHistory

        adapter.setList(test)
        binding.testHistory.adapter = adapter
        binding.testHistory.layoutManager = LinearLayoutManager(context)


    }

    override fun onClicked(holder: ItemHistoryAdapter.ViewHolder, position: Int) {
        Toast.makeText(
            requireContext(),
            adapter.getItem(position)?.status.toString(),
            Toast.LENGTH_LONG
        ).show()
    }

}