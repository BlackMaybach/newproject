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
import com.example.newproject.databinding.FragmentSmsBinding
import com.example.newproject.ui.api.models.AccountLogin
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
        val mMaxLength = 6

        binding.btnToPasswordFragment.setOnClickListener {
            val password = binding.smsCodeET.text.toString()
            if (password.isNullOrEmpty()) {
                Toast.makeText(context, "Введите код", Toast.LENGTH_LONG).show()
            } else if (password.length < mMaxLength) {
                Toast.makeText(context, "Код состоит из 6 значений", Toast.LENGTH_LONG).show()
            }else {
                getCode(password)
            }
        }
    }

    private fun getCode(password: String) {
        val number = args.numberArg
        Log.e("NUMBER", number)

        val account = AccountLogin(
            number,
            password
        )

        // отправляем номер в RegistrationViewModel
        viewModel.getSmsCodeFragment(account)

        viewModel.smsCode.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.LOADING -> {
                    binding.btnToPasswordFragment.isEnabled = false
                }
                Status.SUCCESS -> {
                    binding.btnToPasswordFragment.isEnabled = true

                    findNavController().navigate(
                        SmsFragmentDirections.actionSmsFragmentToPasswordFragment(
                            password.toInt()
                        )
                    )
                }

                Status.ERROR -> {
                    requireContext().showToast(it.message)
                    binding.btnToPasswordFragment.isEnabled = true
                }
            }
        }

    }

}