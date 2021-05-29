package com.greenfodor.medicalreports.presentation.startup

import androidx.lifecycle.viewModelScope
import com.greenfodor.medicalreports.model.dbo.User
import com.greenfodor.medicalreports.model.responses.ErrorResponse
import com.greenfodor.medicalreports.persistance.Repository
import com.greenfodor.medicalreports.presentation.base.BaseViewModel
import com.greenfodor.medicalreports.utils.SessionUtils
import com.greenfodor.medicalreports.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: Repository) : BaseViewModel() {

    val onLoginUser = SingleLiveEvent<User>()

    fun loginUser(email: String, password: String) {
        isLoading.value = true
        viewModelScope.launch {
            try {
                val authResponse = repository.login(email, password)
                isLoading.value = false

                SessionUtils.saveSessionToken(authResponse.token)
                onLoginUser.value = authResponse.user?.toUser()

            } catch (e: ErrorResponse) {
                showError.value = e
                isLoading.value = false
            }
        }
    }
}