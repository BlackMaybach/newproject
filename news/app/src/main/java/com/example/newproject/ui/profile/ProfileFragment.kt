package com.example.newproject.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.newproject.databinding.FragmentProfileBinding
import com.example.newproject.utils.Status
import com.example.newproject.utils.showToast

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
            Toast.makeText(requireContext(), "AA", Toast.LENGTH_SHORT).show()
        }

        viewModel.getInfoUser()

        viewModel.infoUser.observe(viewLifecycleOwner) {
            when(it.status) {

                Status.SUCCESS -> {
                    binding.userName.text = it.data?.firstName + " " + it.data?.lastName + " " + it.data?.middleName
                    binding.userNumber.text = it.data?.contactPhone
                    binding.userBirth.text = it.data?.issueDate
                    binding.userStatus.text = it.data?.status.toString()
                }

                Status.ERROR -> {
                    requireContext().showToast(it.message)
                }
            }
        }

    }


}