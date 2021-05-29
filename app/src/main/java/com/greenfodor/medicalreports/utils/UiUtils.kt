package com.greenfodor.medicalreports.utils

import androidx.annotation.StringRes
import com.greenfodor.medicalreports.MedicalReportsApp

object UiUtils {

    private fun getAppContext() = MedicalReportsApp.instance

    fun getString(@StringRes id: Int) = getAppContext().getString(id)
}