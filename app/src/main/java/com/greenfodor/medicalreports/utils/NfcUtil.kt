package com.greenfodor.medicalreports.utils

import android.app.Activity
import android.app.PendingIntent
import android.content.Intent
import android.nfc.NdefMessage
import android.nfc.NdefRecord
import android.nfc.NfcAdapter
import android.util.Log

object NfcUtil {

    fun <T> enableNfcForegroundDispatch(nfcAdapter: NfcAdapter?, activity: Activity, classType: Class<T>) {
        try {
            val intent = Intent(activity, classType).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            val nfcPendingIntent = PendingIntent.getActivity(activity, 0, intent, 0)
            nfcAdapter?.enableForegroundDispatch(activity, nfcPendingIntent, null, null)
        } catch (ex: IllegalStateException) {
            Log.e("NFC ERROR", "Error enabling NFC foreground dispatch", ex)
        }
    }

    fun disableNfcForegroundDispatch(nfcAdapter: NfcAdapter?, activity: Activity) {
        try {
            nfcAdapter?.disableForegroundDispatch(activity)
        } catch (ex: IllegalStateException) {
            Log.e("NFC ERROR", "Error disabling NFC foreground dispatch", ex)
        }
    }

    fun retrieveNFCMessage(intent: Intent?): String {
        intent?.let {
            if (NfcAdapter.ACTION_NDEF_DISCOVERED == intent.action) {
                val nDefMessages = getNDefMessages(intent)
                nDefMessages[0].records?.let {
                    it.forEach { record ->
                        record?.payload.let { byteArray ->
                            byteArray?.let { btArray ->
                                val entry = String(btArray)
                                return if (entry.length > 3) entry.substring(3, entry.lastIndex) else entry
                            }
                        }
                    }
                }
            } else {
                return "Touch NFC tag to read data"
            }
        }
        return "Touch NFC tag to read data"
    }

    private fun getNDefMessages(intent: Intent): Array<NdefMessage> {
        val rawMessage = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)
        rawMessage?.let {
            return rawMessage.map {
                it as NdefMessage
            }.toTypedArray()
        }
        // Unknown tag type
        val empty = byteArrayOf()
        val record = NdefRecord(NdefRecord.TNF_UNKNOWN, empty, empty, empty)
        val msg = NdefMessage(arrayOf(record))
        return arrayOf(msg)
    }
}