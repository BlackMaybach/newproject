package com.example.newproject.ui.registration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.newproject.R
import com.example.newproject.databinding.FragmentAgreementBinding


class AgreementFragment : Fragment() {


    private lateinit var binding: FragmentAgreementBinding

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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val number = 1

        binding.btnConfirm.setOnClickListener {
            findNavController().navigate(AgreementFragmentDirections.actionAgreementFragmentToRegistrationFragment(number))
        }
    }

}