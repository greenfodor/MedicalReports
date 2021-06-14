package com.greenfodor.medicalreports.model.requests

import com.google.gson.annotations.SerializedName

data class RegisterPatientRequest(
    @SerializedName("name")
    val name: String,
    @SerializedName("dob")
    val dob: String,
    @SerializedName("gender")
    val gender: String
)