package com.greenfodor.medicalreports.model.dbo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.joda.time.DateTime

@Parcelize
data class Patient(
    val id: String,
    val name: String,
    val dob: DateTime,
    val gender: String
) : Parcelable