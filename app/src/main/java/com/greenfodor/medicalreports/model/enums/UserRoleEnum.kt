package com.greenfodor.medicalreports.model.enums

enum class UserRoleEnum(val value: String) {
    ADMIN(value = "admin"),
    MEDICAL_LABORATORY_PROFESSIONAL(value = "mlp"),
    PHYSICIAN(value = "physician"),
    NURSE(value = "nurse"),
    UNASSIGNED("")
}