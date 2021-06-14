package com.greenfodor.medicalreports.presentation.patient.viewmodels

import androidx.lifecycle.viewModelScope
import com.greenfodor.medicalreports.model.responses.ErrorResponse
import com.greenfodor.medicalreports.model.responses.GetReportResponse
import com.greenfodor.medicalreports.persistance.Repository
import com.greenfodor.medicalreports.presentation.base.BaseViewModel
import com.greenfodor.medicalreports.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GenerateReportViewModel(private val repository: Repository) : BaseViewModel() {

    val onReportGenerated = SingleLiveEvent<GetReportResponse>()

    fun generateReport(patientId: String, generalCondition: Int?, heartAction: Int?, heartSound: Int?, breathing: Int?, headInjury: Int?) {
        isLoading.value = true

        viewModelScope.launch {
            try {
                val report = withContext(Dispatchers.IO) {
                    repository.generateReport(patientId, generalCondition, heartAction, heartSound, breathing, headInjury)
                }
                isLoading.value = false
                onReportGenerated.value = report
            } catch (e: ErrorResponse) {
                showError.value = e
                isLoading.value = false
            }
        }
    }
}
