package com.greenfodor.medicalreports.presentation.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.greenfodor.medicalreports.databinding.ActivityHomeBinding
import com.greenfodor.medicalreports.presentation.splash.SplashActivity
import com.greenfodor.medicalreports.utils.SessionUtils
import com.greenfodor.medicalreports.utils.viewBinding

class HomeActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityHomeBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setUpActions()
    }

    private fun setUpActions() {
        binding.signOutBtn.setOnClickListener {
            SessionUtils.clearSession()

            val intent = Intent(this, SplashActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }
}