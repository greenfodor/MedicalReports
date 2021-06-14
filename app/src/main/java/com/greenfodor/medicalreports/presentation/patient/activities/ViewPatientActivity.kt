package com.greenfodor.medicalreports.presentation.patient.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.greenfodor.medicalreports.R
import com.greenfodor.medicalreports.databinding.ActivityViewPatientBinding
import com.greenfodor.medicalreports.model.dbo.Patient
import com.greenfodor.medicalreports.presentation.patient.adapters.ReportsAdapter
import com.greenfodor.medicalreports.presentation.patient.viewmodels.GetReportsViewModel
import com.greenfodor.medicalreports.utils.viewBinding
import org.koin.android.viewmodel.ext.android.viewModel

class ViewPatientActivity : AppCompatActivity() {

    companion object {
        const val PATIENT_EXTRA_KEY = "PATIENT_EXTRA_KEY"
    }

    private val binding by viewBinding(ActivityViewPatientBinding::inflate)
    private val getReportsViewModel: GetReportsViewModel by viewModel()

    private lateinit var patient: Patient
    private lateinit var adapter: ReportsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        patient = intent.getParcelableExtra(PATIENT_EXTRA_KEY) ?: return
        initViews()
        initRv()
        initActions()
        initObservers()
    }

    override fun onResume() {
        super.onResume()

        getReportsViewModel.getReports(patient.id)
    }

    private fun initViews() {
        binding.nameTv.text = getString(R.string.name_label, patient.name)
        binding.dobTv.text = getString(R.string.dob_label, patient.dob.toString("dd-MM-yyyy"))
        binding.genderTv.text = getString(R.string.gender_label, patient.gender)
    }

    private fun initRv() {
        adapter = ReportsAdapter {
            getReportsViewModel.getReport(it.patientId, it.reportNo)
        }
        binding.reportsRv.adapter = adapter
    }

    private fun initActions() {
        binding.generateReportBtn.setOnClickListener {
            val intent = Intent(this, GenerateReportActivity::class.java).apply {
                putExtra(GenerateReportActivity.PATIENT_EXTRA_KEY, patient)
            }
            startActivity(intent)
        }
    }

    private fun initObservers() {
        getReportsViewModel.onReportsReceived.observe(this) { reports ->
            reports ?: return@observe

            adapter.submitList(reports)
        }

        getReportsViewModel.isLoading.observe(this) { isLoading ->
            isLoading ?: return@observe

            binding.loadingLottie.isVisible = isLoading
        }

        getReportsViewModel.showError.observe(this) { error ->
            error ?: return@observe

            Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
        }
    }
}