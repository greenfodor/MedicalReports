package com.greenfodor.medicalreports.persistance

import com.greenfodor.medicalreports.model.responses.LoginResponse

interface Repository {

    suspend fun login(email: String, password: String): LoginResponse
}