package com.example.newproject.ui.addNew

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.newproject.R
import com.example.newproject.databinding.FragmentAddNewBinding
import com.example.newproject.databinding.FragmentCreditsBinding


class AddNewFragment : Fragment() {

private lateinit var binding : FragmentAddNewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddNewBinding.inflate(inflater, container, false)
        binding.toolbar1.textViewStart.text = "Новая заявка на кредит"
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val arrCanal = listOf("Электронный кошелёк", "Не электронный кошелёк")
        val arrProvider = listOf("Balance", "O деньги")

        val mEditSpinnerCanal = binding.editSpinnerCanal
        val adapterCanal = ArrayAdapter(
            requireContext(), android.R.layout.simple_spinner_dropdown_item,
            arrCanal
        )
        mEditSpinnerCanal.setAdapter(adapterCanal)

        val mEditSpinnerProvider = binding.editSpinnerProvider
        val adapterProvider = ArrayAdapter(
            requireContext(), android.R.layout.simple_spinner_dropdown_item,
            arrProvider
        )
        mEditSpinnerProvider.setAdapter(adapterProvider)


        val btn = binding.btnConfirm

        btn.setOnClickListener {
            val radioGroup = binding.radioGroup
            val sum = binding.creditSum.text.toString()
            val date = binding.creditDate.text.toString()
            val canal = binding.editSpinnerCanal.text.toString()
            val provider = binding.editSpinnerProvider.text.toString()
            val number = binding.creditNumber.text.toString()

            if (sum.isEmpty()) {
                Toast.makeText(requireContext(), "Заполните поле сумма", Toast.LENGTH_LONG).show()
            }
            else if(date.isEmpty()) {
                Toast.makeText(requireContext(), "Заполните поле срок кредита", Toast.LENGTH_LONG).show()
            }
            else if(canal.isEmpty()) {
                Toast.makeText(requireContext(), "Заполните поле канал выдачи", Toast.LENGTH_LONG).show()
            }
            else if(provider.isEmpty()) {
                Toast.makeText(requireContext(), "Заполните поле провайдеры", Toast.LENGTH_LONG).show()
            }
            else if(number.isEmpty()) {
                Toast.makeText(requireContext(), "Заполните поле номер", Toast.LENGTH_LONG).show()
            }
            else {
                Toast.makeText(requireContext(), "You have Selected $canal $provider $sum $date $number", Toast.LENGTH_LONG).show()
            }
        }
    }

}