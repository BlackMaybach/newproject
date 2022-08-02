package com.example.newproject.ui.start

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.newproject.App
import com.example.newproject.R
import com.example.newproject.databinding.FragmentStartBinding
import com.example.newproject.utils.SharedPreference
import com.example.newproject.utils.gone


class StartFragment : Fragment() {


    private lateinit var binding: FragmentStartBinding
    private val shared_pref = SharedPreference(App.instance.applicationContext)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartBinding.inflate(inflater,container,false)

        binding.toolbar1.toolbarExitButton.gone()
        val imageSlider = binding.imageSlider
        val imageList = ArrayList<SlideModel>()
        imageList.add(SlideModel(R.drawable.carousel))
        imageList.add(SlideModel(R.drawable.carouseltwo))
        imageSlider.setImageList(imageList, ScaleTypes.CENTER_INSIDE) //FIT
        imageSlider.stopSliding()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginFragment.setOnClickListener {
            findNavController().navigate(StartFragmentDirections.actionStartFragmentToLoginFragment())
        }

        binding.regFragment.setOnClickListener {
            findNavController().navigate(
                StartFragmentDirections.actionStartFragmentToRegistrationFragment(
                    0
                )
            )
        }

        binding.link.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.elet.kg"))
            startActivity(i)
        }

        Log.e("TOKEN", shared_pref.userToken.toString())


    }

    override fun onDestroyView() {
        super.onDestroyView()
        return binding = null
    }
}
