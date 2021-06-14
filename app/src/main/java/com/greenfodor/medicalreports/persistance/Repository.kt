package com.greenfodor.medicalreports.persistance

import com.greenfodor.medicalreports.model.responses.*
import org.joda.time.DateTime

interface Repository {

    suspend fun login(email: String, password: String): LoginResponse

    suspend fun createUser(name: String, email: String, password: String): String

    suspend fun registerPatient(name: String, dob: DateTime, gender: String): RegisterPatientResponse

    suspend fun getPatient(patientId: String): GetPatientResponse

    suspend fun getReports(patientId: String): List<GetPatientReportsResponse>

    suspend fun getReport(patientId: String, reportNo: Int): GetReportResponse

    suspend fun generateReport(
        patientId: String,
        generalCondition: Int?,
        heartAction: Int?,
        heartSound: Int?,
        breathing: Int?,
        headInjury: Int?
    ): GetReportResponse
}