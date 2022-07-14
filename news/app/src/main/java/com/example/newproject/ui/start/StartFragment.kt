package com.example.newproject.ui.start

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.navigation.fragment.findNavController
import com.example.newproject.databinding.FragmentStartBinding
import com.example.newproject.utils.gone


class StartFragment : Fragment() {

    private lateinit var binding: FragmentStartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartBinding.inflate(inflater,container,false)
        binding.toolbar1.toolbarExitButton.gone()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginFragment.setOnClickListener {
            findNavController().navigate(StartFragmentDirections.actionStartFragmentToLoginFragment())
        }

        binding.regFragment.setOnClickListener {
            findNavController().navigate(StartFragmentDirections.actionStartFragmentToRegistrationFragment(0))
        }

        binding.link.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.elet.kg"))
            startActivity(i)
        }
    }

}
