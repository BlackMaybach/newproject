package com.example.newproject.ui.registration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.newproject.databinding.FragmentRegistrationBinding
import com.example.newproject.ui.api.models.Register
import com.example.newproject.utils.Status
import com.example.newproject.utils.showToast

class RegistrationFragment : Fragment() {

    private lateinit var binding: FragmentRegistrationBinding
    private val viewModel by lazy { RegistrationViewModel() }
    private val args: RegistrationFragmentArgs by navArgs()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        binding.toolbar2.textToolbar.text = "Авторизация"
        binding.toolbar2.backButton.setOnClickListener {
            activity?.onBackPressed()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toAgreementFragment.setOnClickListener {
            findNavController().navigate(RegistrationFragmentDirections.actionRegistrationFragmentToAgreementFragment())
        }

        // если есть 1 - чекбокс чекед и кнопка работает
        val checkbox = binding.registrationCheckbox
        val button = binding.btnToSmsFragment
        val mMaxLength = 12
        var number = args.number


        if (number == 1) {
            checkbox.isChecked = true
            button.isEnabled = true

            binding.btnToSmsFragment.setOnClickListener {

                val code = binding.registrationPhoneET.text.toString()
                if (code.isNullOrEmpty()) {
                    Toast.makeText(context, "Введите номер телефона", Toast.LENGTH_LONG).show()
                } else if (code.length > mMaxLength) {
                    Toast.makeText(context, "Номер не должен превышать 12 знаков", Toast.LENGTH_LONG).show()
                } else {
                    postNumber()
                }
            }
            // если нет 1 - чекбокс не чекед и кнопка не работает
        } else {
            button.isEnabled = false
            button.setOnClickListener {
                Toast.makeText(context, "DISABLED", Toast.LENGTH_LONG).show()
            }
        }
        // на нажатие чекбокса
        checkbox.setOnCheckedChangeListener { compoundButton, checked ->
            if (checked) {
                button.isEnabled = true
                binding.btnToSmsFragment.setOnClickListener {

                    val code = binding.registrationPhoneET.text.toString()
                    if (code.isNullOrEmpty()) {
                        Toast.makeText(context, "Введите номер телефона", Toast.LENGTH_LONG).show()
                    } else if (code.length > mMaxLength) {
                        Toast.makeText(context, "Номер не должен превышать 12 знаков", Toast.LENGTH_LONG).show()
                    } else {
//                        findNavController().navigate(RegistrationFragmentDirections.actionRegistrationFragmentToSmsFragment())
                        postNumber()
                    }

                }
            } else {
                button.isEnabled = false
                Toast.makeText(context, "Button is invisible", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun postNumber() {
        val number = Register(
            null,
            null,
            null,
            null,
            null,
            null,
            binding.registrationPhoneET.text.toString(),
            null,
            null
        )

        // отправляем номер во RegistrationViewModel
        viewModel.getNumberFragment(number)

        viewModel.registration.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.LOADING -> {
                    binding.btnToSmsFragment.isEnabled = false
                }
                Status.SUCCESS -> {
                    binding.btnToSmsFragment.isEnabled = true
                    val numberArg = binding.registrationPhoneET.text.toString()
                    findNavController().navigate(
                        RegistrationFragmentDirections.actionRegistrationFragmentToSmsFragment(
                            numberArg
                        )
                    )
                }
                Status.ERROR -> {
                    requireContext().showToast(it.message)
                    binding.btnToSmsFragment.isEnabled = true
                }
            }
        }

    }

}

