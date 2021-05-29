package com.greenfodor.medicalreports.presentation.startup

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.greenfodor.medicalreports.databinding.ActivityLoginBinding
import com.greenfodor.medicalreports.presentation.home.HomeActivity
import com.greenfodor.medicalreports.utils.viewBinding
import org.koin.android.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityLoginBinding::inflate)
    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setUpActions()
        setUpObservers()
    }

    private fun setUpActions() {
        binding.passwordEt.setOnEditorActionListener { _, actionId, _ ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_DONE -> {
                    binding.signInBtn.performClick()
                    false
                }
                else -> false
            }
        }

        binding.signInBtn.setOnClickListener {
            val email = binding.emailEt.text?.toString()
            val password = binding.passwordEt.text?.toString()
            loginViewModel.loginUser(email ?: "", password ?: "")
        }
    }

    private fun setUpObservers() {
        loginViewModel.onLoginUser.observe(this) { user ->
            user ?: return@observe

            startHomeScreen()
        }

        loginViewModel.isLoading.observe(this) { shouldShowLoading ->
            shouldShowLoading ?: return@observe

            binding.loadingLottie.isVisible = shouldShowLoading
        }

        loginViewModel.showError.observe(this) { error ->
            error ?: return@observe

            Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun startHomeScreen() {
        val intent = Intent(this, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}