package com.greenfodor.medicalreports.model.responses

import com.google.gson.annotations.SerializedName

data class GetReportResponse(
    @SerializedName("reportNo")
    val reportNo: Int,
    @SerializedName("patientName")
    val patientName: String,
    @SerializedName("authorName")
    val authorName: String,
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
