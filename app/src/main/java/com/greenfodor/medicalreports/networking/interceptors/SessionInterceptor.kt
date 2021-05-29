package com.greenfodor.medicalreports.networking.interceptors

import com.greenfodor.medicalreports.BuildConfig
import com.greenfodor.medicalreports.utils.Constants
import com.greenfodor.medicalreports.utils.SessionUtils
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class SessionInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()

        val url = chain.request().url.toString()

        if (url.contains(BuildConfig.BASE_ENDPOINT)) {
            SessionUtils.getSessionToken()?.let {
                builder.addHeader(Constants.Api.AUTHORIZATION, Constants.Api.BEARER_TOKEN + it)
            }
        }

        return chain.proceed(builder.build())
    }
}