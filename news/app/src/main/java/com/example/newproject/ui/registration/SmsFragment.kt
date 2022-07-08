package com.example.newproject.ui.registration

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.newproject.R
import com.example.newproject.databinding.FragmentSmsBinding


class SmsFragment : Fragment() {
    private lateinit var binding: FragmentSmsBinding
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

        binding.btnToPasswordFragment.setOnClickListener {
            val code = binding.smsCodeET.text.toString()
                if(code.isNullOrEmpty()) {
                    Toast.makeText(context,"Введите код",Toast.LENGTH_LONG).show()
                } else {
                    findNavController().navigate(SmsFragmentDirections.actionSmsFragmentToPasswordFragment(code.toInt()))
                }

        }
    }

}