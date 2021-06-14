package com.greenfodor.medicalreports.model.responses

import com.google.gson.annotations.SerializedName

data class RegisterPatientResponse(
    @SerializedName("patientId")
    val patientId: String
)
