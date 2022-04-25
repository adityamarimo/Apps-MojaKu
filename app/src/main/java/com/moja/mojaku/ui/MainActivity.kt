package com.moja.mojaku.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.dynamicfeatures.fragment.DynamicNavHostFragment
import androidx.navigation.ui.NavigationUI
import com.moja.mojaku.R
import com.moja.mojaku.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        NavigationUI.setupWithNavController(binding.navView, navController)
    }

    private val navController by lazy {
        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
                as DynamicNavHostFragment).navController
    }
}