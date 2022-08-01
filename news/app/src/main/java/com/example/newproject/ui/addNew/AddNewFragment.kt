package com.example.newproject.ui.addNew

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.newproject.R
import com.example.newproject.databinding.FragmentAddNewBinding
import com.example.newproject.ui.api.models.creditReferences.PayOutType
import com.example.newproject.utils.*


class AddNewFragment : Fragment() {

    private lateinit var binding: FragmentAddNewBinding

    private val viewModel by lazy { AddNewViewModel() }

    var typeName: String? = null
    var providerName: String? = null
    var enumL: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddNewBinding.inflate(inflater, container, false)
        binding.toolbar1.textViewStart.text = "Новая заявка на кредит"
        binding.toolbar1.toolbarExitButton.gone()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        viewModel.getCreditReferences()

        viewModel.references.observe(viewLifecycleOwner) {
            when (it.status) {



                Status.SUCCESS -> {
                    val payOutTypes = it.data?.payOutTypes as List<PayOutType>
                    val providers = it.data.providers

                    val creditType = binding.payOutTypes
                    val creditProvider = binding.providers

                    val adapterPayType = ArrayAdapter(
                        requireContext(),
                        android.R.layout.simple_spinner_item, payOutTypes
                    )
                    creditType.adapter = adapterPayType

                    creditType.onItemSelectedListener = object :
                        AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            parent: AdapterView<*>,
                            view: View, position: Int, id: Long
                        ) {
                            Toast.makeText(
                                requireContext(),
                                payOutTypes[position].value,
                                Toast.LENGTH_SHORT
                            ).show()
                            typeName = payOutTypes[position].value
                        }

                        override fun onNothingSelected(parent: AdapterView<*>) {

                        }
                    }

                    val adapterProvider = ArrayAdapter(
                        requireContext(),
                        android.R.layout.simple_spinner_item, providers
                    )
                    creditProvider.adapter = adapterProvider

                    creditProvider.onItemSelectedListener = object :
                        AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            parent: AdapterView<*>,
                            view: View, position: Int, id: Long
                        ) {
                            Toast.makeText(
                                requireContext(),
                                providers[position].providerName,
                                Toast.LENGTH_SHORT
                            ).show()
                            providerName = providers[position].providerName

                        }

                        override fun onNothingSelected(parent: AdapterView<*>) {

                        }
                    }
                    sendNewCredit()

                }
                Status.ERROR -> {
                    requireContext().showToast(it.message)
                }
            }
        }
    }


    private fun sendNewCredit() {
        val btn = binding.btnConfirm

        btn.setOnClickListener {

            val sum = binding.creditSum.text.toString()
            val date = binding.creditDate.text.toString()
            val number = binding.creditNumber.text.toString()
            val radioGroup = binding.radioGroup

            // Get the checked radio button id from radio group
            var id: Int = radioGroup.checkedRadioButtonId
            if (id != -1) { // If any radio button checked from radio group
                // Get the instance of radio button using id
                val radio: RadioButton = view?.findViewById(id)!!
                enumLanguage(radio.text as String)
            } else {
                // If no radio button checked in this radio group
                Toast.makeText(
                    requireContext(), "On button click : nothing selected",
                    Toast.LENGTH_SHORT
                ).show()
            }


            if (sum.isEmpty()) {
                Toast.makeText(requireContext(), "Заполните поле сумма", Toast.LENGTH_SHORT).show()
            } else if (date.isEmpty()) {
                Toast.makeText(requireContext(), "Заполните поле срок кредита", Toast.LENGTH_SHORT)
                    .show()
            } else if (number.isEmpty()) {
                Toast.makeText(requireContext(), "Заполните поле номер", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                    requireContext(),
                    "You have Selected $enumL $typeName $providerName $sum $date $number",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun enumLanguage(language: String) {
        for (status in Language.values()) {
            if (status.take == language) {
                enumL = status.lang
            }
        }
    }
}
