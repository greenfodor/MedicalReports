package com.greenfodor.medicalreports.model.responses

import com.google.gson.annotations.SerializedName
import com.greenfodor.medicalreports.model.dbo.Patient
import org.joda.time.DateTime

data class GetPatientResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("dob")
    val dob: String,
    @SerializedName("gender")
    val gender: String
) {
    fun toPatient() = Patient(id, name, DateTime.parse(dob), gender)
}