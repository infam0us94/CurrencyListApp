package com.currencyapplication.currencylistapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.currencyapplication.currencylistapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initNavigation()
    }

    private fun initNavigation() {
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation_view)
        val navController: NavController = findNavController(R.id.container_for_fragments)
        bottomNavigationView.setupWithNavController(navController)
    }
}