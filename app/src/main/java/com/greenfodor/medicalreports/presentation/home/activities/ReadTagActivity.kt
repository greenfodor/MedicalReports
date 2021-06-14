package com.greenfodor.medicalreports.presentation.home.activities

import android.content.Context
import android.content.Intent
import android.nfc.NfcAdapter
import android.nfc.NfcManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.greenfodor.medicalreports.databinding.ActivityReadTagBinding
import com.greenfodor.medicalreports.model.dbo.Patient
import com.greenfodor.medicalreports.presentation.home.viewmodels.GetPatientViewModel
import com.greenfodor.medicalreports.presentation.patient.activities.ViewPatientActivity
import com.greenfodor.medicalreports.utils.NfcUtil
import com.greenfodor.medicalreports.utils.viewBinding
import org.koin.android.viewmodel.ext.android.viewModel

class ReadTagActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityReadTagBinding::inflate)
    private val getPatientViewModel: GetPatientViewModel by viewModel()

    private var nfcAdapter: NfcAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initObservers()
        initNfcAdapter()
    }

    override fun onResume() {
        super.onResume()
        NfcUtil.enableNfcForegroundDispatch(nfcAdapter, this, javaClass)
    }

    override fun onPause() {
        NfcUtil.disableNfcForegroundDispatch(nfcAdapter, this)
        super.onPause()
    }

    private fun initObservers() {
        getPatientViewModel.onPatientReceived.observe(this) { patient ->
            patient ?: return@observe

            openViewPatient(patient)
        }

        getPatientViewModel.showError.observe(this) { error ->
            error ?: return@observe

            Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun initNfcAdapter() {
        val nfcManager = getSystemService(Context.NFC_SERVICE) as NfcManager
        nfcAdapter = nfcManager.defaultAdapter
    }

    private fun openViewPatient(patient: Patient) {
        val intent = Intent(this, ViewPatientActivity::class.java).apply {
            putExtra(ViewPatientActivity.PATIENT_EXTRA_KEY, patient)
        }
        startActivity(intent)
        finish()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val patientId = NfcUtil.retrieveNFCMessage(intent)
        getPatientViewModel.getPatient(patientId)
    }
}