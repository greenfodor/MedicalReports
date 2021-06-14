package com.greenfodor.medicalreports.model.responses

import com.google.gson.annotations.SerializedName

data class GetPatientReportsResponse(
    @SerializedName("reportNo")
    val reportNo: Int,
    @SerializedName("patientId")
    val patientId: String,
    @SerializedName("authorName")
    val authorName: String
)