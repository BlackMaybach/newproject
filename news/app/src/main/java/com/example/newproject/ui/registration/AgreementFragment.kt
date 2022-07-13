package com.example.newproject.ui.registration

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.newproject.R
import com.example.newproject.databinding.FragmentAgreementBinding
import com.example.newproject.utils.Status
import com.example.newproject.utils.showToast


class AgreementFragment : Fragment() {


    private lateinit var binding: FragmentAgreementBinding
    private val viewModel by lazy { AgreementViewModel() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAgreementBinding.inflate(inflater,container,false)
        binding.toolbar2.textToolbar.text = "Соглашение"
        binding.toolbar2.backButton.setOnClickListener {
            activity?.onBackPressed()
        }
        getAgreementText()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val number = 1

        binding.btnConfirm.setOnClickListener {
            findNavController().navigate(AgreementFragmentDirections.actionAgreementFragmentToRegistrationFragment(number))
        }
    }

    fun getAgreementText() {
        viewModel.getAgreementText()
        viewModel.agreementText.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.agreementText.text = it.data
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        binding.agreementText.text = Html.fromHtml(it.data,Html.FROM_HTML_MODE_LEGACY)
                    } else {
                        binding.agreementText.text = Html.fromHtml(it.data)
                    }

                }
                Status.ERROR -> {
                    requireContext().showToast(it.message)
                }
            }
        }
    }

}