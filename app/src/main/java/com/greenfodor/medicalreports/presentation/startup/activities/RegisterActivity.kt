package com.greenfodor.medicalreports.presentation.startup.activities

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.greenfodor.medicalreports.R
import com.greenfodor.medicalreports.databinding.ActivityRegisterBinding
import com.greenfodor.medicalreports.presentation.startup.viewmodels.RegisterViewModel
import com.greenfodor.medicalreports.utils.viewBinding
import org.koin.android.viewmodel.ext.android.viewModel

class RegisterActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityRegisterBinding::inflate)
    private val registerViewModel: RegisterViewModel by viewModel()

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
                    binding.signUpBtn.performClick()
                    false
                }
                else -> false
            }
        }

        binding.signUpBtn.setOnClickListener {
            val name = binding.nameEt.text?.toString() ?: ""
            val email = binding.emailEt.text?.toString() ?: ""
            val password = binding.passwordEt.text?.toString() ?: ""

            registerViewModel.createUser(name, email, password)
        }
    }

    private fun setUpObservers() {
        registerViewModel.userCreatedEvent.observe(this) {
            Toast.makeText(this, getString(R.string.register_success), Toast.LENGTH_LONG).show()
        }

        registerViewModel.isLoading.observe(this) { shouldDisplayLoading ->
            shouldDisplayLoading ?: return@observe

            binding.loadingLottie.isVisible = shouldDisplayLoading
        }

        registerViewModel.showError.observe(this) { error ->
            error ?: return@observe

            Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
        }
    }
}