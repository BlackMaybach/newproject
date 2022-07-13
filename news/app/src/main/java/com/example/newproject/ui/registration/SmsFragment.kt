package com.example.newproject.ui.registration

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.newproject.R
import com.example.newproject.databinding.FragmentSmsBinding
import com.example.newproject.ui.api.models.Otp
import com.example.newproject.ui.api.models.Register
import com.example.newproject.utils.Status
import com.example.newproject.utils.showToast


class SmsFragment : Fragment() {
    private lateinit var binding: FragmentSmsBinding
    private val viewModel by lazy { SmsViewModel() }

    private val args: SmsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSmsBinding.inflate(inflater, container, false)
        binding.toolbar2.textToolbar.text = "Код из СМС"
        binding.toolbar2.backButton.setOnClickListener {
            activity?.onBackPressed()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val number = args.numberArg.toInt()
//        Log.e("NUMBER", number.toString())

        binding.btnToPasswordFragment.setOnClickListener {
            val codeEditText = binding.smsCodeET.text.toString()
            if (codeEditText.isNullOrEmpty()) {
                Toast.makeText(context, "Введите код", Toast.LENGTH_LONG).show()
            } else {
                getCode(codeEditText)
            }
        }
    }

    private fun getCode(codeEditText: String) {
        val number = args.numberArg.toInt()
        Log.e("NUMBER", number.toString())


        // отправляем номер во RegistrationViewModel
        viewModel.getSmsCodeFragment(number,codeEditText)

        viewModel.smsCode.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.LOADING -> {
                    binding.btnToPasswordFragment.isEnabled = false
                }
                Status.SUCCESS -> {
                    binding.btnToPasswordFragment.isEnabled = true
                    findNavController().navigate(
                        SmsFragmentDirections.actionSmsFragmentToPasswordFragment(
                            codeEditText.toInt()
                        )
                    )
                }
                Status.ERROR -> {
                    requireContext().showToast(it.message)
                    binding.btnToPasswordFragment.isEnabled = true
                    findNavController().navigate(
                        SmsFragmentDirections.actionSmsFragmentToPasswordFragment(
                            codeEditText.toInt()
                        )
                    )
                }
            }
        }

    }

}