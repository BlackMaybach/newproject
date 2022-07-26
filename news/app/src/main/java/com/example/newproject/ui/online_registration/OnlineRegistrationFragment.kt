package com.example.newproject.ui.online_registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.newproject.databinding.FragmentOnlineRegistrationBinding
import com.example.newproject.ui.api.models.references.Area
import com.example.newproject.ui.api.models.references.City
import com.example.newproject.ui.api.models.references.Region
import com.example.newproject.utils.Status
import com.example.newproject.utils.showToast


class OnlineRegistrationFragment : Fragment() {

    private lateinit var binding: FragmentOnlineRegistrationBinding

    private val viewModel by lazy { OnlineRegistrationViewModel() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentOnlineRegistrationBinding.inflate(inflater, container, false)
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
                    val regions = it.data?.regions!!
                    val areas = it.data.areas
                    val city = it.data.cities
                    regionsSpinner(regions, areas, city)
                }
                Status.ERROR -> {
                    requireContext().showToast(it.message)
                }
            }
        }
    }


    private fun regionsSpinner(regions: List<Region>, areas: List<Area>, city: List<City>) {
        val regionsSpinner = binding.regionsSpinner
        if (regionsSpinner != null) {
            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item, regions
            )
            regionsSpinner.adapter = adapter

            regionsSpinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    Toast.makeText(
                        requireContext(),
                        regions[position].id.toString(),
                        Toast.LENGTH_LONG
                    ).show()

                    //получаем id области
                    val regionID = regions[position].id

                    //сравниваем id района с id областью
                    val regionsAreasFilter = areas.filter { it.regionId == regionID }

                    // по выбранному элементу сравниваем и получаем инфу с id области
                    districtSpinner(regionsAreasFilter, city)
                }

                override fun onNothingSelected(parent: AdapterView<*>) {

                }
            }
        }
    }

    private fun districtSpinner(regionsAreasFilter: List<Area>, cityID: List<City>) {
        val districtSpinner = binding.districtSpinner
        if (districtSpinner != null) {
            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item, regionsAreasFilter
            )
            districtSpinner.adapter = adapter

            districtSpinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    Toast.makeText(
                        requireContext(),
                        regionsAreasFilter[position].id.toString(),
                        Toast.LENGTH_LONG
                    ).show()
                    val districtID = regionsAreasFilter[position].id
                    val areasCityFilter = cityID.filter { it.areaId == districtID }
                    citySpinner(areasCityFilter)

                }

                override fun onNothingSelected(parent: AdapterView<*>) {

                }
            }
        }
    }

    private fun citySpinner(areasCityFilter: List<City>) {
        val citySpinner = binding.citySpinner
        if (citySpinner != null) {
            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item, areasCityFilter
            )
            citySpinner.adapter = adapter

            citySpinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    Toast.makeText(
                        requireContext(),
                        areasCityFilter[position].id.toString(),
                        Toast.LENGTH_LONG
                    ).show()

                }

                override fun onNothingSelected(parent: AdapterView<*>) {

                }
            }
        }
    }


}