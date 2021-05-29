package com.greenfodor.medicalreports.networking.interceptors

import com.google.gson.Gson
import com.greenfodor.medicalreports.model.ErrorResponse
import com.greenfodor.medicalreports.utils.SessionUtils
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.nio.charset.Charset

class ErrorInterceptor : Interceptor {
    companion object {
        private val UTF8 = Charset.forName("UTF-8")
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        when (response.code) {
            401 -> {
                doLogout()
            }
            in 400..501 -> {
                throwError(getError(response))
            }
        }

        return response
    }


    private fun doLogout() {
        SessionUtils.clearSession()
        startLoginScreen()
    }

    private fun getError(response: Response): ErrorResponse? {
        val responseBody = response.body

        var error: ErrorResponse? = null
        try {
            val source = responseBody?.source()
            source?.request(java.lang.Long.MAX_VALUE)
            val buffer = source?.buffer

            var charset: Charset? = UTF8
            val contentType = responseBody?.contentType()
            contentType?.let {
                charset = it.charset(UTF8)
            }

            if (responseBody?.contentLength() != 0L && charset != null) {
                val responseJSON = buffer?.clone()?.readString(charset!!)

                try {
                    error = Gson().fromJson(responseJSON, ErrorResponse::class.java)
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                return error
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return null
    }

    private fun throwError(errorResponse: ErrorResponse?) {
        errorResponse?.let {
            throw errorResponse
        }
    }

    private fun startLoginScreen() {

    }
}