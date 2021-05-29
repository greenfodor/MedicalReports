package com.greenfodor.medicalreports.model

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("message") override val message: String,
    @SerializedName("status") val status: Int? = null,
    @SerializedName("code") val code: String? = null
) : RuntimeException()