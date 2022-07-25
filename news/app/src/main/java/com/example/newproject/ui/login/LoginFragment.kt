package com.example.newproject.ui.login

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.newproject.R
import com.example.newproject.databinding.FragmentLoginBinding
import com.example.newproject.ui.api.models.AccountLogin
import com.example.newproject.ui.registration.RegistrationFragmentDirections
import com.example.newproject.ui.registration.RegistrationViewModel
import com.example.newproject.utils.Status
import com.example.newproject.utils.showToast


class LoginFragment : Fragment() {


    private lateinit var binding: FragmentLoginBinding
    private val viewModel by lazy { LoginViewModel() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.toolbar2.textToolbar.text = "Войти"
        binding.toolbar2.backButton.setOnClickListener {
            activity?.onBackPressed()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var maxNumber = 9

        val phone = "0505945500"
        val password = "12qw!@QW"
        binding.loginPhoneET.text = Editable.Factory.getInstance().newEditable(phone)
        binding.loginPasswordET.text = Editable.Factory.getInstance().newEditable(password)


        binding.toHomeFragment.setOnClickListener {


            val numberLogin = binding.loginPhoneET.text.toString()
            val passwordLogin = binding.loginPasswordET.text.toString()
            if (numberLogin.isNullOrEmpty()) {
                Toast.makeText(context, "Введите номер", Toast.LENGTH_LONG).show()
            } else if (numberLogin.length < maxNumber) {
                Toast.makeText(context, "Номер должен остоять из 9 цифр", Toast.LENGTH_LONG).show()
            } else if (passwordLogin.isNullOrEmpty()) {
                Toast.makeText(context, "Введите пароль", Toast.LENGTH_LONG).show()
            } else {
                getLogin()
                viewModel.clear()
            }
        }
    }

    private fun getLogin() {
        val numberLogin = binding.loginPhoneET.text.toString()
        val passwordLogin = binding.loginPasswordET.text.toString()
        val login = AccountLogin(
            numberLogin,
            passwordLogin
        )
        viewModel.getLogin(login)

        viewModel.loginData.observe(viewLifecycleOwner) {
            if (it != null) {
                when (it.status) {
                    Status.LOADING -> {
                        binding.toHomeFragment.isEnabled = false
                    }
                    Status.SUCCESS -> {
                        binding.toHomeFragment.isEnabled = true
                        findNavController().navigate(R.id.homeFragment)

                    }
                    Status.ERROR -> {
                        binding.toHomeFragment.isEnabled = true
                        if (it != null) {
                            requireContext().showToast(it.message)
                        }

                    }
                }
            }
        }
    }

}