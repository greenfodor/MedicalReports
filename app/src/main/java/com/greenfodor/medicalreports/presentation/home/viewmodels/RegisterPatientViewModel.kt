package com.greenfodor.medicalreports.presentation.home.viewmodels

import androidx.lifecycle.viewModelScope
import com.greenfodor.medicalreports.model.responses.ErrorResponse
import com.greenfodor.medicalreports.persistance.Repository
import com.greenfodor.medicalreports.presentation.base.BaseViewModel
import com.greenfodor.medicalreports.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.joda.time.DateTime

class RegisterPatientViewModel(private val repository: Repository) : BaseViewModel() {

    val onRegisterPatient = SingleLiveEvent<String>()

    fun registerPatient(name: String, dob: DateTime, gender: String) {
        isLoading.value = true

        viewModelScope.launch {
            try {
                val registerResponse = withContext(Dispatchers.IO) { repository.registerPatient(name, dob, gender) }
                isLoading.value = false
                onRegisterPatient.value = registerResponse.patientId
            } catch (e: ErrorResponse) {
                showError.value = e
                isLoading.value = false
            }
        }
    }
}