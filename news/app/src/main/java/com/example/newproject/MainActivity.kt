package com.example.newproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.newproject.databinding.ActivityLoginBinding
import com.example.newproject.databinding.ActivityMainBinding
import com.example.newproject.ui.profile.ProfilePhotoFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupBottomNav()
    }

        private fun setupBottomNav() {
        val navController = findNavController(R.id.fragment_container_main)
        binding.bottomNavigation.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> showBottomNav()
                R.id.creditsFragment -> showBottomNav()
                R.id.addNewFragment -> showBottomNav()
                R.id.calculatorFragment -> showBottomNav()
                R.id.profileFragment -> showBottomNav()
                else -> binding.bottomNavigation.visibility = View.GONE
            }
        }
    }

    private fun showBottomNav() {
        binding.bottomNavigation.visibility = View.VISIBLE
    }
}