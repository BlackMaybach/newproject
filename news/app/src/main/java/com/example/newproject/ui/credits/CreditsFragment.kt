package com.example.newproject.ui.credits

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.newproject.customView.ViewPageAdapter
import com.example.newproject.databinding.FragmentCreditsBinding
import com.example.newproject.utils.gone


class CreditsFragment : Fragment() {

    private lateinit var binding: FragmentCreditsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreditsBinding.inflate(inflater, container, false)
        binding.toolbar1.textViewStart.text = "Кредит"
        binding.toolbar1.toolbarExitButton.gone()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var viewPager = binding.viewPager
        var tabLayout = binding.tabLayout
        val adapter = ViewPageAdapter(childFragmentManager)
        adapter.addFragment(CreditMainFragment(), "Текущие")
        adapter.addFragment(CreditHistoryFragment(), "История")

        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)


    }


}