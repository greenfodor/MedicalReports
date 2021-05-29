package com.greenfodor.medicalreports.persistance

import com.greenfodor.medicalreports.model.responses.LoginResponse

interface Repository {

    suspend fun login(email: String, password: String): LoginResponse

    suspend fun createUser(name: String, email: String, password: String): String
}