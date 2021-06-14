package com.greenfodor.medicalreports.presentation.home.activities

import android.content.Context
import android.content.Intent
import android.nfc.NfcAdapter
import android.nfc.NfcManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.greenfodor.medicalreports.R
import com.greenfodor.medicalreports.databinding.ActivityWriteTagBinding
import com.greenfodor.medicalreports.utils.NfcUtil
import com.greenfodor.medicalreports.utils.viewBinding

class WriteTagActivity : AppCompatActivity() {

    companion object {
        const val PATIENT_ID_EXTRA_KEY = "PATIENT_ID_EXTRA_KEY"
    }

    private val binding by viewBinding(ActivityWriteTagBinding::inflate)
    private var nfcAdapter: NfcAdapter? = null
    private lateinit var patientId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        patientId = intent.getStringExtra(PATIENT_ID_EXTRA_KEY) ?: ""

        Log.d("whatever", patientId)

        if (patientId.isEmpty()) {
            finish()
            return
        }

        Log.d("whatever", "have id")
        initNfcAdapter()
        Log.d("whatever", "adapter init")
    }

    override fun onResume() {
        super.onResume()
        NfcUtil.enableNfcForegroundDispatch(nfcAdapter, this, javaClass)
    }

    override fun onPause() {
        NfcUtil.disableNfcForegroundDispatch(nfcAdapter, this)
        super.onPause()
    }

    private fun initNfcAdapter() {
        val nfcManager = getSystemService(Context.NFC_SERVICE) as NfcManager
        nfcAdapter = nfcManager.defaultAdapter
    }

    private fun showWriteSuccessfulDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.write_successful_title)
            .setMessage(R.string.write_successful_description)
            .setPositiveButton(R.string.done) { dialog, _ ->
                dialog.dismiss()
                finish()
            }
            .show()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val writeSuccessful = NfcUtil.createNFCMessage(patientId, intent)

        if (writeSuccessful) {
            showWriteSuccessfulDialog()
        }
    }
}