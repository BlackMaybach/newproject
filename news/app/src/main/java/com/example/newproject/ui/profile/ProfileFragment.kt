package com.example.newproject.ui.profile

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.newproject.databinding.FragmentProfileBinding
import com.example.newproject.utils.*


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    private val viewModel by lazy { ProfileViewModel() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        binding.toolbar1.textViewStart.text = "Настройки приложения"
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnToForm.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToProfileFormFragment())
        }

        binding.darkTheme.setOnClickListener {
            AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)

        }

        binding.lightTheme.setOnClickListener {
            AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
        }

        val nightModeFlags =
            view.context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK

        when (nightModeFlags) {
            Configuration.UI_MODE_NIGHT_YES -> {
                binding.darkTheme.gone()
                binding.lightTheme.show()
            }
            Configuration.UI_MODE_NIGHT_NO -> {
                binding.darkTheme.show()
                binding.lightTheme.gone()
            }
            Configuration.UI_MODE_NIGHT_UNDEFINED -> {}
        }

        viewModel.getInfoUser()

        viewModel.infoUser.observe(viewLifecycleOwner) {
            when (it.status) {

                Status.SUCCESS -> {
                    binding.userName.text =
                        it.data?.firstName + " " + it.data?.lastName + " " + it.data?.middleName
                    binding.userNumber.text = "0" + it.data?.contactPhone
                    binding.userBirth.text = it.data?.issueDate
                    val statusID = it.data?.status!!
                    getStatus(statusID)
                }

                Status.ERROR -> {
                    requireContext().showToast(it.message)
                }
            }
        }

    }

    private fun getStatus(id: Int) {
        for (status in UserStatus.values()) {
            if (status.id == id) {
                binding.userStatus.text = status.desc
                binding.creditStatus.text = status.creditStatus
            } else {
                binding.userStatus.text = "NONE"
            }
        }
    }

}