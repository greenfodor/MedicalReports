package com.greenfodor.medicalreports.presentation.home.viewmodels

import androidx.lifecycle.viewModelScope
import com.greenfodor.medicalreports.model.dbo.Patient
import com.greenfodor.medicalreports.model.responses.ErrorResponse
import com.greenfodor.medicalreports.persistance.Repository
import com.greenfodor.medicalreports.presentation.base.BaseViewModel
import com.greenfodor.medicalreports.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.joda.time.DateTime

class GetPatientViewModel(private val repository: Repository) : BaseViewModel() {

    val onPatientReceived = SingleLiveEvent<Patient>()

    fun getPatient(id: String) {
        isLoading.value = true

        viewModelScope.launch {
            try {
                val getPatientResponse = withContext(Dispatchers.IO) { repository.getPatient(id) }
                isLoading.value = false
                onPatientReceived.value = getPatientResponse.toPatient()
            } catch (e: ErrorResponse) {
                showError.value = e
                isLoading.value = false
            }
        }
    }
}