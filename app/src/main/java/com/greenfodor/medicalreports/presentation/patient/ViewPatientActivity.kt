package com.greenfodor.medicalreports.presentation.patient

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.greenfodor.medicalreports.R
import com.greenfodor.medicalreports.databinding.ActivityViewPatientBinding
import com.greenfodor.medicalreports.model.dbo.Patient
import com.greenfodor.medicalreports.utils.viewBinding

class ViewPatientActivity : AppCompatActivity() {

    companion object {
        const val PATIENT_EXTRA_KEY = "PATIENT_EXTRA_KEY"
    }

    private val binding by viewBinding(ActivityViewPatientBinding::inflate)

    private lateinit var patient: Patient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        patient = intent.getParcelableExtra(PATIENT_EXTRA_KEY) ?: return
        initViews()
    }

    private fun initViews() {
        binding.nameTv.text = getString(R.string.name_label, patient.name)
        binding.dobTv.text = getString(R.string.dob_label, patient.dob.toString("dd-MM-yyyy"))
        binding.genderTv.text = getString(R.string.gender_label, patient.gender)
    }
}