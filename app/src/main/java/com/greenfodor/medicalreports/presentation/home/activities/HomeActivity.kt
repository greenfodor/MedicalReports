package com.greenfodor.medicalreports.presentation.home.activities

import android.content.Context
import android.content.Intent
import android.nfc.NfcAdapter
import android.nfc.NfcManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.greenfodor.medicalreports.databinding.ActivityHomeBinding
import com.greenfodor.medicalreports.model.enums.UserRoleEnum
import com.greenfodor.medicalreports.presentation.splash.SplashActivity
import com.greenfodor.medicalreports.utils.NfcUtil
import com.greenfodor.medicalreports.utils.SessionUtils
import com.greenfodor.medicalreports.utils.viewBinding

class HomeActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityHomeBinding::inflate)
    private var nfcAdapter: NfcAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setUpActions()
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

        binding.registerPatientBtn.isVisible = SessionUtils.getCurrentUserRole() == UserRoleEnum.NURSE.value
    }

    private fun initNfcAdapter() {
        val nfcManager = getSystemService(Context.NFC_SERVICE) as NfcManager
        nfcAdapter = nfcManager.defaultAdapter
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val message = NfcUtil.retrieveNFCMessage(intent)
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}