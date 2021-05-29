package com.greenfodor.medicalreports.persistance

import com.greenfodor.medicalreports.model.requests.LoginRequest
import com.greenfodor.medicalreports.model.responses.LoginResponse
import com.greenfodor.medicalreports.networking.AppApi
import com.greenfodor.medicalreports.networking.SafeApiRequest
import org.koin.core.KoinComponent

class RepositoryImpl(private val appApi: AppApi) : Repository, KoinComponent, SafeApiRequest() {

    override suspend fun login(email: String, password: String): LoginResponse {
        return apiRequest { appApi.login(LoginRequest(email, password)) }
    }
}