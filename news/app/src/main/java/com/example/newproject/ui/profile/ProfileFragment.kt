package com.example.newproject.ui.profile

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.newproject.R
import com.example.newproject.databinding.FragmentProfileBinding
import com.example.newproject.ui.api.models.AccountPassword
import com.example.newproject.ui.registration.PasswordFragmentDirections
import com.example.newproject.utils.*
import com.google.android.material.bottomsheet.BottomSheetDialog


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

    @SuppressLint("InflateParams", "SetTextI18n")
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

        binding.btnShowChangePasswordBottomSheet.setOnClickListener {
            val dialog = BottomSheetDialog(requireContext())
            val view = layoutInflater.inflate(R.layout.bottom_sheet_change_password, null)

            val buttonSend = view.findViewById<Button>(R.id.buttonSend)

            buttonSend.setOnClickListener {
                val old = view.findViewById<EditText>(R.id.oldChangePassword).text.toString()
                val new = view.findViewById<EditText>(R.id.newChangePassword).text.toString()
                val newConfirm =
                    view.findViewById<EditText>(R.id.newChangePasswordConfirm).text.toString()
                val passAlert = view.findViewById<TextView>(R.id.passAlert)


                if (old.isNullOrEmpty()) {
                    passAlert.show()
                    passAlert.text = "Заполните все поля"
                } else if (new.isEmpty()) {
                    passAlert.show()
                    passAlert.text = "Заполните все поля"
                } else if (newConfirm.isEmpty()) {
                    passAlert.show()
                    passAlert.text = "Заполните все поля"
                } else if (new != newConfirm) {
                    passAlert.show()
                    passAlert.text = "Пароли не совпадают"
                } else {
                    dialog.dismiss()
                    sendPassword(old, new, newConfirm)
                }


            }

            // below line is use to set cancelable to avoid
            // closing of dialog box when clicking on the screen.
            dialog.setCancelable(true)

            // on below line we are setting
            // content view to our view.
            dialog.setContentView(view)

            // on below line we are calling
            // a show method to display a dialog.
            dialog.show()
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

    private fun sendPassword(old: String, new: String, newConfirm: String) {
        val accountPassword = AccountPassword(
            old,
            new,
            newConfirm
        )

        viewModel.refreshPassword(accountPassword)
        viewModel.pass.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.LOADING -> {

                }
                Status.SUCCESS -> {
                    requireContext().showToast("Пароль успешно изменен")
                }

                Status.ERROR -> {
                    requireContext().showToast(it.message)
                }
            }
        }

    }


}