package com.example.newproject.ui.profile

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newproject.R
import com.example.newproject.databinding.FragmentProfileFormBinding
import com.example.newproject.utils.Status
import com.example.newproject.utils.showToast


class ProfileFormFragment : Fragment() {

    private lateinit var binding: FragmentProfileFormBinding
    private val viewModel by lazy { ProfileFormViewModel() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentProfileFormBinding.inflate(inflater, container, false)
        binding.toolbar2.textToolbar.text = "Анкета"
        binding.toolbar2.backButton.setOnClickListener {
            activity?.onBackPressed()
        }
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getInfoUser()

        viewModel.infoFormUser.observe(viewLifecycleOwner) {
            when (it.status) {

                Status.SUCCESS -> {
                    binding.firstName.text = it.data?.firstName
                    binding.lastName.text = it.data?.lastName
                    binding.middleName.text = it.data?.middleName
                    binding.contactPhone.text = "0" + it.data?.contactPhone
                    binding.identificationNumber.text = it.data?.identificationNumber

                    val documentSeries = it.data?.documentSeries!!
                    binding.documentSeries.text = documentSeries

                    binding.issueAuthority.text = it.data.issueAuthority
                    binding.registrationCityName.text = it.data.registrationCityName
                    binding.registrationStreet.text = it.data.registrationStreet
                    binding.registrationHouse.text = it.data.registrationHouse
                    binding.registrationFlat.text = it.data.registrationFlat

                    binding.registrationCityNameFact.text = it.data.registrationCityName
                    binding.registrationStreetFact.text = it.data.residenceStreet
                    binding.registrationHouseFact.text = it.data.residenceHouse
                    binding.registrationFlatFact.text = it.data.residenceFlat



                    getTypeDocument(documentSeries)
                }

                Status.ERROR -> {
                    requireContext().showToast(it.message)
                }
            }
        }

    }

    private fun getTypeDocument(documentSeries: String) {
        if (documentSeries == "ID") {
            binding.documentTypeId.text = "Паспорт гражданина Кыргызской Республики образца 2017 года"
        } else {
            binding.documentTypeId.text = "Паспорт гражданина Кыргызской Республики образца ниже 2017 года"
        }
    }


}