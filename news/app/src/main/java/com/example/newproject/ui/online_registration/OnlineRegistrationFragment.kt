package com.example.newproject.ui.online_registration

import android.R
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.newproject.databinding.FragmentOnlineRegistrationBinding
import com.example.newproject.ui.api.models.references.Region
import com.example.newproject.ui.registration.SmsViewModel
import com.example.newproject.utils.Status
import com.example.newproject.utils.showToast
import com.reginald.editspinner.EditSpinner


class OnlineRegistrationFragment : Fragment() {

    private lateinit var binding: FragmentOnlineRegistrationBinding

    private val viewModel by lazy { OnlineRegistrationViewModel() }

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
        getReferencesOnlineReg()
    }

    private fun getReferencesOnlineReg() {
        viewModel.getReferences()

        viewModel.references.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {



                    val arrRegion: ArrayList<String> = ArrayList()
//                    it.data?.regions?.get(0)?.let { it1 -> arrDistrict.add(it1.name) }
                    val regions = it.data?.regions!!
                    for(regions in regions) {
                        arrRegion.add(regions.name)
                    }
                    val mEditSpinnerRegion = binding.editSpinnerRegion
                    val adapterRegion = ArrayAdapter(
                        requireContext(), android.R.layout.simple_spinner_dropdown_item,
                        arrRegion
                    )
                    mEditSpinnerRegion.setAdapter(adapterRegion)


                    val arrDistrict: ArrayList<String> = ArrayList()
//                    it.data?.areas?.get(0)?.let { it1 -> arrDistrict.add(it1.name) }
                    val areas = it.data?.areas!!


                    for(areas in areas) {
                        arrDistrict.add(areas.name)
                    }

                    val mEditSpinnerDistrict = binding.editSpinnerDistrict
                    val adapterDistrict = ArrayAdapter(
                        requireContext(), android.R.layout.simple_spinner_dropdown_item,
                        arrDistrict
                    )
                    mEditSpinnerDistrict.setAdapter(adapterDistrict)

                    val arrCity: ArrayList<String> = ArrayList()
//                    it.data?.cities?.get(0)?.let { it1 -> arrCity.add(it1.cityName) }
                    val cities = it.data?.cities!!
                    for(cities in cities) {
                        arrCity.add(cities.cityName)
                    }

                    val mEditSpinnerCity = binding.editSpinnerCity
                    val adapterCity = ArrayAdapter(
                        requireContext(), android.R.layout.simple_spinner_dropdown_item,
                        arrCity
                    )
                    mEditSpinnerCity.setAdapter(adapterCity)




                    
                }
                Status.ERROR -> {
                    requireContext().showToast(it.message)
                }
            }
        }
    }

}