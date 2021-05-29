package com.greenfodor.medicalreports.model.requests

import com.google.gson.annotations.SerializedName

data class CreateUserRequest(
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)