package com.example.newproject.ui.profile

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

        binding = FragmentProfileFormBinding.inflate(inflater,container,false)
        binding.toolbar2.textToolbar.text = "Анкета"
        binding.toolbar2.backButton.setOnClickListener {
            activity?.onBackPressed()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getInfoUser()

        viewModel.infoFormUser.observe(viewLifecycleOwner) {
            when(it.status) {

                Status.SUCCESS -> {
                    binding.firstName.text = it.data?.firstName
                    binding.lastName.text = it.data?.lastName
                    binding.middleName.text = it.data?.middleName
                    binding.contactPhone.text = it.data?.contactPhone
                    binding.identificationNumber.text = it.data?.identificationNumber
                    binding.documentSeries.text = it.data?.documentSeries
                    binding.issueAuthority.text = it.data?.issueAuthority
                }

                Status.ERROR -> {
                    requireContext().showToast(it.message)
                }
            }
        }

    }

}