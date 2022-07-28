package com.example.newproject.ui.calculator

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.fragment.findNavController
import com.example.newproject.databinding.FragmentCalculatorBinding
import com.example.newproject.ui.api.models.creditCalculator.PostCalculation
import com.example.newproject.utils.PaymentType
import com.example.newproject.utils.Status
import com.example.newproject.utils.gone
import com.example.newproject.utils.showToast
import java.text.SimpleDateFormat
import java.util.*


class CalculatorFragment : Fragment() {


    private lateinit var binding: FragmentCalculatorBinding

    private val viewModel by lazy { CalculatorViewModel() }

    private var idEnum = 0

    var cal: Calendar = Calendar.getInstance()

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dateValue = binding.firstPaymentDate

        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(
                view: DatePicker, year: Int, monthOfYear: Int,
                dayOfMonth: Int
            ) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }
        }

        ///         DatePickerDialog        ///
        binding.firstPaymentDate.setOnClickListener {
            DatePickerDialog(
                requireContext(),
                dateSetListener,
                // set DatePickerDialog to point to today's date when it loads up
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        ///       Отправка данных в функцию     ///
        binding.btnSend.setOnClickListener {
            val loanAmount = binding.loanAmount.text.toString().toInt()
            val period = binding.period.text.toString().toInt()
            val percentsBaseType = binding.percentsBaseType.text.toString().toInt()
            val firstPaymentDate = binding.firstPaymentDate.text.toString()
            val discountPeriod = binding.discountPeriod.text.toString().toInt()

            Toast.makeText(
                requireContext(),
                "$loanAmount $period $percentsBaseType $firstPaymentDate $discountPeriod",
                Toast.LENGTH_SHORT
            ).show()

            sendCalculation(loanAmount, period, percentsBaseType, firstPaymentDate, discountPeriod)
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
                    findNavController().navigate(CalculatorFragmentDirections.actionCalculatorFragmentToShowCalculationFragment(it.data!!))
                }

                Status.ERROR -> {
                    requireContext().showToast(it.message)
                    binding.btnSend.isEnabled = true
                }
            }
        }
    }

    private fun updateDateInView() {
        val myFormat = "yyyy-MM-dd" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        dateValue!!.text = sdf.format(cal.time)
    }


}