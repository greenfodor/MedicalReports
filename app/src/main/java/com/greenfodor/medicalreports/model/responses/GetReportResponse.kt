package com.greenfodor.medicalreports.model.responses

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
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
) : Parcelable