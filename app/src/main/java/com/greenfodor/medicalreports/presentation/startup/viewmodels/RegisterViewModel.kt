package com.greenfodor.medicalreports.presentation.startup.viewmodels

import androidx.lifecycle.viewModelScope
import com.greenfodor.medicalreports.model.responses.ErrorResponse
import com.greenfodor.medicalreports.persistance.Repository
import com.greenfodor.medicalreports.presentation.base.BaseViewModel
import com.greenfodor.medicalreports.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterViewModel(private val repository: Repository) : BaseViewModel() {

    val userCreatedEvent = SingleLiveEvent<Any>()

    fun createUser(name: String, email: String, password: String) {
        isLoading.value = true
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) { repository.createUser(name, email, password) }
                isLoading.value = false
                userCreatedEvent.call()
            } catch (e: ErrorResponse) {
                showError.value = e
                isLoading.value = false
            }
        }
    }
}