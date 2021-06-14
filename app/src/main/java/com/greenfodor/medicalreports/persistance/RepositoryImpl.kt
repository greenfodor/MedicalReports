package com.greenfodor.medicalreports.persistance

import com.greenfodor.medicalreports.model.requests.CreatePatientReportRequest
import com.greenfodor.medicalreports.model.requests.CreateUserRequest
import com.greenfodor.medicalreports.model.requests.LoginRequest
import com.greenfodor.medicalreports.model.requests.RegisterPatientRequest
import com.greenfodor.medicalreports.model.responses.*
import com.greenfodor.medicalreports.networking.AppApi
import com.greenfodor.medicalreports.networking.SafeApiRequest
import org.joda.time.DateTime
import org.koin.core.KoinComponent

class RepositoryImpl(private val appApi: AppApi) : Repository, KoinComponent, SafeApiRequest() {

    override suspend fun login(email: String, password: String): LoginResponse {
        return apiRequest { appApi.login(LoginRequest(email, password)) }
    }

    override suspend fun createUser(name: String, email: String, password: String): String {
        return apiRequest { appApi.createUser(CreateUserRequest(name, email, password)) }
    }

    override suspend fun registerPatient(name: String, dob: DateTime, gender: String): RegisterPatientResponse {
        return apiRequest { appApi.registerPatient(RegisterPatientRequest(name, dob.toString(), gender)) }
    }

    override suspend fun getPatient(patientId: String): GetPatientResponse {
        return apiRequest { appApi.getPatient(patientId) }
    }

    override suspend fun getReports(patientId: String): List<GetPatientReportsResponse> {
        return apiRequest { appApi.getReports(patientId) }
    }

    override suspend fun getReport(patientId: String, reportNo: Int): GetReportResponse {
        return apiRequest { appApi.getReport(patientId, reportNo) }
    }

    override suspend fun generateReport(
        patientId: String,
        generalCondition: Int?,
        heartAction: Int?,
        heartSound: Int?,
        breathing: Int?,
        headInjury: Int?
    ): GetReportResponse {
        return apiRequest {
            appApi.generateReport(
                patientId,
                CreatePatientReportRequest(generalCondition, heartAction, heartSound, breathing, headInjury)
            )
        }
    }
}