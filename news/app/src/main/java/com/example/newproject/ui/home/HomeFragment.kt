package com.example.newproject.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newproject.R
import com.example.newproject.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.link.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.elet.kg"))
            startActivity(i)
        }

        binding.linkButton.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.elet.kg"))
            startActivity(i)
        }
    }

}