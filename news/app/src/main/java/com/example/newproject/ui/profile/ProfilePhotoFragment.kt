package com.example.newproject.ui.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newproject.databinding.FragmentProfilePhotoBinding
import com.example.newproject.repository.Repository
import com.example.newproject.utils.Status
import com.example.newproject.utils.showToast


class ProfilePhotoFragment : Fragment() {


    private lateinit var binding : FragmentProfilePhotoBinding

    private val viewmodel by lazy { ProfilePhotoViewModel() }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentProfilePhotoBinding.inflate(inflater,container,false)
        binding.toolbar2.textToolbar.text = "Фото"
        binding.toolbar2.backButton.setOnClickListener{
            activity?.onBackPressed()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sendIDUser()
    }

    private fun sendIDUser() {

        viewmodel.takeID()

        viewmodel.actions.observe(viewLifecycleOwner) {

            when (it.status) {
                Status.LOADING -> {

                }
                Status.SUCCESS -> {

                }

                Status.ERROR -> {
                    requireContext().showToast(it.message)
                }
            }

        }

    }

}