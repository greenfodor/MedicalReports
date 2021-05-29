package com.greenfodor.medicalreports.model.dbo

import com.greenfodor.medicalreports.model.enums.UserRoleEnum

data class User(
    val id: String,
    val name: String,
    val email: String,
    val role: UserRoleEnum
)