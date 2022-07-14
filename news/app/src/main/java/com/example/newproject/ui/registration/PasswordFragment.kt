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
import com.example.newproject.databinding.FragmentPasswordBinding
import com.example.newproject.ui.api.models.AccountLogin
import com.example.newproject.ui.api.models.AccountPassword
import com.example.newproject.utils.Status
import com.example.newproject.utils.showToast


class PasswordFragment : Fragment() {

    private lateinit var binding: FragmentPasswordBinding
    private val args: PasswordFragmentArgs by navArgs()
    private val viewModel by lazy { PasswordViewModel() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPasswordBinding.inflate(inflater,container,false)
        binding.toolbar2.textToolbar.text = "Пароль"
        binding.toolbar2.backButton.setOnClickListener {
            activity?.onBackPressed()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // код из фрагмета смс
       val code = args.code
        Log.e("CODE","$code")


        binding.btnSavePassword.setOnClickListener {
            val pass = binding.newPassword.text.toString()
            val pass2 = binding.newPasswordDouble.text.toString()
                if ((pass == pass2) && (pass.isNotEmpty() && pass2.isNotEmpty())) {
                    postPassword(pass,pass2)

                } else {
                    Toast.makeText(context, "Пароли не совпадают", Toast.LENGTH_LONG).show()
                }
            }
        }

    private fun postPassword(pass: String,pass2: String) {
        val code = args.code.toString()
        Log.e("CODE",code)
        val accountPassword = AccountPassword(
            code,
            pass,
            pass2
        )

        // отправляем номер во RegistrationViewModel
        viewModel.getPassword(accountPassword)
        viewModel.pass.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.LOADING -> {
                    binding.btnSavePassword.isEnabled = false
                }
                Status.SUCCESS -> {
                    binding.btnSavePassword.isEnabled = true
                    findNavController().navigate(PasswordFragmentDirections.actionPasswordFragmentToHomeFragment())
                }

                Status.ERROR -> {
                    requireContext().showToast(it.message)
                    binding.btnSavePassword.isEnabled = true
                }
            }
        }

    }

    }
