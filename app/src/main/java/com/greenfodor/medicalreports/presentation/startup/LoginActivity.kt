package com.greenfodor.medicalreports.presentation.startup

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import com.greenfodor.medicalreports.databinding.ActivityLoginBinding
import com.greenfodor.medicalreports.utils.viewBinding

class LoginActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityLoginBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setUpActions()
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
            //TODO: implement login
        }
    }
}