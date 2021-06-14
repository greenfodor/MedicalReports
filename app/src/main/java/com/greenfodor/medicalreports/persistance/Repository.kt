package com.greenfodor.medicalreports.persistance

import com.greenfodor.medicalreports.model.responses.GetPatientResponse
import com.greenfodor.medicalreports.model.responses.LoginResponse
import com.greenfodor.medicalreports.model.responses.RegisterPatientResponse
import org.joda.time.DateTime

interface Repository {

    suspend fun login(email: String, password: String): LoginResponse

    suspend fun createUser(name: String, email: String, password: String): String

    suspend fun registerPatient(name: String, dob: DateTime, gender: String): RegisterPatientResponse

    suspend fun getPatient(patientId: String): GetPatientResponse
}