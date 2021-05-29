package com.greenfodor.medicalreports.utils

import android.content.Context
import com.greenfodor.medicalreports.MedicalReportsApp

object SessionUtils {
    private const val SESSION_PREFERENCES = "user_sh_preferences"
    private const val SESSION_TOKEN_KEY = "SESSION_TOKEN_KEY"

    private fun getSessionPreferences() =
        MedicalReportsApp.instance.applicationContext.getSharedPreferences(SESSION_PREFERENCES, Context.MODE_PRIVATE)

    fun saveSessionToken(accessToken: String?) = getSessionPreferences().edit().putString(SESSION_TOKEN_KEY, accessToken).apply()

    fun getSessionToken(): String? = getSessionPreferences().getString(SESSION_TOKEN_KEY, null)

    fun clearSession() = getSessionPreferences().edit().clear().apply()
}