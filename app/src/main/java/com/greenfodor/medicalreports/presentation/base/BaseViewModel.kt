package com.greenfodor.medicalreports.presentation.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.greenfodor.medicalreports.model.responses.ErrorResponse

abstract class BaseViewModel : ViewModel() {
    val showError = MutableLiveData<ErrorResponse>()
    val isLoading = MutableLiveData<Boolean>()
}