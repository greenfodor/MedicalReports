package com.greenfodor.medicalreports.presentation.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.greenfodor.medicalreports.databinding.ActivitySplashBinding
import com.greenfodor.medicalreports.presentation.home.HomeActivity
import com.greenfodor.medicalreports.presentation.startup.LandingActivity
import com.greenfodor.medicalreports.utils.SessionUtils
import com.greenfodor.medicalreports.utils.viewBinding

class SplashActivity : AppCompatActivity() {
    companion object {
        private const val SPLASH_SCREEN_DELAY = 1333L
    }

    private val binding by viewBinding(ActivitySplashBinding::inflate)
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        startHandler()
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacksAndMessages(null)
    }

    private fun startHandler() = handler.postDelayed({
        val clazz = when {
            isUserLoggedIn() -> HomeActivity::class.java
            else -> LandingActivity::class.java
        }
        startNextActivity(clazz)
    }, SPLASH_SCREEN_DELAY)

    private fun isUserLoggedIn() = !SessionUtils.getSessionToken().isNullOrEmpty()

    private fun startNextActivity(clazz: Class<*>) {
        val intent = Intent(this, clazz)
        startActivity(intent)
        finish()
    }
}