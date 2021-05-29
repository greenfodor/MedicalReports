package com.greenfodor.medicalreports.networking

import com.greenfodor.medicalreports.model.requests.LoginRequest
import com.greenfodor.medicalreports.model.responses.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AppApi {
    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
}