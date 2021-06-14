package com.greenfodor.medicalreports.presentation.patient.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.greenfodor.medicalreports.model.responses.ErrorResponse
import com.greenfodor.medicalreports.model.responses.GetPatientReportsResponse
import com.greenfodor.medicalreports.model.responses.GetReportResponse
import com.greenfodor.medicalreports.persistance.Repository
import com.greenfodor.medicalreports.presentation.base.BaseViewModel
import com.greenfodor.medicalreports.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GetReportsViewModel(private val repository: Repository) : BaseViewModel() {

    val onReportsReceived = MutableLiveData<List<GetPatientReportsResponse>>()
    val onReportLoaded = SingleLiveEvent<GetReportResponse>()

    fun getReports(patientId: String) {
        isLoading.value = true

        viewModelScope.launch {
            try {
                val reports = withContext(Dispatchers.IO) { repository.getReports(patientId) }
                isLoading.value = false
                onReportsReceived.value = reports
            } catch (e: ErrorResponse) {
                showError.value = e
                isLoading.value = false
            }
        }
    }

    fun getReport(patientId: String, reportNo: Int) {
        isLoading.value = true

        viewModelScope.launch {
            try {
                val report = withContext(Dispatchers.IO) { repository.getReport(patientId, reportNo) }
                isLoading.value = false
                onReportLoaded.value = report
            } catch (e: ErrorResponse) {
                showError.value = e
                isLoading.value = false
            }
        }
    }
}