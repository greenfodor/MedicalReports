package com.greenfodor.medicalreports.presentation.home.activities

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.greenfodor.medicalreports.databinding.ActivityRegisterPatientBinding
import com.greenfodor.medicalreports.presentation.home.viewmodels.RegisterPatientViewModel
import com.greenfodor.medicalreports.utils.viewBinding
import org.joda.time.DateTime
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class RegisterPatientActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    private val binding by viewBinding(ActivityRegisterPatientBinding::inflate)
    private val registerPatientViewModel: RegisterPatientViewModel by viewModel()

    private var selectedDate: DateTime? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setUpActions()
        setUpObservers()
    }

    private fun setUpActions() {
        binding.registerBtn.setOnClickListener {
            val name = binding.nameEt.text?.toString()
            val gender = binding.genderEt.text?.toString()

            if (name.isNullOrEmpty() || gender.isNullOrEmpty() || selectedDate == null) {
                Toast.makeText(this, "All fields are required in order to proceed.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            selectedDate?.let { registerPatientViewModel.registerPatient(name, it, gender) }
        }

        binding.dateEt.setOnClickListener {
            val calendar = Calendar.getInstance()
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val month = calendar.get(Calendar.MONTH)
            val year = calendar.get(Calendar.YEAR)

            DatePickerDialog(this, this, year, month, day).show()
        }
    }

    private fun setUpObservers() {
        registerPatientViewModel.onRegisterPatient.observe(this) { patientId ->
            patientId ?: return@observe

            startWriteTagActivity(patientId)
        }

        registerPatientViewModel.isLoading.observe(this) { shouldShowLoading ->
            shouldShowLoading ?: return@observe

            binding.loadingLottie.isVisible = shouldShowLoading
        }

        registerPatientViewModel.showError.observe(this) { error ->
            error ?: return@observe

            Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun startWriteTagActivity(patientId: String) {
        val intent = Intent(this, WriteTagActivity::class.java).apply {
            putExtra(WriteTagActivity.PATIENT_ID_EXTRA_KEY, patientId)
        }
        startActivity(intent)
        finish()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        selectedDate = DateTime(year, month + 1, dayOfMonth, 0, 0)

        binding.dateEt.setText(selectedDate?.toString("dd-MM-yyyy"))
    }
}