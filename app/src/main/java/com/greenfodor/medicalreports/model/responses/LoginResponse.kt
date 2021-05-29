package com.greenfodor.medicalreports.model.responses

import com.google.gson.annotations.SerializedName
import com.greenfodor.medicalreports.model.dbo.User
import com.greenfodor.medicalreports.model.enums.UserRoleEnum

data class LoginResponse(
    @SerializedName("user")
    val user: UserResponse?,
    @SerializedName("token")
    val token: String?
)

data class UserResponse(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("role")
    val role: String?
) {
    fun toUser() = User(
        id ?: "", name ?: "", email ?: "",
        when (role) {
            UserRoleEnum.ADMIN.value -> UserRoleEnum.ADMIN
            UserRoleEnum.MEDICAL_LABORATORY_PROFESSIONAL.value -> UserRoleEnum.MEDICAL_LABORATORY_PROFESSIONAL
            UserRoleEnum.NURSE.value -> UserRoleEnum.NURSE
            UserRoleEnum.PHYSICIAN.value -> UserRoleEnum.PHYSICIAN
            else -> UserRoleEnum.UNASSIGNED
        }
    )
}