package com.greenfodor.medicalreports.utils

import android.content.Context
import com.google.gson.Gson
import com.greenfodor.medicalreports.MedicalReportsApp
import com.greenfodor.medicalreports.model.dbo.User

object SessionUtils {
    private const val SESSION_PREFERENCES = "user_sh_preferences"
    private const val SESSION_TOKEN_KEY = "SESSION_TOKEN_KEY"
    private const val CURRENT_USER_KEY = "CURRENT_USER_KEY"

    private fun getSessionPreferences() =
        MedicalReportsApp.instance.applicationContext.getSharedPreferences(SESSION_PREFERENCES, Context.MODE_PRIVATE)

    fun saveSessionToken(accessToken: String?) = getSessionPreferences().edit().putString(SESSION_TOKEN_KEY, accessToken).apply()

    fun getSessionToken(): String? = getSessionPreferences().getString(SESSION_TOKEN_KEY, null)

    fun saveCurrentUser(user: User) {
        val gson = Gson()
        val userAsString = gson.toJson(user)

        getSessionPreferences().edit()
            .putString(CURRENT_USER_KEY, userAsString)
            .apply()
    }

    fun getCurrentUserRole(): String? {
        val userAsString = getSessionPreferences().getString(CURRENT_USER_KEY, "")
        if (userAsString.isNullOrEmpty()) return null

        val gson = Gson()
        val user = gson.fromJson(userAsString, User::class.java)
        return user.role.value
    }

    fun clearSession() = getSessionPreferences().edit().clear().apply()
}