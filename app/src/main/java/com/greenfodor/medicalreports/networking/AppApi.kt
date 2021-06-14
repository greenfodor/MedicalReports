package com.greenfodor.medicalreports.networking

import com.greenfodor.medicalreports.model.requests.CreatePatientReportRequest
import com.greenfodor.medicalreports.model.requests.CreateUserRequest
import com.greenfodor.medicalreports.model.requests.LoginRequest
import com.greenfodor.medicalreports.model.requests.RegisterPatientRequest
import com.greenfodor.medicalreports.model.responses.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AppApi {
    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("users/create")
    suspend fun createUser(@Body createUserRequest: CreateUserRequest): Response<String>

    @POST("patients/register")
    suspend fun registerPatient(@Body registerPatientRequest: RegisterPatientRequest): Response<RegisterPatientResponse>

    @GET("patients/{id}")
    suspend fun getPatient(@Path("id") patientId: String): Response<GetPatientResponse>

    @GET("patients/{id}/reports")
    suspend fun getReports(@Path("id") patientId: String): Response<List<GetPatientReportsResponse>>

    @GET("patients/{id}/reports/{reportNo}")
    suspend fun getReport(
        @Path("id") patientId: String,
        @Path("reportNo") reportNo: Int
    ): Response<GetReportResponse>

    @POST("patients/{id}/reports/generate")
    suspend fun generateReport(
        @Path("id") patientId: String,
        @Body request: CreatePatientReportRequest
    ): Response<GetReportResponse>
}