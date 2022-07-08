package com.example.newproject.ui.registration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.newproject.R
import com.example.newproject.databinding.FragmentRegistrationBinding

class RegistrationFragment : Fragment() {

    private lateinit var binding: FragmentRegistrationBinding

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
        val number = args.number

        if (number == 1) {
            checkbox.isChecked = true
            button.isEnabled = true
            binding.btnToSmsFragment.setOnClickListener {
                findNavController().navigate(RegistrationFragmentDirections.actionRegistrationFragmentToSmsFragment())
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
                    findNavController().navigate(RegistrationFragmentDirections.actionRegistrationFragmentToSmsFragment())
                }
            } else {
                button.isEnabled = false
                Toast.makeText(context, "Button is invisible", Toast.LENGTH_SHORT).show()
            }
        }
    }

}

