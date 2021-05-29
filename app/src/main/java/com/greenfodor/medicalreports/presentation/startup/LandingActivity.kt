package com.greenfodor.medicalreports.presentation.startup

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.greenfodor.medicalreports.databinding.ActivityLandingBinding
import com.greenfodor.medicalreports.utils.viewBinding

class LandingActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityLandingBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setUpActions()
    }

    private fun setUpActions() {
        binding.signInBtn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}