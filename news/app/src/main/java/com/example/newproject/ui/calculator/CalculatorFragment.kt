package com.example.newproject.ui.calculator

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.fragment.findNavController
import com.example.newproject.R
import com.example.newproject.databinding.FragmentCalculatorBinding
import com.example.newproject.ui.api.models.creditCalculator.PostCalculation
import com.example.newproject.utils.PaymentType
import com.example.newproject.utils.Status
import com.example.newproject.utils.gone
import com.example.newproject.utils.showToast
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*


class CalculatorFragment : Fragment() {


    private lateinit var binding: FragmentCalculatorBinding

    private val viewModel by lazy { CalculatorViewModel() }

    private var idEnum = 0


    private var dateValue: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalculatorBinding.inflate(inflater, container, false)
        binding.toolbar1.textViewStart.text = "Кредитный калькулятор"
        binding.toolbar1.toolbarExitButton.gone()
        return binding.root
    }

    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dateValue = binding.firstPaymentDate

        // when floationg acition button is clicked
        binding.firstPaymentDate.setOnClickListener {
            // Initiation date picker with
            // MaterialDatePicker.Builder.datePicker()
            // and building it using build()
            val datePicker = MaterialDatePicker.Builder.datePicker().build()

            datePicker.show(parentFragmentManager, "DatePicker")
            // Setting up the event for when ok is clicked
            datePicker.addOnPositiveButtonClickListener {
                // formatting date in dd-mm-yyyy format.
                val dateFormatter = SimpleDateFormat("yyyy-MM-dd")
                val date = dateFormatter.format(Date(it))

                dateValue!!.text = date
                Toast.makeText(requireContext(), "$date is selected", Toast.LENGTH_LONG).show()

            }

            // Setting up the event for when cancelled is clicked
            datePicker.addOnNegativeButtonClickListener {
                Toast.makeText(
                    requireContext(),
                    "${datePicker.headerText} is cancelled",
                    Toast.LENGTH_LONG
                ).show()
            }

            // Setting up the event for when back button is pressed
            datePicker.addOnCancelListener {
                Toast.makeText(requireContext(), "Date Picker Cancelled", Toast.LENGTH_LONG).show()
            }
        }


        ///       Отправка данных в функцию     ///
        binding.btnSend.setOnClickListener {
            val loanAmount = binding.loanAmount.text.toString()
            val period = binding.period.text.toString()
            val percentsBaseType = binding.percentsBaseType.text.toString()
            val firstPaymentDate = binding.firstPaymentDate.text.toString()
            val discountPeriod = binding.discountPeriod.text.toString()

            if (loanAmount.isEmpty()) {
                Toast.makeText(requireContext(), "Заполните поле сумма кредита", Toast.LENGTH_SHORT)
                    .show()
            } else if (period.isEmpty()) {
                Toast.makeText(requireContext(), "Заполните поле срок кредита", Toast.LENGTH_SHORT)
                    .show()
            } else if (percentsBaseType.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    "Заполните поле процентная ставка",
                    Toast.LENGTH_SHORT
                )
                    .show()
            } else if (firstPaymentDate.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    "Заполните поле дата первой выплаты",
                    Toast.LENGTH_SHORT
                )
                    .show()
            } else if (discountPeriod.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    "Заполните поле льготный период по осн. долгу",
                    Toast.LENGTH_SHORT
                )
                    .show()
            } else {
                sendCalculation(
                    loanAmount.toInt(),
                    period.toInt(),
                    percentsBaseType.toInt(),
                    firstPaymentDate,
                    discountPeriod.toInt()
                )
            }
        }


        ///       Spinner        ///
        val paymentTypeSpinner = binding.spinnerPayment
        val arrayPayment = PaymentType.values()

        val adapterPayType = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item, arrayPayment
        )
        paymentTypeSpinner.adapter = adapterPayType

        paymentTypeSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?, position: Int, id: Long
            ) {
                Toast.makeText(
                    requireContext(),
                    arrayPayment[position].id.toString(),
                    Toast.LENGTH_SHORT
                ).show()
                idEnum = arrayPayment[position].id

            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
    }


    ///       Отправка данных      ///
    private fun sendCalculation(
        loanAmount: Int,
        period: Int,
        percentsBaseType: Int,
        firstPaymentDate: String,
        discountPeriod: Int
    ) {
        val calculationInfo = PostCalculation(
            discountPeriod,
            firstPaymentDate,
            loanAmount,
            idEnum!!,
            percentsBaseType,
            period,
        )


        viewModel.sendInfo(calculationInfo)

        viewModel.info.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.LOADING -> {
                    binding.btnSend.isEnabled = false
                }
                Status.SUCCESS -> {
                    binding.btnSend.isEnabled = true
                    findNavController().navigate(
                        CalculatorFragmentDirections.actionCalculatorFragmentToShowCalculationFragment(
                            it.data!!
                        )
                    )
                }

                Status.ERROR -> {
                    requireContext().showToast(it.message)
                    binding.btnSend.isEnabled = true
                }
            }
        }
    }


}