package com.greenfodor.medicalreports.networking.interceptors

import com.greenfodor.medicalreports.utils.SessionUtils
import okhttp3.Interceptor
import okhttp3.Response

class ErrorInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        when (response.code) {
            401 -> {
                doLogout()
            }
        }

        return response
    }


    private fun doLogout() {
        SessionUtils.clearSession()
        startLoginScreen()
    }

    private fun startLoginScreen() {

    }
}