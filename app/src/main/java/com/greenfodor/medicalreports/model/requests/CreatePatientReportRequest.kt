package com.greenfodor.medicalreports.model.requests

import com.google.gson.annotations.SerializedName

data class CreatePatientReportRequest(
    @SerializedName("generalCondition")
    val generalCondition: Int?,
    @SerializedName("heartAction")
    val heartAction: Int?,
    @SerializedName("heartSound")
    val heartSound: Int?,
    @SerializedName("breathing")
    val breathing: Int?,
    @SerializedName("headInjury")
    val headInjury: Int?
)
