package com.example.newproject.ui.registration

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.example.newproject.R
import com.example.newproject.databinding.FragmentPasswordBinding


class PasswordFragment : Fragment() {

    private lateinit var binding: FragmentPasswordBinding
    private val args: PasswordFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPasswordBinding.inflate(inflater,container,false)
        binding.toolbar2.textToolbar.text = "Пароль"
        binding.toolbar2.backButton.setOnClickListener {
            activity?.onBackPressed()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // код из фрагмета смс
       val code = args.code
        Log.e("CODE","$code")



        binding.btnSavePassword.setOnClickListener {
            val pass = binding.newPassword.text.toString()
            val pass2 = binding.newPasswordDouble.text.toString()
                if ((pass == pass2) && (pass.isNotEmpty() && pass2.isNotEmpty())) {
                    Toast.makeText(context, "Пароли совпадают: $pass + $pass2", Toast.LENGTH_LONG)
                        .show()
                } else {
                    Toast.makeText(context, "Пароли не совпадают", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
