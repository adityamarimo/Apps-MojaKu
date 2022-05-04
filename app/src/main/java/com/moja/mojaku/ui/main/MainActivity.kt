package com.moja.mojaku.ui.main

import android.os.Bundle
import androidx.navigation.dynamicfeatures.fragment.DynamicNavHostFragment
import androidx.navigation.ui.NavigationUI
import com.moja.mojaku.R
import com.moja.mojaku.databinding.ActivityMainBinding
import com.moja.mojaku.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    private val navController by lazy {
        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
                as DynamicNavHostFragment).navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initNavigation()
    }


    private fun initNavigation() {
        NavigationUI.setupWithNavController(binding?.navView!!, navController)
    }

    override fun onBackPressed() {
        if (onBackPressedDispatcher.hasEnabledCallbacks()) {
            super.onBackPressed()
        } else {
            finishAfterTransition()
        }
    }
}