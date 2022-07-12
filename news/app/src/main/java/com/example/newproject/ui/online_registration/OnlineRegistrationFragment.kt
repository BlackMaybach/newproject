package com.example.newproject.ui.online_registration

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import androidx.fragment.app.Fragment
import com.example.newproject.databinding.FragmentOnlineRegistrationBinding
import com.reginald.editspinner.EditSpinner


class OnlineRegistrationFragment : Fragment() {

    private lateinit var binding: FragmentOnlineRegistrationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentOnlineRegistrationBinding.inflate(inflater,container,false)
        binding.toolbar2.textToolbar.text = "Онлайн идентификация"
        binding.toolbar2.backButton.setOnClickListener {
            activity?.onBackPressed()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val arrRegion = listOf("Чуйская область","Баткенская область")
        val arrDistrict = listOf("Бишкек","Баткен")

        val mEditSpinnerRegion = binding.editSpinnerRegion
        val adapterRegion = ArrayAdapter(
            requireContext(), android.R.layout.simple_spinner_dropdown_item,
            arrRegion
        )
        mEditSpinnerRegion.setAdapter(adapterRegion)

        val mEditSpinnerDistrict = binding.editSpinnerDistrict
        val adapterDistrict = ArrayAdapter(
            requireContext(), android.R.layout.simple_spinner_dropdown_item,
            arrDistrict
        )
        mEditSpinnerDistrict.setAdapter(adapterDistrict)


    }



}