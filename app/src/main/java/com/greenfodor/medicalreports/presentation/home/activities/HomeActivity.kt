package com.greenfodor.medicalreports.presentation.home.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.greenfodor.medicalreports.databinding.ActivityHomeBinding
import com.greenfodor.medicalreports.model.enums.UserRoleEnum
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

        binding.registerPatientBtn.setOnClickListener {
            val intent = Intent(this, RegisterPatientActivity::class.java)
            startActivity(intent)
        }

        binding.readPatientTagBtn.setOnClickListener {
            val intent = Intent(this, ReadTagActivity::class.java)
            startActivity(intent)
        }

        binding.registerPatientBtn.isVisible = SessionUtils.getCurrentUserRole() == UserRoleEnum.NURSE.value
    }
}