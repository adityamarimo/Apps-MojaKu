package com.moja.mojaku.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import com.moja.mojaku.R
import com.moja.mojaku.databinding.ActivitySplashScreenBinding
import com.moja.mojaku.ui.main.MainActivity
import com.moja.mojaku.ui.base.BaseActivity
import kotlinx.coroutines.*

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : BaseActivity<ActivitySplashScreenBinding>() {
    override fun getViewBinding(): ActivitySplashScreenBinding =
        ActivitySplashScreenBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        CoroutineScope(Dispatchers.Main).launch {
            delay(1500)

            Intent(this@SplashScreenActivity, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }
    }

    override fun onBackPressed() {
        if (onBackPressedDispatcher.hasEnabledCallbacks()) {
            super.onBackPressed()
        } else {
            finishAfterTransition()
        }
    }
}